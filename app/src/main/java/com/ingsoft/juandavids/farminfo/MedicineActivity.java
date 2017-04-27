package com.ingsoft.juandavids.farminfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ingsoft.juandavids.farminfo.adapter.MedicineAdapter;
import com.ingsoft.juandavids.farminfo.model.AnimalInfo;
import com.ingsoft.juandavids.farminfo.model.Medicine;
import com.socrata.android.client.Callback;
import com.socrata.android.client.Consumer;
import com.socrata.android.client.Response;

import java.util.ArrayList;
import java.util.List;

public class MedicineActivity extends AppCompatActivity {

    Consumer consumer;

    AnimalInfo animalInfo;
    List<Medicine> medicines;
    List<Medicine> filteredMedicines;
    ListView medicineView;

    EditText searchBox;

    public ArrayList<Typeface> typeFaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        Intent intent = getIntent();
        animalInfo = intent.getParcelableExtra("animalGroup");

        setFonts();

        fetchData();

        setSearch();
    }

    private void setSearch() {
        searchBox = (EditText) findViewById(R.id.searchBox);
        filteredMedicines = new ArrayList<>();

        searchBox.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (medicines != null && medicines.size() != 0) {
                    String searchText = s.toString().toLowerCase();

                    filteredMedicines.clear();

                    for (int i = 0; i < medicines.size(); i++) {
                        Medicine item = medicines.get(i);
                        if (item.getProductInfo().toLowerCase().contains(searchText)
                                || item.getType().toLowerCase().contains(searchText)
                                || item.getPresentation().toLowerCase().contains(searchText)) {
                            filteredMedicines.add(item);
                        }
                    }

                    medicineView.setAdapter(new MedicineAdapter(MedicineActivity.this, filteredMedicines));
                }
            }
        });
    }

    private void fetchData() {
        final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);

        consumer = new Consumer(getString(R.string.api_url), getString(R.string.api_token));

        consumer.getObjects(getString(R.string.api_medicine_source), animalInfo.getMedicineQuery(), Medicine.class, new Callback<List<Medicine>>() {
            @Override
            public void onResults(Response<List<Medicine>> response) {
                medicines = response.getEntity();

                medicineView = (ListView) findViewById(R.id.databaseListView);
                medicineView.setAdapter(new MedicineAdapter(MedicineActivity.this, medicines));

                searchBox.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                searchBox.setEnabled(true);
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
