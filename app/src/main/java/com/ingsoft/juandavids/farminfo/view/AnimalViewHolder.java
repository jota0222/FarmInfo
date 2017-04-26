package com.ingsoft.juandavids.farminfo.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingsoft.juandavids.farminfo.R;
import com.ingsoft.juandavids.farminfo.model.AnimalInfo;

public class AnimalViewHolder {
    public ImageView animalImage;
    public TextView animalName;
    public AnimalInfo animalInfo;

    public AnimalViewHolder(View v){
        this.animalImage = (ImageView) v.findViewById(R.id.animalImageView);
        this.animalName = (TextView) v.findViewById(R.id.animalTextView);
    }
}
