package com.carbon.cartetresors.entities;

import com.carbon.cartetresors.entities.enumerations.Orientation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
public class Aventurier {
    private String nom;
    private Position position;
    private Orientation orientation;
    private char[] mouvements;
    private int nombretresor;

    public Aventurier(String[] line) {
        this.nom = line[1].trim();
        this.position= new Position(Integer.parseInt(line[2].trim()),Integer.parseInt(line[3].trim()));
        this.orientation = Orientation.valueOf(line[4].trim());
        this.mouvements = line[5].trim().toCharArray();
    }

    public Aventurier(String nom, int x, int y, Orientation orientation, String mouvement) {
        this.nom = nom;
        this.position = new Position(x, y);
        this.orientation = orientation;
        this.mouvements = mouvement.toCharArray();
    }

    public void tournerGaucher() {

        switch (this.orientation){
            case N:
                this.orientation=Orientation.O;
                break;
            case O:
                this.orientation=Orientation.S;
                break;
            case S:
                this.orientation=Orientation.E;
                break;
            case E:
                this.orientation=Orientation.N;
                break;
        }
    }

    public void tournerDroite() {
        switch (this.orientation){
            case N:
                this.orientation=Orientation.E;
                break;
            case E:
                this.orientation=Orientation.S;
                break;
            case S:
                this.orientation=Orientation.O;
                break;
            case O:
                this.orientation=Orientation.N;
                break;
        }
    }

    public void avancer(int x, int y){
        this.position.setAxeX(x);
        this.position.setAxeY(y);
    }
    public void ramassertresor(){
        nombretresor++;
    }

}
