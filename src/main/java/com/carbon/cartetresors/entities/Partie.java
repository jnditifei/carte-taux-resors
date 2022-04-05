package com.carbon.cartetresors.entities;

import lombok.Getter;

import java.util.List;

@Getter
public class Partie {
    private List<Aventurier> aventuriers;
    private Carte carte;

    public Partie(List<Aventurier> aventuriers, Carte carte) {
        this.aventuriers=aventuriers;
        this.carte=carte;
    }
}
