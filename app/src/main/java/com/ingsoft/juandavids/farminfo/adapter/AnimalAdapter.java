package com.ingsoft.juandavids.farminfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ingsoft.juandavids.farminfo.MainActivity;
import com.ingsoft.juandavids.farminfo.R;
import com.ingsoft.juandavids.farminfo.model.AnimalInfo;
import com.ingsoft.juandavids.farminfo.view.AnimalViewHolder;

import java.util.ArrayList;

/**
 * Creado por Juan David Hernández el 22/04/2017.
 */

public class AnimalAdapter extends BaseAdapter {

    private ArrayList<AnimalInfo> animalList;
    private Context context;

    public AnimalAdapter(Context context, ArrayList<AnimalInfo> animals){
        this.context = context;
        animalList = animals;
    }

    @Override
    public int getCount() {
        return animalList.size();
    }

    @Override
    public Object getItem(int i) {
        return animalList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View cell = view;
        AnimalViewHolder holder;
        if (cell == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = inflater.inflate(R.layout.animal_icon, viewGroup, false);
            holder = new AnimalViewHolder(cell);
            cell.setTag(holder);

        } else {
            holder = (AnimalViewHolder) cell.getTag();
        }

        AnimalInfo animal = animalList.get(i);
        holder.animalInfo = animal;
        holder.animalImage.setImageResource(animal.imageId);
        holder.animalName.setText(animal.name);
        holder.animalName.setTypeface(((MainActivity)context).typeFaces.get(1));

        return cell;
    }
}

