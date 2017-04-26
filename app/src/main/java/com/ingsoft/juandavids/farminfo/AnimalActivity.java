package com.ingsoft.juandavids.farminfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ingsoft.juandavids.farminfo.utilities.AnimalInfo;

import java.util.ArrayList;

public class AnimalActivity extends AppCompatActivity {

    public ArrayList<Typeface> typeFaces;
    AnimalInfo animalInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        Intent intent = getIntent();
        animalInfo = intent.getParcelableExtra("animalGroup");

        setFonts();
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

        TextView groupTitle = (TextView) findViewById(R.id.selectedGroupTextView);
        groupTitle.setText(animalInfo.name + ":");
        // Mejorando vista del título de grupo
        groupTitle.setTypeface(typeFaces.get(1));

        Button btnMedicine = (Button) findViewById(R.id.btnMedicine);
        btnMedicine.setTypeface(typeFaces.get(1));

        Button btnSlaughterhouse = (Button) findViewById(R.id.btnSlaughterhouse);
        btnSlaughterhouse.setTypeface(typeFaces.get(1));
    }

    public void btnBack_click(View view) {
        finish();
    }

    public void btnMedicine_click(View view) {
        Intent intent = new Intent(this, MedicineActivity.class);
        intent.putExtra("animalGroup", animalInfo);
        intent.putExtra("dataBase", getString(R.string.medicine));
        startActivity(intent);
    }

    public void btnSlaughterhouse_click(View view) {
        Intent intent = new Intent(this, MedicineActivity.class);
        intent.putExtra("animalGroup", animalInfo);
        intent.putExtra("dataBase", getString(R.string.slaughterhouses));
        startActivity(intent);
    }
}
