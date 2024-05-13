package com.example.ceramiczuhairkhalaf.Classes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CardSet implements Parcelable {
    private String styleImage;
    private String productName;
    private String size;
    private String price;
    private String madeIn;
    private String company;
    private String designedIn;
    private String polishedOrMatt;
    private String image;

    public CardSet() {
    }

    public CardSet(String styleImage, String productName, String size, String price, String madeIn, String company, String designedIn, String polishedOrMatt, String image) {
        this.styleImage = styleImage;
        this.productName = productName;
        this.size = size;
        this.price = price;
        this.madeIn = madeIn;
        this.company = company;
        this.designedIn = designedIn;
        this.polishedOrMatt = polishedOrMatt;
        this.image = image;
    }

    protected CardSet(Parcel in){
        styleImage = in.readString();
        productName = in.readString();
    }
    public static final Creator<CardSet> CREATOR = new Creator<CardSet>() {
        @Override
        public CardSet createFromParcel(Parcel in) {return new CardSet(in);}

        @Override
        public CardSet[] newArray(int size) {return new CardSet[size];}
    };

    public String getStyleImage() {
        return styleImage;
    }

    public void setStyleImage(String styleImage) {
        this.styleImage = styleImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }



    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDesignedIn() {
        return designedIn;
    }

    public void setDesignedIn(String designedIn) {
        this.designedIn = designedIn;
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

    public String getPolishedOrMatt() {
        return polishedOrMatt;
    }

    public void setPolishedOrMatt(String polishedOrMatt) {
        this.polishedOrMatt = polishedOrMatt;
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
