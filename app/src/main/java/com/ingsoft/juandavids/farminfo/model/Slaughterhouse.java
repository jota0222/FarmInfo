package com.ingsoft.juandavids.farminfo.model;

import com.socrata.android.client.SodaEntity;
import com.socrata.android.client.SodaField;

/**
 * Creado por Juan David Hernández el 26/04/2017.
 */

@SodaEntity
public class Slaughterhouse {
    @SodaField("razonsocial")
    private String name;

    @SodaField("departamento")
    private String departament;

    @SodaField("municipio")
    private String town;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public String toString(){
        return String.format(
                "Razon Social: %s\n" +
                "Departamentp: %s\n" +
                "Municipio: %s\n", name, departament, town);
    }
}
