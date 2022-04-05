package com.carbon.cartetresors.entities;

import com.carbon.cartetresors.entities.enumerations.Orientation;

public class Aventurier {
    private String nom;
    private Position position;
    private Orientation orientation;
    private String mouvement;

    public Aventurier(String[] line) {
        this.nom = line[1];
        this.position= new Position(Integer.parseInt(line[2]),Integer.parseInt(line[3]));
        this.orientation = Orientation.valueOf(line[4]);
        this.mouvement = line[5].trim();
    }

    public Aventurier(String nom, int x, int y, Orientation orientation, String mouvement) {
        this.nom = nom;
        this.position = new Position(x, y);
        this.orientation = orientation;
        this.mouvement = mouvement;
    }
}
