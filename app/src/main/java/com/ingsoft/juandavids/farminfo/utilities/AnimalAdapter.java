package com.ingsoft.juandavids.farminfo.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ingsoft.juandavids.farminfo.R;

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
        ViewHolder holder;
        if (cell == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = inflater.inflate(R.layout.animal_icon, viewGroup, false);
            holder = new ViewHolder(cell);
            cell.setTag(holder);

        } else {
            holder = (ViewHolder) cell.getTag();
        }

        AnimalInfo animal = animalList.get(i);
        holder.animalInfo = animal;
        holder.animalImage.setImageResource(animal.imageId);
        holder.animalName.setText(animal.name);

        return cell;
    }
}

