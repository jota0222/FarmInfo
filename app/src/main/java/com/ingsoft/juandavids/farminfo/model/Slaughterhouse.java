package com.ingsoft.juandavids.farminfo.model;

import com.socrata.android.client.SodaEntity;
import com.socrata.android.client.SodaField;

import java.io.Serializable;

/**
 * Creado por Juan David Hernández el 26/04/2017.
 */

@SodaEntity
public class Slaughterhouse implements Serializable{
    @SodaField("razonsocial")
    private String name;

    @SodaField("departamento")
    private String departament;

    @SodaField("municipio")
    private String town;

    public String getName() {
        return name;
    }

    public String getDepartament() {
        return departament;
    }

    public String getTown() {
        return town;
    }

    @Override
    public String toString(){
        return String.format(
                "Razon Social: %s\n" +
                "Departamentp: %s\n" +
                "Municipio: %s\n", name, departament, town);
    }
}
