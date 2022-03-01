package com.example.catalogapp;

public class SecondModelRecommend {

    private int image;
    private float star_text;
    private String price;
    private String kilo;
    private boolean isNew;

    public SecondModelRecommend(int image, float star_text, String price, String kilo, boolean isNew) {
        this.image = image;
        this.star_text = star_text;
        this.price = price;
        this.kilo = kilo;
        this.isNew = isNew;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public float getStar_text() {
        return star_text;
    }

    public void setStar_text(float star_text) {
        this.star_text = star_text;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getKilo() {
        return kilo;
    }

    public void setKilo(String kilo) {
        this.kilo = kilo;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
