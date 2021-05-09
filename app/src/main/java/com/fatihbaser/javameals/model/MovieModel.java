package com.fatihbaser.javameals.model;

public class MovieModel {
    private String title;
    private String image;
    private String malzemeler;
    private String tarifi;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMalzemeler() {
        return malzemeler;
    }

    public void setMalzemeler(String malzemeler) {
        this.malzemeler = malzemeler;
    }

    public String getTarifi() {
        return tarifi;
    }

    public void setTarifi(String tarifi) {
        this.tarifi = tarifi;
    }

    public MovieModel(String title, String image, String malzemeler, String tarifi) {
        this.title = title;
        this.image = image;
        this.malzemeler = malzemeler;
        this.tarifi = tarifi;


    }
}
