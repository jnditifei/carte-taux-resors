package com.carbon.cartetresors;

import com.carbon.cartetresors.entities.Partie;
import com.carbon.cartetresors.repositories.PartieRepository;
import com.carbon.cartetresors.repositories.exceptions.InitException;
import com.carbon.cartetresors.repositories.implementations.PartieRepositoryImplementation;
import com.carbon.cartetresors.services.PartieService;
import com.carbon.cartetresors.services.implementations.PartieServiceImplementation;
import com.carbon.cartetresors.utils.FileProcessor;

import java.io.IOException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        Partie partie;
        FileProcessor fileProcessor = new FileProcessor();
        PartieRepository partieRepository = new PartieRepositoryImplementation(fileProcessor);
        PartieService partieService = new PartieServiceImplementation(partieRepository);

        Scanner sc = new Scanner(System.in);
        System.out.println("Merci de saisir le chemin fichier décrivant la partie");
        String inputFile = sc.nextLine();
        System.out.println("Merci de saisir le chemin fichier à enregistrer");
        String outputFile = sc.nextLine();
        try {
            partie = partieService.creerPartie(inputFile);
            partie = partieService.jouerPartie(partie);
            partieService.imprimerPartie(partie, outputFile);
        } catch (InitException | IOException e) {
            e.printStackTrace();
        }
    }
}
