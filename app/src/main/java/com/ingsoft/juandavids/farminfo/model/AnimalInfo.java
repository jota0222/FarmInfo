package com.ingsoft.juandavids.farminfo.model;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import com.ingsoft.juandavids.farminfo.R;

import java.util.ArrayList;

/**
 * Creado por Juan David Hernández el 22/04/2017.
 */
public class AnimalInfo implements Parcelable {
    public int imageId;
    public String name;
    public ArrayList<String> animalTypesInBD;

    private AnimalInfo(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
        this.animalTypesInBD = new ArrayList<>();
        this.animalTypesInBD.add("Mixto");
        this.animalTypesInBD.add("Acondicionador");
    }

    private AnimalInfo(Parcel source) {
        this.imageId = source.readInt();
        this.name = source.readString();
        this.animalTypesInBD = source.createStringArrayList();
    }

    public static final Creator<AnimalInfo> CREATOR = new Creator<AnimalInfo>() {
        @Override
        public AnimalInfo createFromParcel(Parcel in) {
            return new AnimalInfo(in);
        }

        @Override
        public AnimalInfo[] newArray(int size) {
            return new AnimalInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.imageId);
        parcel.writeString(this.name);
        parcel.writeStringList(this.animalTypesInBD);
    }

    /*
     * Método para llenar la lista de grupos de animales. Los cambios en los grupos se realizarán desde aquí.
     */
    public static ArrayList<AnimalInfo> loadAnimals(Resources res) {
        String[] animalStrings = res.getStringArray(R.array.animal_names);
        ArrayList<AnimalInfo> animalList = new ArrayList<>();

        AnimalInfo animal = new AnimalInfo(R.drawable.aves, animalStrings[0]);
        animal.animalTypesInBD.add("Ave");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.ornamentales, animalStrings[7]);
        animal.animalTypesInBD.add("Aves ornamentales");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.bovinos, animalStrings[1]);
        animal.animalTypesInBD.add("Bovino");
        animal.animalTypesInBD.add("Ternera");
        animal.animalTypesInBD.add("Ternero");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.caninos, animalStrings[2]);
        animal.animalTypesInBD.add("Canino");
        animal.animalTypesInBD.add("Cachorro");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.caprinos, animalStrings[3]);
        animal.animalTypesInBD.add("Caprino");
        animal.animalTypesInBD.add("Ovicaprino");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.conejos, animalStrings[4]);
        animal.animalTypesInBD.add("Conejo");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.equinos, animalStrings[5]);
        animal.animalTypesInBD.add("Equino");
        animal.animalTypesInBD.add("Potro");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.felinos, animalStrings[6]);
        animal.animalTypesInBD.add("Felino");
        animal.animalTypesInBD.add("Pequeños Felinos");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.ovinos, animalStrings[8]);
        animal.animalTypesInBD.add("Ovino");
        animal.animalTypesInBD.add("Borrego");
        animal.animalTypesInBD.add("Ovicaprino");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.porcinos, animalStrings[9]);
        animal.animalTypesInBD.add("Porcino");
        animal.animalTypesInBD.add("Lechon");
        animalList.add(animal);

        return animalList;
    }

    public String getMedicineQuery() {
        String query = "select %s where %s";
        String select = "descripci_n_producto, clase, presentaci_n";
        String where = "especie like '%" + this.animalTypesInBD.get(0) + "%'";
        for (int i = 1; i < this.animalTypesInBD.size(); i++) {
            if (this.animalTypesInBD.get(i).equals("Ave")) {
                where += " OR especie = 'Aves'";
            } else {
                where += " OR especie like '%" + this.animalTypesInBD.get(i) + "%'";
            }
        }

        return String.format(query, select, where);
    }

    public String getSlaughterhouseQuery() {
        String query = "select %s where %s";
        String select = "razonsocial, departamento, municipio";
        String where = "(especie like '%" + this.animalTypesInBD.get(0).toUpperCase() + "%'";
        for (int i = 1; i < this.animalTypesInBD.size(); i++) {
            where += " OR especie like '%" + this.animalTypesInBD.get(i).toUpperCase() + "%'";
        }
        where += ") AND (estadoactual like '%ABIERTA%' OR estadoactual like '%VISITA%')";
        return String.format(query, select, where);
    }
}
