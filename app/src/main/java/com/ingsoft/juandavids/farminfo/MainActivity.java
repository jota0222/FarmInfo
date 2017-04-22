package com.ingsoft.juandavids.farminfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.ingsoft.juandavids.farminfo.utilities.AnimalAdapter;
import com.ingsoft.juandavids.farminfo.utilities.AnimalInfo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView animalGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<AnimalInfo> animalList = new ArrayList<>();

        animalList.add(new AnimalInfo(R.drawable.bovinos, "Bovinos"));
        animalList.add(new AnimalInfo(R.drawable.aves, "Aves"));
        animalList.add(new AnimalInfo(R.drawable.caninos, "Caninos"));
        animalList.add(new AnimalInfo(R.drawable.porcinos, "Porcinos"));

        animalGrid = (GridView) findViewById(R.id.animalGridView);
        animalGrid.setAdapter(new AnimalAdapter(this, animalList));
    }
}
