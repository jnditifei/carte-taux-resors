package com.carbon.cartetresors.entities;

import lombok.Getter;

@Getter
public class Carte {
    private Case[][] grille;
    public Carte(String largeur, String hauteur) {
        this.grille = new Case[Integer.parseInt(largeur)][Integer.parseInt(hauteur)];
    }
    public void deplacer(Position position, int newX, int newY){
        this.getGrille()[position.getAxeX()][position.getAxeY()].setAventurierPresent(false);
        this.getGrille()[newX][newY].setAventurierPresent(false);
    }
}
