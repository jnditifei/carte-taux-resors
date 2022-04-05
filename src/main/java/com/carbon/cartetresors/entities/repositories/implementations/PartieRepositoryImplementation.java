package com.carbon.cartetresors.entities.repositories.implementations;

import com.carbon.cartetresors.entities.Carte;
import com.carbon.cartetresors.entities.Case;
import com.carbon.cartetresors.entities.enumerations.CaseType;
import com.carbon.cartetresors.entities.repositories.PartieRepository;
import com.carbon.cartetresors.utils.FileProcessor;

import java.util.Arrays;
import java.util.List;

public class PartieRepositoryImplementation implements PartieRepository {
    private final FileProcessor fileProcessor;

    public PartieRepositoryImplementation(FileProcessor fileProcessor){
        this.fileProcessor = fileProcessor;
    }
    @Override
    public Carte getCarte() {

        List<String> lines = fileProcessor.read();
        Carte carte  = build(lines.get(0));
        for(String line: lines){
            if(line.startsWith("M")){
                setMontagne(line, carte.getGrille());
            }else if(line.startsWith("T")){
                setTresor(line, carte.getGrille());
            }else if(line.startsWith("A")){
                setAventurier(line, carte.getGrille());
            }
        }
        return carte;
    }

    private Carte build(String firstLine){
        String[] dimension  = firstLine.split("-");
        Arrays.stream(dimension).forEach(a ->a.trim());
        Carte carte = new Carte(dimension[1], dimension[2]);
        Case[][] grille = carte.getGrille();
        for (int row = 0; row < grille.length; row++) {
            for (int col = 0; col < grille[row].length; col++) {
                grille[row][col] = new Case();
            }
        }
        return carte;
    }

    void setMontagne(String line, Case[][] grille) {
        String[] values = line.split("-");
        grille[Integer.parseInt(values[1])][Integer.parseInt(values[2])]= new Case(CaseType.M);
    }
    void setTresor(String line, Case[][] grille) {
        String[] values = line.split("-");
        grille[Integer.parseInt(values[1])][Integer.parseInt(values[2])]= new Case(CaseType.T, values[3]);
    }
    void setAventurier(String line, Case[][] grille) {
        String[] values = line.split("-");
        grille[Integer.parseInt(values[2].trim())][Integer.parseInt(values[3].trim())].setAventurierPresent(true);
    }
}
