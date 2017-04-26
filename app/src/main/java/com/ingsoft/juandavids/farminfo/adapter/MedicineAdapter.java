package com.ingsoft.juandavids.farminfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ingsoft.juandavids.farminfo.model.Medicine;
import com.ingsoft.juandavids.farminfo.R;
import com.ingsoft.juandavids.farminfo.view.MedicineViewHolder;

import java.util.List;

/**
 * Creado por Juan David Hernández el 22/04/2017.
 */

public class MedicineAdapter extends BaseAdapter {

    private List<Medicine> database;
    private Context context;

    public MedicineAdapter(Context context, List<Medicine> database){
        this.context = context;
        this.database = database;
    }

    @Override
    public int getCount() {
        return database.size();
    }

    @Override
    public Object getItem(int i) {
        return database.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        MedicineViewHolder holder;
        if (row == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.medicine_item, viewGroup, false);
            holder = new MedicineViewHolder(row);
            row.setTag(holder);

        } else {
            holder = (MedicineViewHolder) row.getTag();
        }

        Medicine item = database.get(i);
        holder.setView(item, context);


        return row;
    }
}

