package com.example.ceramiczuhairkhalaf;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CardSet implements Parcelable {
    private String styleImage;
    private String productName;

    public CardSet() {
    }

    public CardSet(String styleImage, String productName) {
        this.styleImage = styleImage;
        this.productName = productName;
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

    @Override
    public String toString() {
        return "CardSet{" +
                "styleImage='" + styleImage + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

    }
}
