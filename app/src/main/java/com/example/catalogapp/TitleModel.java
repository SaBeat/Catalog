package com.example.catalogapp;

public class TitleModel {

    private String title;
    private int image_main;

    public TitleModel(String title, int image_main) {
        this.title = title;
        this.image_main = image_main;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage_main() {
        return image_main;
    }

    public void setImage_main(int image_main) {
        this.image_main = image_main;
    }
}
