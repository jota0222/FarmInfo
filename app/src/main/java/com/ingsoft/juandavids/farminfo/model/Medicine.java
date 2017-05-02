package com.ingsoft.juandavids.farminfo.model;

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

    public String getProductInfo() {
        return productInfo;
    }

    public String getType() {
        return type;
    }

    public String getPresentation() {
        return presentation;
    }

    @Override
    public String toString(){
        return String.format(
                "Información: %s\n" +
                "Clase: %s\n" +
                "Presentación: %s\n", productInfo, type, presentation);
    }
}
