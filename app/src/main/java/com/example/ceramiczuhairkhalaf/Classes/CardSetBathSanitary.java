package com.example.ceramiczuhairkhalaf.Classes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CardSetBathSanitary implements Parcelable {
    private String name;
    private String size;
    private String price;
    private String madeIn;
    private String image;


    public CardSetBathSanitary() {
    }

    public CardSetBathSanitary(String name, String size, String price, String madeIn, String image) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.madeIn = madeIn;
        this.image = image;
    } protected CardSetBathSanitary(Parcel in){
        image = in.readString();
        name = in.readString();
    }
    public static final Creator<CardSetBathSanitary> CREATOR = new Creator<CardSetBathSanitary>() {
        @Override
        public CardSetBathSanitary createFromParcel(Parcel in) {return new CardSetBathSanitary(in);}

        @Override
        public CardSetBathSanitary[] newArray(int size) {return new CardSetBathSanitary[size];}
    };



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

    }
}
