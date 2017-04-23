package com.ingsoft.juandavids.farminfo;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ingsoft.juandavids.farminfo.utilities.AnimalAdapter;
import com.ingsoft.juandavids.farminfo.utilities.AnimalInfo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    GridView animalGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = this.getResources();
        String[] animalStrings = res.getStringArray(R.array.animal_names);

        ArrayList<AnimalInfo> animalList = new ArrayList<>();

        animalList.add(new AnimalInfo(R.drawable.aves, animalStrings[0]));
        animalList.add(new AnimalInfo(R.drawable.ornamentales, animalStrings[7]));
        animalList.add(new AnimalInfo(R.drawable.bovinos, animalStrings[1]));
        animalList.add(new AnimalInfo(R.drawable.caninos, animalStrings[2]));
        animalList.add(new AnimalInfo(R.drawable.caprinos, animalStrings[3]));
        animalList.add(new AnimalInfo(R.drawable.conejos, animalStrings[4]));
        animalList.add(new AnimalInfo(R.drawable.equinos, animalStrings[5]));
        animalList.add(new AnimalInfo(R.drawable.felinos, animalStrings[6]));
        animalList.add(new AnimalInfo(R.drawable.ovinos, animalStrings[8]));
        animalList.add(new AnimalInfo(R.drawable.porcinos, animalStrings[9]));

        animalGrid = (GridView) findViewById(R.id.animalGridView);
        animalGrid.setAdapter(new AnimalAdapter(this, animalList));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
