package com.ingsoft.juandavids.farminfo.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.ingsoft.juandavids.farminfo.R;
import com.ingsoft.juandavids.farminfo.model.Medicine;
import com.ingsoft.juandavids.farminfo.utilities.AnimalInfo;
import com.ingsoft.juandavids.farminfo.view.MedicineView;
import com.socrata.android.client.Consumer;
import com.socrata.android.soql.Query;
import com.socrata.android.ui.list.SodaListFragment;

import static com.socrata.android.soql.clauses.Expression.like;

public class DatabaseFragment extends SodaListFragment<MedicineView, Medicine> {

    public AnimalInfo animalInfo;
    Consumer consumer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        consumer = new Consumer(getString(R.string.api_url), getString(R.string.api_token));
        super.onCreate(savedInstanceState);
    }

    @Override
    public Consumer getConsumer() {
        return consumer;
    }

    @Override
    public Query getQuery() {
        Query q = new Query("r6g7-nmt3.json", Medicine.class);
        for (String item : animalInfo.animalTypesInBD) {
            q.addWhere(like("especie", String.format("'%%25%s%%25'", item)));
        }
        return q;
    }

    @Override
    public void onItemClick(AdapterView adapterView, View view, int position, long id) {

    }
}
