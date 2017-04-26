package com.ingsoft.juandavids.farminfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ingsoft.juandavids.farminfo.model.Slaughterhouse;
import com.ingsoft.juandavids.farminfo.R;
import com.ingsoft.juandavids.farminfo.view.SlaughterhouseViewHolder;

import java.util.List;

/**
 * Creado por Juan David Hernández el 22/04/2017.
 */

public class SlaughterhouseAdapter extends BaseAdapter {

    private List<Slaughterhouse> database;
    private Context context;

    public SlaughterhouseAdapter(Context context, List<Slaughterhouse> database){
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
        SlaughterhouseViewHolder holder;
        if (row == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.slaughterhouse_item, viewGroup, false);
            holder = new SlaughterhouseViewHolder(row);
            row.setTag(holder);

        } else {
            holder = (SlaughterhouseViewHolder) row.getTag();
        }

        Slaughterhouse item = database.get(i);
        holder.setView(item, context);


        return row;
    }
}

