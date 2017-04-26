package com.ingsoft.juandavids.farminfo.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ingsoft.juandavids.farminfo.model.Medicine;
import com.ingsoft.juandavids.farminfo.MedicineActivity;
import com.ingsoft.juandavids.farminfo.R;

public class MedicineViewHolder {
    private TextView productInfo;
    private TextView type;
    private TextView presentation;

    public MedicineViewHolder(View v){
        this.productInfo = (TextView) v.findViewById(R.id.lblMedicineInfo);
        this.type = (TextView) v.findViewById(R.id.lblMedicineType);
        this.presentation = (TextView) v.findViewById(R.id.lblMedicinePresentation);
    }

    public void setView(Medicine medicine, Context context) {

        productInfo.setText(medicine.getProductInfo());
        productInfo.setTypeface(((MedicineActivity)context).typeFaces.get(1));

        type.setText(medicine.getType());
        type.setTypeface(((MedicineActivity)context).typeFaces.get(1));

        presentation.setText(medicine.getPresentation());
        presentation.setTypeface(((MedicineActivity)context).typeFaces.get(1));
    }
}
