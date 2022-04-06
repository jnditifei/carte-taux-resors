package com.carbon.cartetresors.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter@ToString
@EqualsAndHashCode
public class Partie {
    private List<Aventurier> aventuriers;
    private Carte carte;

    public Partie(List<Aventurier> aventuriers, Carte carte) {
        this.aventuriers=aventuriers;
        this.carte=carte;
    }
}
