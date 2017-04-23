package com.ingsoft.juandavids.farminfo;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.ingsoft.juandavids.farminfo.utilities.AnimalAdapter;
import com.ingsoft.juandavids.farminfo.utilities.AnimalInfo;
import com.ingsoft.juandavids.farminfo.utilities.AnimalViewHolder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    GridView animalGrid;
    public ArrayList<Typeface> typeFaces;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFonts();

        Resources res = this.getResources();

        ArrayList<AnimalInfo> animalList = AnimalInfo.loadAnimals(res);

        animalGrid = (GridView) findViewById(R.id.animalGridView);
        animalGrid.setAdapter(new AnimalAdapter(this, animalList));
        animalGrid.setOnItemClickListener(this);

    }

    private void setFonts() {
        // Agregando fuentes
        typeFaces = new ArrayList<>();
        typeFaces.add(Typeface.createFromAsset(getAssets(),"fonts/blackjar.ttf"));
        typeFaces.add(Typeface.createFromAsset(getAssets(),"fonts/bloggersans.ttf"));

        // Mejorando vista del título
        TextView title = (TextView) findViewById(R.id.AppTitleTextView);
        title.setTypeface(typeFaces.get(0));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, AnimalActivity.class);
        AnimalViewHolder holder = (AnimalViewHolder) view.getTag();
        intent.putExtra("animalGroup", holder.animalInfo);
        startActivity(intent);
    }
}
