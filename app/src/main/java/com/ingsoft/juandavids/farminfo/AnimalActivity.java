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

public class AnimalActivity extends AppCompatActivity {

    public ArrayList<Typeface> typeFaces;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        // Agregando fuentes
        typeFaces = new ArrayList<>();
        typeFaces.add(Typeface.createFromAsset(getAssets(),"fonts/blackjar.ttf"));
        typeFaces.add(Typeface.createFromAsset(getAssets(),"fonts/bloggersans.ttf"));

        // Mejorando vista del título
        TextView title = (TextView) findViewById(R.id.AppTitleTextView);
        title.setTypeface(typeFaces.get(0));

        Intent intent = getIntent();
        AnimalInfo animalInfo = intent.getParcelableExtra("animalGroup");

        TextView groupTitle = (TextView) findViewById(R.id.selectedGroupTextView);
        groupTitle.setText(animalInfo.name + ":");
        // Mejorando vista del título de grupo
        groupTitle.setTypeface(typeFaces.get(1));
    }

    public void btnBack_click(View view) {
        finish();
    }
}
