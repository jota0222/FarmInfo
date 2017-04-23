package com.ingsoft.juandavids.farminfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ingsoft.juandavids.farminfo.utilities.AnimalInfo;

public class AnimalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        Intent intent = getIntent();
        AnimalInfo animalInfo = (AnimalInfo) intent.getSerializableExtra("animalGroup");

        TextView groupTitle = (TextView) findViewById(R.id.selectedGroupTextView);
        groupTitle.setText(animalInfo.name);
    }
}
