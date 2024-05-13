package com.example.ceramiczuhairkhalaf.Classes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Tile implements Parcelable {
    private String name;
    private double size;
    private double price;
    private String madeIn;
    private String company;
    private String designedIn;
    private boolean polished;
    private String image;
    private String styleImage;

    public Tile() {
    }

    public Tile(String name, double size, double price, String madeIn, String company, String designedIn, boolean polished, String image, String styleImage) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.madeIn = madeIn;
        this.company = company;
        this.designedIn = designedIn;
        this.polished = polished;
        this.image = image;
        this.styleImage = styleImage;
    }

    protected Tile(Parcel in) {
        name = in.readString();
        size = in.readDouble();
        price = in.readDouble();
        madeIn = in.readString();
        company = in.readString();
        designedIn = in.readString();
        polished = in.readByte() != 0;
        image = in.readString();
        styleImage = in.readString();
    }

    public static final Creator<Tile> CREATOR = new Creator<Tile>() {
        @Override
        public Tile createFromParcel(Parcel in) {
            return new Tile(in);
        }

        @Override
        public Tile[] newArray(int size) {
            return new Tile[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
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

    public boolean isPolished() {
        return polished;
    }

    public void setPolished(boolean polished) {
        this.polished = polished;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStyleImage() {
        return styleImage;
    }

    public void setStyleImage(String styleImage) {
        this.styleImage = styleImage;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", madeIn='" + madeIn + '\'' +
                ", company='" + company + '\'' +
                ", designedIn='" + designedIn + '\'' +
                ", polished=" + polished +
                ", image='" + image + '\'' +
                ", styleImage='" + styleImage + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(size);
        dest.writeDouble(price);
        dest.writeString(madeIn);
        dest.writeString(company);
        dest.writeString(designedIn);
        dest.writeByte((byte) (polished ? 1 : 0));
        dest.writeString(image);
        dest.writeString(styleImage);
    }
}
