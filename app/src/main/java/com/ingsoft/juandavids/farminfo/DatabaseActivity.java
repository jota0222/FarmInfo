package com.ingsoft.juandavids.farminfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ingsoft.juandavids.farminfo.utilities.AnimalInfo;

import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {

    String dataBase;
    AnimalInfo animalInfo;
    ArrayList<Typeface> typeFaces;

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
