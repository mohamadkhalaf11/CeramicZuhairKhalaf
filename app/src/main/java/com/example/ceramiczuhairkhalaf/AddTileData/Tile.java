package com.example.ceramiczuhairkhalaf.AddTileData;

public class Tile {
    private String name;
    private double size;
    private double price;
    private String madeIn;
    private String company;
    private String designedIn;
    private boolean polished;

    public Tile(){
    }
    public Tile(String name, double size, double price, String madeIn, String company, String designedIn, boolean polished) {
        this.name=name;
        this.size=size;
        this.price=price;
        this.madeIn=madeIn;
        this.company=company;
        this.designedIn=designedIn;
        this.polished=polished;
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public String getCompany() {
        return company;
    }

    public String getDesignedIn() {
        return designedIn;
    }

    public boolean isPolished() {
        return polished;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setDesignedIn(String designedIn) {
        this.designedIn = designedIn;
    }

    public void setPolished(boolean polished) {
        this.polished = polished;
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
                '}';
    }
}
