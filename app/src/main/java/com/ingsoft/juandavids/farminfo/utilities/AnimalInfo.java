package com.ingsoft.juandavids.farminfo.utilities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Creado por Juan David Hernández el 22/04/2017.
 */
public class AnimalInfo implements Serializable{
    int imageId;
    public String name;

    public AnimalInfo(int imageId, String name){
        this.imageId = imageId;
        this.name = name;
    }

}
