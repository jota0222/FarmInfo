package com.ingsoft.juandavids.farminfo.utilities;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import com.ingsoft.juandavids.farminfo.R;

import java.util.ArrayList;

/**
 * Creado por Juan David Hernández el 22/04/2017.
 */
public class AnimalInfo implements Parcelable{
    int imageId;
    public String name;
    private ArrayList<String> animalTypesInBD;

    private AnimalInfo(int imageId, String name){
        this.imageId = imageId;
        this.name = name;
        this.animalTypesInBD = new ArrayList<>();
        this.animalTypesInBD.add("Mixto");
    }

    private AnimalInfo(Parcel source){
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
        animal.animalTypesInBD.add("Aves");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.ornamentales, animalStrings[7]);
        animal.animalTypesInBD.add("Aves ornamentales");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.bovinos, animalStrings[1]);
        animal.animalTypesInBD.add("Bovinos");
        animal.animalTypesInBD.add("Terneras");
        animal.animalTypesInBD.add("Terneros");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.caninos, animalStrings[2]);
        animal.animalTypesInBD.add("Cachorros");
        animal.animalTypesInBD.add("Caninos");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.caprinos, animalStrings[3]);
        animal.animalTypesInBD.add("Caprinos");
        animal.animalTypesInBD.add("Ovicaprinos");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.conejos, animalStrings[4]);
        animal.animalTypesInBD.add("Conejos");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.equinos, animalStrings[5]);
        animal.animalTypesInBD.add("Equinos");
        animal.animalTypesInBD.add("Potros");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.felinos, animalStrings[6]);
        animal.animalTypesInBD.add("Felinos");
        animal.animalTypesInBD.add("Pequeños Felinos");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.ovinos, animalStrings[8]);
        animal.animalTypesInBD.add("Borregos");
        animal.animalTypesInBD.add("Ovicaprinos");
        animal.animalTypesInBD.add("Ovinos");
        animalList.add(animal);

        animal = new AnimalInfo(R.drawable.porcinos, animalStrings[9]);
        animal.animalTypesInBD.add("Lechones");
        animal.animalTypesInBD.add("Porcinos");
        animalList.add(animal);

        return animalList;
    }
}
