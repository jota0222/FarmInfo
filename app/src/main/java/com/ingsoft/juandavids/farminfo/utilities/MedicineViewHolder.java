package com.ingsoft.juandavids.farminfo.utilities;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ingsoft.juandavids.farminfo.DTO.Medicine;
import com.ingsoft.juandavids.farminfo.MedicineActivity;
import com.ingsoft.juandavids.farminfo.R;

public class MedicineViewHolder {
    TextView productInfo;
    TextView type;
    TextView presentation;
    public Medicine medicine;

    MedicineViewHolder(View v){
        this.productInfo = (TextView) v.findViewById(R.id.lblMedicineInfo);
        this.type = (TextView) v.findViewById(R.id.lblMedicineType);
        this.presentation = (TextView) v.findViewById(R.id.lblMedicinePresentation);
    }

    public void setView(Medicine medicine, Context context) {
        this.medicine = medicine;

        productInfo.setText(medicine.getProductInfo());
        productInfo.setTypeface(((MedicineActivity)context).typeFaces.get(1));

        type.setText(medicine.getType());
        type.setTypeface(((MedicineActivity)context).typeFaces.get(1));

        presentation.setText(medicine.getPresentation());
        presentation.setTypeface(((MedicineActivity)context).typeFaces.get(1));
    }
}
