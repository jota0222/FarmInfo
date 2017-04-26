package com.ingsoft.juandavids.farminfo.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ingsoft.juandavids.farminfo.R;
import com.ingsoft.juandavids.farminfo.model.Medicine;
import com.socrata.android.ui.list.BindableView;
import com.socrata.android.ui.list.SodaHolder;

/**
 * Creado por JuanDavidHernández el 25/04/2017.
 */

@SodaHolder(layout = "medicine_item")
public class MedicineView implements BindableView<Medicine>{

    private TextView productInfo;

    private TextView type;

    private TextView presentation;

    @Override
    public void createViewHolder(View convertView) {
        productInfo = (TextView) convertView.findViewById(R.id.lblMedicineInfo);
        type = (TextView) convertView.findViewById(R.id.lblMedicineType);
        presentation = (TextView) convertView.findViewById(R.id.lblMedicinePresentation);
    }

    @Override
    public void bindView(Medicine item, int position, View convertView, ViewGroup parent) {
        productInfo.setText(item.getProductInfo());
        type.setText(item.getType());
        presentation.setText(item.getPresentation());
    }
}
