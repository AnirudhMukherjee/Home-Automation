package com.example.anirudh.djstrike;

/**
 * Created by aniru on 16-02-2018.
 */

public class Appliance {
    private String name;
    private String number;
    private int thumbnail;

    public Appliance(String name, String number, int thumbnail) {
        this.name = name;
        this.number = number;
        this.thumbnail = thumbnail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getThumbnail() {
        return thumbnail;
    }
}
