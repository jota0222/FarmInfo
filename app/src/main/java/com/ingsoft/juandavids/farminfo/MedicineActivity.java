package com.ingsoft.juandavids.farminfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ingsoft.juandavids.farminfo.adapter.MedicineAdapter;
import com.ingsoft.juandavids.farminfo.model.AnimalInfo;
import com.ingsoft.juandavids.farminfo.model.Medicine;
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

public class MedicineActivity extends AppCompatActivity {

    Consumer consumer;

    AnimalInfo animalInfo;
    List<Medicine> medicines;
    List<Medicine> filteredMedicines;
    ListView medicineView;
    List<Medicine> medicineOff;
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
        else {
            medicineOff = loadOffline();
            if (medicineOff != null){
                viewOffline(medicineOff);
                searchOff();
            }

        }

    }

    public void setSearch() {

        searchBox = (EditText) findViewById(R.id.searchBox);
        filteredMedicines = new ArrayList<>();

        searchBox.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (medicines != null && medicines.size() != 0) {
                    String searchText = s.toString().toLowerCase();

                    filteredMedicines.clear();

                    for (int i = 0; i < medicines.size(); i++) {
                        Medicine item = medicines.get(i);
                        if (item.getProductInfo().toLowerCase().contains(searchText)
                                || item.getType().toLowerCase().contains(searchText)
                                || item.getPresentation().toLowerCase().contains(searchText)) {
                            filteredMedicines.add(item);
                        }
                    }

                    medicineView.setAdapter(new MedicineAdapter(MedicineActivity.this, filteredMedicines));
                }
            }
        });
    }

    private void searchOff() {

        searchBox = (EditText) findViewById(R.id.searchBox);
        filteredMedicines = new ArrayList<>();

        searchBox.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (medicineOff != null && medicineOff.size() != 0) {
                    String searchText = s.toString().toLowerCase();

                    filteredMedicines.clear();

                    for (int i = 0; i < medicineOff.size(); i++) {
                        Medicine item = medicineOff.get(i);
                        if (item.getProductInfo().toLowerCase().contains(searchText)
                                || item.getType().toLowerCase().contains(searchText)
                                || item.getPresentation().toLowerCase().contains(searchText)) {
                            filteredMedicines.add(item);
                        }
                    }

                    medicineView.setAdapter(new MedicineAdapter(MedicineActivity.this, filteredMedicines));
                }
            }
        });
    }

    private void fetchData() {
        final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);

        consumer = new Consumer(getString(R.string.api_url), getString(R.string.api_token));

        consumer.getObjects(getString(R.string.api_medicine_source), animalInfo.getMedicineQuery(), Medicine.class, new Callback<List<Medicine>>() {
            @Override
            public void onResults(Response<List<Medicine>> response) {
                medicines = response.getEntity();
                saveOffline(medicines);

                medicineView = (ListView) findViewById(R.id.databaseListView);
                medicineView.setAdapter(new MedicineAdapter(MedicineActivity.this, medicines));

                searchBox.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                searchBox.setEnabled(true);
                progress.setVisibility(View.GONE);
                medicineView.setVisibility(View.VISIBLE);
            }
        });
    }

    private List<Medicine> loadOffline(){

        List<Medicine> medicinesOff = null;

            try {
                File path = getFilesDir();
                File newDirectory = new File(path, "medicamentos");

                if (newDirectory.exists()) {
                    File archive = new File(newDirectory.getAbsolutePath(), animalInfo.name + ".dat");

                    if (archive.exists()) {
                        FileInputStream fileIn = new FileInputStream(archive);
                        ObjectInputStream reader = new ObjectInputStream(fileIn);
                        medicinesOff = (List<Medicine>) reader.readObject();
                        reader.close();
                        fileIn.close();

                    }
                     else {
                            Toast alert = Toast.makeText(this, "Atención, no se han hallado medicinas para este animal.\n" +
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

        return medicinesOff;
    }

    private void saveOffline(List<Medicine> list){

            try {
                File path = getFilesDir();
                File newDirectory = new File(path, "medicamentos");
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

    private void viewOffline(List<Medicine> list){
        final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
        searchBox = (EditText) findViewById(R.id.searchBox);

        medicineView = (ListView) findViewById(R.id.databaseListView);
        medicineView.setAdapter(new MedicineAdapter(MedicineActivity.this, list));

        searchBox.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        searchBox.setEnabled(true);
        progress.setVisibility(View.GONE);
        medicineView.setVisibility(View.VISIBLE);

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
        // Mejorando vista del título de la base de datos
        databaseTitle.setTypeface(typeFaces.get(1));

        databaseTitle = (TextView) findViewById(R.id.columna2);
        // Mejorando vista del título de la base de datos
        databaseTitle.setTypeface(typeFaces.get(1));

        databaseTitle = (TextView) findViewById(R.id.columna3);
        // Mejorando vista del título de la base de datos
        databaseTitle.setTypeface(typeFaces.get(1));
    }

    public void btnBack_click(View view) {
        finish();
    }



}
