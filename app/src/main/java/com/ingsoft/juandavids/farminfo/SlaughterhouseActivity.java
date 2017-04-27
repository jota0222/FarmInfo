package com.ingsoft.juandavids.farminfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ingsoft.juandavids.farminfo.adapter.SlaughterhouseAdapter;
import com.ingsoft.juandavids.farminfo.model.AnimalInfo;
import com.ingsoft.juandavids.farminfo.model.Slaughterhouse;
import com.socrata.android.client.Callback;
import com.socrata.android.client.Consumer;
import com.socrata.android.client.Response;

import java.util.ArrayList;
import java.util.List;

public class SlaughterhouseActivity extends AppCompatActivity {

    Consumer consumer;

    AnimalInfo animalInfo;
    List<Slaughterhouse> slaughterhouses;
    List<Slaughterhouse> filteredSlaughterhouse;
    ListView slaughterhouseView;

    EditText searchBox;
    public ArrayList<Typeface> typeFaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        Intent intent = getIntent();
        animalInfo = intent.getParcelableExtra("animalGroup");

        setFonts();

        fetchData();

        setSearch();
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

    private void fetchData() {
        final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);

        consumer = new Consumer(getString(R.string.api_url), getString(R.string.api_token));

        consumer.getObjects(getString(R.string.api_slaughterhouse_source), animalInfo.getSlaughterhouseQuery(), Slaughterhouse.class, new Callback<List<Slaughterhouse>>() {
            @Override
            public void onResults(Response<List<Slaughterhouse>> response) {
                slaughterhouses = response.getEntity();

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
