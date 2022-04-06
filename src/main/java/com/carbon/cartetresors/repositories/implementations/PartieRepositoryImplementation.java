package com.carbon.cartetresors.repositories.implementations;

import com.carbon.cartetresors.entities.Aventurier;
import com.carbon.cartetresors.entities.Carte;
import com.carbon.cartetresors.entities.Case;
import com.carbon.cartetresors.entities.enumerations.CaseType;
import com.carbon.cartetresors.repositories.PartieRepository;
import com.carbon.cartetresors.repositories.exceptions.InitException;
import com.carbon.cartetresors.utils.FileProcessor;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PartieRepositoryImplementation implements PartieRepository {
    private final FileProcessor fileProcessor;

    public PartieRepositoryImplementation(FileProcessor fileProcessor){
        this.fileProcessor = fileProcessor;
    }
    @Override
    public Carte getCarte(String path) throws InitException {

        List<String> lines = fileProcessor.read(path);
        Carte carte  = build(lines);
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

    @Override
    public List<Aventurier> getAventuriers(String path) {
        return fileProcessor.read(path).stream().filter(l -> l.startsWith("A"))
                .map(a ->new Aventurier(a.split("-")))
                .collect(Collectors.toList());
    }

    @Override
    public void print(List<String> lines, String path) throws IOException {
        fileProcessor.write(lines, path);
    }

    private Carte build(List<String> lines) throws InitException{
        String firstLine;
        Carte carte;
        try {
            firstLine = lines.get(0);
            if (!firstLine.startsWith("C")){
                throw new InitException("Le fichier doit commencer par les dimensions de la grille");
            }
            String[] dimension = firstLine.split("-");
            carte = new Carte(dimension[1], dimension[2]);
            Case[][] grille = carte.getGrille();
            for (int row = 0; row < grille.length; row++) {
                for (int col = 0; col < grille[row].length; col++) {
                    grille[row][col] = new Case();
                }
            }
        }catch (IndexOutOfBoundsException e) {
            throw new InitException("Le fichier est vide");
        }
        return carte;
    }

    private void setMontagne(String line, Case[][] grille) {
        String[] values = line.split("-");
        grille[Integer.parseInt(values[1])][Integer.parseInt(values[2])]= new Case(CaseType.M);
    }
    private void setTresor(String line, Case[][] grille) {
        String[] values = line.split("-");
        grille[Integer.parseInt(values[1])][Integer.parseInt(values[2])]= new Case(CaseType.T, values[3]);
    }
    private void setAventurier(String line, Case[][] grille) {
        String[] values = line.split("-");
        grille[Integer.parseInt(values[2].trim())][Integer.parseInt(values[3].trim())].setAventurierPresent(true);
    }
}
