package com.ingsoft.juandavids.farminfo.model;

import android.util.Log;

import com.socrata.android.client.SodaEntity;
import com.socrata.android.client.SodaField;

import java.io.Serializable;

/**
 * Creado por Juan David Hernández el 23/04/2017.
 */
@SodaEntity
public class Medicine implements Serializable{

    @SodaField("descripci_n_producto")
    private String productInfo;

    @SodaField("clase")
    private String type;

    @SodaField("presentaci_n")
    private String presentation;

    public Medicine() {
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    @Override
    public String toString(){
        return String.format(
                "Información: %s\n" +
                "Clase: %s\n" +
                "Presentación: %s\n", productInfo, type, presentation);
    }
}
