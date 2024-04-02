package com.example.ceramiczuhairkhalaf.AddTileData;

public class BathSanitary {
    private String name;
    private double size;
    private double price;
    private String madeIn;
    private String image;


    public BathSanitary() {
    }

    public BathSanitary(String name, double size, double price, String madeIn, String image) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.madeIn = madeIn;
        this.image = image;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
