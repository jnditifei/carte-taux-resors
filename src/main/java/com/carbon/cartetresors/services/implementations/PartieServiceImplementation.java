package com.carbon.cartetresors.services.implementations;

import com.carbon.cartetresors.entities.*;
import com.carbon.cartetresors.entities.repositories.PartieRepository;
import com.carbon.cartetresors.services.Exception.NotAuthorizedException;
import com.carbon.cartetresors.services.PartieService;

public class PartieServiceImplementation implements PartieService {

    private final PartieRepository partieRepository;

    public PartieServiceImplementation(PartieRepository partieRepository) {
        this.partieRepository= partieRepository;

    }

    @Override
    public Partie creerPartie() {
        return null;
    }

    @Override
    public Partie jouerPartie(Partie partie) {
        partie.getAventuriers().forEach(a->executer(a, partie.getCarte()));
        return partie;
    }

    private void executer(Aventurier aventurier, Carte carte)  {
        for(char c: aventurier.getMouvements()) {
            switch (c){
                case 'A':
                    try {
                        avancer(aventurier, carte);
                        break;
                    }catch (NotAuthorizedException e) {
                        break;
                    }
                case 'G':
                    aventurier.tournerGaucher();
                    break;
                case 'D':
                    aventurier.tournerDroite();
                    break;
            }
        }
    }

    private void avancer(Aventurier aventurier, Carte carte) throws NotAuthorizedException {
        Position nouvellePosition = calculerNouvellePosition(aventurier);
        int axeX = nouvellePosition.getAxeX();
        int axeY = nouvellePosition.getAxeY();
        try {
            Case land = carte.getGrille()[axeX][axeY];
            if (land.getCaseType().getName().equals("T") && land.getTresor() > 0) {
                carte.getGrille()[axeX][axeY].decrementTresor();
                aventurier.ramassertresor();
            }else if (land.getCaseType().getName().equals("MONTAGE") || land.isAventurierPresent()) {
                throw new NotAuthorizedException("La montagne ne peut être franchie");
            }
            carte.deplacer(aventurier.getPosition(), axeX, axeY);
            aventurier.avancer(axeX, axeY);
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new NotAuthorizedException("Case hors limite de la grille");
        }
    }

    private Position calculerNouvellePosition(Aventurier aventurier) {
        Position nouvellePosition = new Position();
        switch (aventurier.getOrientation()){
            case N:
                nouvellePosition.setAxeX(aventurier.getPosition().getAxeX());
                nouvellePosition.setAxeY(aventurier.getPosition().getAxeY() -1);
                break;
            case E:
                nouvellePosition.setAxeX(aventurier.getPosition().getAxeX()+1);
                nouvellePosition.setAxeY(aventurier.getPosition().getAxeY());
                break;
            case S:
                nouvellePosition.setAxeX(aventurier.getPosition().getAxeX());
                nouvellePosition.setAxeY(aventurier.getPosition().getAxeY() +1);
                break;
            case O:
                nouvellePosition.setAxeX(aventurier.getPosition().getAxeX()-1);
                nouvellePosition.setAxeY(aventurier.getPosition().getAxeY());
                break;
        }
        return nouvellePosition;
    }

}
