package com.example.catalogapp.model;

public class SecondModelShow {
    private int image;
    private boolean _new;
    private double star_point;
    private String detail;
    private String price;
    private String kilo;

    public SecondModelShow(int image, boolean _new, double star_point, String detail, String price, String kilo) {
        this.image = image;
        this._new = _new;
        this.star_point = star_point;
        this.detail = detail;
        this.price = price;
        this.kilo = kilo;
    }

    public SecondModelShow(boolean _new, double star_point, String detail, String price, String kilo) {
        this._new = _new;
        this.star_point = star_point;
        this.detail = detail;
        this.price = price;
        this.kilo = kilo;
    }

//    public SecondModelShow(String string, String detail, String price, String kilo, double aDouble) {
//        this.detail = detail;
//        this.price = price;
//        this.kilo = kilo;
//    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean is_new() {
        return _new;
    }

    public void set_new(boolean _new) {
        this._new = _new;
    }

    public double getStar_point() {
        return star_point;
    }

    public void setStar_point(double star_point) {
        this.star_point = star_point;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
}
