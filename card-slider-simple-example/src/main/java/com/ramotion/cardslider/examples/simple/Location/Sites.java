package com.ramotion.cardslider.examples.simple.Location;

public class Sites {
    String nom,short_desc,desc,gouv,ville;
    Integer image,location;

    public Sites(String nom, String short_desc, String desc, String gouv, String ville, Integer image, Integer location) {
        this.nom = nom;
        this.short_desc = short_desc;
        this.desc = desc;
        this.gouv = gouv;
        this.ville = ville;
        this.image = image;
        this.location = location;
    }

    public String getNom() {
        return nom;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getGouv() {
        return gouv;
    }

    public String getVille() {
        return ville;
    }

    public Integer getImage() {
        return image;
    }

    public Integer getLocation() {
        return location;
    }
}
