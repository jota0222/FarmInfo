package com.ingsoft.juandavids.farminfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.ingsoft.juandavids.farminfo.fragments.DatabaseFragment;
import com.ingsoft.juandavids.farminfo.model.Medicine;
import com.ingsoft.juandavids.farminfo.utilities.AnimalInfo;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity extends FragmentActivity {

    AnimalInfo animalInfo;
    ArrayList<Typeface> typeFaces;
    List<Medicine> medicines;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        Intent intent = getIntent();
        animalInfo = intent.getParcelableExtra("animalGroup");

        setFonts();

//        /*
//        * TODO: llamada a la API de SOCRATA si dataBase == @string/medicine se busca en datos abiertos de animales
//        * sino se busca en plantas de beneficio animal.
//        * Se debe crear una tabla para mostrar la información adquirida
//        */
        FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
        DatabaseFragment fragment = new DatabaseFragment();
        fragment.animalInfo = animalInfo;
        t.replace(R.id.tableDatabase, fragment);
        t.commit();
//
//        consumer.getObjects("r6g7-nmt3.json", "select * where especie = 'Bovinos'", Medicine.class, new Callback<List<Medicine>>() {
//            @Override
//            public void onResults(Response<List<Medicine>> response) {
//                medicines = response.getEntity();
//                //do somethings with earthquake
//                Log.i("farminfo", medicines.get(0).toString());
//            }
//        });


        //TODO: implementar búsqued o filtros
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

        TextView databaseTitle = (TextView) findViewById(R.id.databaseTextView);
        databaseTitle.setText(getString(R.string.medicine) + ":");

        // Mejorando vista del título de la base de datos
        databaseTitle.setTypeface(typeFaces.get(1));
    }

    public void btnBack_click(View view) {
        finish();
    }
}
