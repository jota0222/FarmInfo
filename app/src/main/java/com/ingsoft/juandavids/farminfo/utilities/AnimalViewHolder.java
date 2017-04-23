package com.ingsoft.juandavids.farminfo.utilities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingsoft.juandavids.farminfo.R;

public class AnimalViewHolder {
    ImageView animalImage;
    TextView animalName;
    public AnimalInfo animalInfo;

    AnimalViewHolder(View v){
        this.animalImage = (ImageView) v.findViewById(R.id.animalImageView);
        this.animalName = (TextView) v.findViewById(R.id.animalTextView);
    }
}
