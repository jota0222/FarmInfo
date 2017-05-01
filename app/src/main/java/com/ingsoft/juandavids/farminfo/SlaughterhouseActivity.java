package com.ingsoft.juandavids.farminfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ingsoft.juandavids.farminfo.adapter.MedicineAdapter;
import com.ingsoft.juandavids.farminfo.adapter.SlaughterhouseAdapter;
import com.ingsoft.juandavids.farminfo.model.AnimalInfo;
import com.ingsoft.juandavids.farminfo.model.Medicine;
import com.ingsoft.juandavids.farminfo.model.Slaughterhouse;
import com.socrata.android.client.Callback;
import com.socrata.android.client.Consumer;
import com.socrata.android.client.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SlaughterhouseActivity extends AppCompatActivity {

    Consumer consumer;

    AnimalInfo animalInfo;
    List<Slaughterhouse> slaughterhouses;
    List<Slaughterhouse> filteredSlaughterhouse;
    ListView slaughterhouseView;
    List<Slaughterhouse> slaughterhouseOff;
    EditText searchBox;
    public ArrayList<Typeface> typeFaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        Intent intent = getIntent();
        animalInfo = intent.getParcelableExtra("animalGroup");

        setFonts();

        if(checkConnection(this)){
            fetchData();
            setSearch();
        }
        else{
            slaughterhouseOff = loadOffline();
            if(slaughterhouseOff != null) {
                viewOffline(slaughterhouseOff);
                searchOff();
            }
        }
    }

    private void setSearch() {
        searchBox = (EditText) findViewById(R.id.searchBox);
        filteredSlaughterhouse = new ArrayList<>();

        searchBox.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (slaughterhouses != null && slaughterhouses.size() != 0) {
                    String searchText = s.toString().toLowerCase();

                    filteredSlaughterhouse.clear();

                    for (int i = 0; i < slaughterhouses.size(); i++) {
                        Slaughterhouse item = slaughterhouses.get(i);
                        if (item.getName().toLowerCase().contains(searchText)
                                || item.getDepartament().toLowerCase().contains(searchText)
                                || item.getTown().toLowerCase().contains(searchText)) {
                            filteredSlaughterhouse.add(item);
                        }
                    }

                    slaughterhouseView.setAdapter(new SlaughterhouseAdapter(SlaughterhouseActivity.this, filteredSlaughterhouse));
                }
            }
        });
    }

    private void searchOff() {
        searchBox = (EditText) findViewById(R.id.searchBox);
        filteredSlaughterhouse = new ArrayList<>();

        searchBox.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (slaughterhouseOff != null && slaughterhouseOff.size() != 0) {
                    String searchText = s.toString().toLowerCase();

                    filteredSlaughterhouse.clear();

                    for (int i = 0; i < slaughterhouseOff.size(); i++) {
                        Slaughterhouse item = slaughterhouseOff.get(i);
                        if (item.getName().toLowerCase().contains(searchText)
                                || item.getDepartament().toLowerCase().contains(searchText)
                                || item.getTown().toLowerCase().contains(searchText)) {
                            filteredSlaughterhouse.add(item);
                        }
                    }

                    slaughterhouseView.setAdapter(new SlaughterhouseAdapter(SlaughterhouseActivity.this, filteredSlaughterhouse));
                }
            }
        });
    }

    private List<Slaughterhouse> loadOffline(){

        List<Slaughterhouse> slaughterhousesOff = null;

            try {
                File path = getFilesDir();
                File newDirectory = new File(path, "plantas");

                if(newDirectory.exists()) {
                    File archive = new File(newDirectory.getAbsolutePath(), animalInfo.name + ".dat");

                    if (archive.exists()) {
                        FileInputStream fileIn = new FileInputStream(archive);
                        ObjectInputStream reader = new ObjectInputStream(fileIn);
                        slaughterhousesOff = (List<Slaughterhouse>) reader.readObject();
                        reader.close();
                        fileIn.close();
                    }
                    else {
                            Toast alert = Toast.makeText(this, "Atención, no se han hallado plantas de beneficio para este animal.\n" +
                                "Por favor, conéctese a la red y carguelos por primera vez, luego de esto estarán disponibles" +
                                " en cualquier momento.", Toast.LENGTH_LONG);
                            alert.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                            alert.show();
                    }
                }

            }
            catch(FileNotFoundException e){}
            catch(IOException e){}
            catch (ClassNotFoundException e){}

        return slaughterhousesOff;
    }

    private void saveOffline(List<Slaughterhouse> list){

            try {
                File path = getFilesDir();
                File newDirectory = new File(path, "plantas");
                if(!newDirectory.exists()) {
                    newDirectory.mkdirs();
                }
                File archive = new File(newDirectory.getAbsolutePath(), animalInfo.name + ".dat");

                if (!archive.exists()) {
                    FileOutputStream fileOut = new FileOutputStream(archive);
                    ObjectOutputStream writer = new ObjectOutputStream(fileOut);
                    writer.writeObject(list);
                    writer.close();
                    fileOut.close();
                }
            }
            catch(FileNotFoundException e){}
            catch(IOException e){}

    }

    private void viewOffline(List<Slaughterhouse> list){
        final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
        searchBox = (EditText) findViewById(R.id.searchBox);

        slaughterhouseView = (ListView) findViewById(R.id.databaseListView);
        slaughterhouseView.setAdapter(new SlaughterhouseAdapter(SlaughterhouseActivity.this, list));

        searchBox.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        searchBox.setEnabled(true);
        progress.setVisibility(View.GONE);
        slaughterhouseView.setVisibility(View.VISIBLE);

    }

    public boolean checkConnection(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = manager.getActiveNetworkInfo();
        boolean state;

        if(netInfo != null && netInfo.isAvailable() && netInfo.isConnected()){
            state = true;
        }
        else{
            state = false;
        }
        return state;
    }

    private void fetchData() {
        final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);

        consumer = new Consumer(getString(R.string.api_url), getString(R.string.api_token));

        consumer.getObjects(getString(R.string.api_slaughterhouse_source), animalInfo.getSlaughterhouseQuery(), Slaughterhouse.class, new Callback<List<Slaughterhouse>>() {
            @Override
            public void onResults(Response<List<Slaughterhouse>> response) {
                slaughterhouses = response.getEntity();
                saveOffline(slaughterhouses);

                slaughterhouseView = (ListView) findViewById(R.id.databaseListView);
                slaughterhouseView.setAdapter(new SlaughterhouseAdapter(SlaughterhouseActivity.this, slaughterhouses));

                searchBox.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                searchBox.setEnabled(true);
                progress.setVisibility(View.GONE);
                slaughterhouseView.setVisibility(View.VISIBLE);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setFonts() {
        // Agregando fuentes
        typeFaces = new ArrayList<>();
        typeFaces.add(Typeface.createFromAsset(getAssets(), "fonts/blackjar.ttf"));
        typeFaces.add(Typeface.createFromAsset(getAssets(), "fonts/bloggersans.ttf"));

        // Mejorando vista del título
        TextView title = (TextView) findViewById(R.id.AppTitleTextView);
        title.setTypeface(typeFaces.get(0));

        TextView databaseTitle = (TextView) findViewById(R.id.columna1);
        databaseTitle.setText(R.string.slaughterhouseName);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) databaseTitle.getLayoutParams();
        lp.weight = 3f;
        databaseTitle.setLayoutParams(lp);
        // Mejorando vista del título de la base de datos
        databaseTitle.setTypeface(typeFaces.get(1));

        databaseTitle = (TextView) findViewById(R.id.columna2);
        databaseTitle.setText(R.string.slaughterhouseDepartament);
        // Mejorando vista del título de la base de datos
        databaseTitle.setTypeface(typeFaces.get(1));

        databaseTitle = (TextView) findViewById(R.id.columna3);
        databaseTitle.setText(R.string.slaughterhouseTown);
        lp = (LinearLayout.LayoutParams) databaseTitle.getLayoutParams();
        lp.weight = 2f;
        databaseTitle.setLayoutParams(lp);
        // Mejorando vista del título de la base de datos
        databaseTitle.setTypeface(typeFaces.get(1));
    }

    public void btnBack_click(View view) {
        finish();
    }


}
