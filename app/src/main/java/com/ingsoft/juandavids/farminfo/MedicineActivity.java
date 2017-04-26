package com.ingsoft.juandavids.farminfo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ingsoft.juandavids.farminfo.model.Medicine;
import com.ingsoft.juandavids.farminfo.model.AnimalInfo;
import com.ingsoft.juandavids.farminfo.adapter.MedicineAdapter;
import com.socrata.android.client.Callback;
import com.socrata.android.client.Consumer;
import com.socrata.android.client.Response;

import java.util.ArrayList;
import java.util.List;

public class MedicineActivity extends AppCompatActivity {

    AnimalInfo animalInfo;
    public ArrayList<Typeface> typeFaces;
    Consumer consumer;
    List<Medicine> medicines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        Intent intent = getIntent();
        animalInfo = intent.getParcelableExtra("animalGroup");

        setFonts();

        fetchData();


        //TODO: implementar búsqued o filtros
    }

    private void fetchData() {
        final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);

        final Context self = this;

        consumer = new Consumer(getString(R.string.api_url), getString(R.string.api_token));

        consumer.getObjects(getString(R.string.api_medicine_source), animalInfo.getMedicineQuery(), Medicine.class, new Callback<List<Medicine>>() {
            @Override
            public void onResults(Response<List<Medicine>> response) {
                medicines = response.getEntity();

                ListView medicineView = (ListView) findViewById(R.id.databaseListView);
                medicineView.setAdapter(new MedicineAdapter(self, medicines));

                progress.setVisibility(View.GONE);
                medicineView.setVisibility(View.VISIBLE);
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
