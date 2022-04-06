package com.carbon.cartetresors.services.implementations;

import com.carbon.cartetresors.entities.*;
import com.carbon.cartetresors.repositories.PartieRepository;
import com.carbon.cartetresors.repositories.exceptions.InitException;
import com.carbon.cartetresors.services.Exception.NotAuthorizedException;
import com.carbon.cartetresors.services.PartieService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartieServiceImplementation implements PartieService {

    private final PartieRepository partieRepository;

    public PartieServiceImplementation(PartieRepository partieRepository) {
        this.partieRepository= partieRepository;

    }

    @Override
    public Partie creerPartie(String path) throws InitException {
        return new Partie(partieRepository.getAventuriers(path), partieRepository.getCarte(path));
    }

    @Override
    public Partie jouerPartie(Partie partie) {
        partie.getAventuriers().forEach(a->executer(a, partie.getCarte()));
        return partie;
    }

    @Override
    public List<String> imprimerPartie(Partie partie, String path) throws IOException {
        List<String> lines = new ArrayList<>();
        Case[][] grille = partie.getCarte().getGrille();
        String firstLine = String.format("C-%d-%d", grille.length, grille[0].length);
        lines.add(firstLine);
        lines.addAll(setCarte(grille));
        lines.addAll(partie.getAventuriers().stream().map( a -> setAventurier(a)).collect(Collectors.toList()));
        partieRepository.print(lines, path);
        return lines;
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
            if (land.getCaseType().getName().equals("TRESOR") && land.getTresor() > 0) {
                carte.getGrille()[axeX][axeY].decrementTresor();
                aventurier.ramassertresor();
            }else if (land.getCaseType().getName().equals("MONTAGNE") || land.isAventurierPresent()) {
                throw new NotAuthorizedException("Cette case est inaccessible");
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

    private List<String> setCarte(Case[][] grille) {
        List<String> lines = new ArrayList<>();
        for (int row = 0; row < grille.length; row++) {
            for (int col = 0; col < grille[row].length; col++) {
                if(grille[row][col].getCaseType().getName().equals("TRESOR")){
                    String s = String.format("T-%d-%d-%d", row, col, grille[row][col].getTresor());
                    lines.add(s);
                }else if(grille[row][col].getCaseType().getName().equals("MONTAGNE")) {
                    String s = String.format("M-%d-%d",row, col);
                    lines.add(s);
                }
            }
        }
        return lines;
    }

    private String setAventurier(Aventurier aventurier){
        return String.format("A - %s - %d - %d - %s - %d", aventurier.getNom(), aventurier.getPosition().getAxeX(), aventurier.getPosition().getAxeY(),
                aventurier.getOrientation().getName(),aventurier.getNombretresor());
    }

}
