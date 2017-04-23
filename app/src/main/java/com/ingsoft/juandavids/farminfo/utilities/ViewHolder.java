package com.ingsoft.juandavids.farminfo.utilities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingsoft.juandavids.farminfo.R;

public class ViewHolder{
    ImageView animalImage;
    TextView animalName;
    public AnimalInfo animalInfo;

    ViewHolder(View v){
        this.animalImage = (ImageView) v.findViewById(R.id.animalImageView);
        this.animalName = (TextView) v.findViewById(R.id.animalTextView);
    }
}
