package com.ingsoft.juandavids.farminfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ingsoft.juandavids.farminfo.DTO.Medicine;
import com.ingsoft.juandavids.farminfo.utilities.AnimalInfo;
import com.socrata.android.client.Callback;
import com.socrata.android.client.Consumer;
import com.socrata.android.client.Response;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    String dataBase;
    AnimalInfo animalInfo;
    ArrayList<Typeface> typeFaces;
    Consumer consumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        Intent intent = getIntent();
        dataBase = intent.getStringExtra("dataBase");
        animalInfo = intent.getParcelableExtra("animalGroup");

        setFonts();

        /*
        *TODO: llamada a la API de SOCRATA si dataBase == @string/medicine se busca en datos abiertos de animales
        * sino se busca en plantas de beneficio animal.
        * Se debe crear una tabla para mostrar la información adquirida
        */
        consumer = new Consumer("www.datos.gov.co", "6HI9A3324vqb88v5VbG2ouyGm");

        consumer.getObjects("r6g7-nmt3.json", "select * where especie = 'Bovinos'" , Medicine.class, new Callback<List<Medicine>>() {
            @Override
            public void onResults(Response<List<Medicine>> response) {
                List<Medicine> medicines = response.getEntity();
                //do somethings with earthquake
                Log.d("PASSSSSSSSSSSSSSSSSSSSS", "");
            }
        });


        //TODO: implementar búsqued o filtros
    }

    @SuppressLint("SetTextI18n")
    private void setFonts() {
        // Agregando fuentes
        typeFaces = new ArrayList<>();
        typeFaces.add(Typeface.createFromAsset(getAssets(),"fonts/blackjar.ttf"));
        typeFaces.add(Typeface.createFromAsset(getAssets(),"fonts/bloggersans.ttf"));

        // Mejorando vista del título
        TextView title = (TextView) findViewById(R.id.AppTitleTextView);
        title.setTypeface(typeFaces.get(0));

        TextView databaseTitle = (TextView) findViewById(R.id.databaseTextView);
        databaseTitle.setText(dataBase + ":");
        // Mejorando vista del título de la base de datos
        databaseTitle.setTypeface(typeFaces.get(1));
    }

    public void btnBack_click(View view) {
        finish();
    }
}
