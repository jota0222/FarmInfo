package com.ingsoft.juandavids.farminfo.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ingsoft.juandavids.farminfo.model.Slaughterhouse;
import com.ingsoft.juandavids.farminfo.R;
import com.ingsoft.juandavids.farminfo.SlaughterhouseActivity;

public class SlaughterhouseViewHolder {
    private TextView productInfo;
    private TextView type;
    private TextView presentation;

    public SlaughterhouseViewHolder(View v){
        this.productInfo = (TextView) v.findViewById(R.id.lblSlaughterhouseName);
        this.type = (TextView) v.findViewById(R.id.lblSlaughterhouseDepartament);
        this.presentation = (TextView) v.findViewById(R.id.lblSlaughterhouseTown);
    }

    public void setView(Slaughterhouse slaughterhouse, Context context) {

        productInfo.setText(slaughterhouse.getName());
        productInfo.setTypeface(((SlaughterhouseActivity)context).typeFaces.get(1));

        type.setText(slaughterhouse.getDepartament());
        type.setTypeface(((SlaughterhouseActivity)context).typeFaces.get(1));

        presentation.setText(slaughterhouse.getTown());
        presentation.setTypeface(((SlaughterhouseActivity)context).typeFaces.get(1));
    }
}
