package com.carbon.cartetresors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.carbon.cartetresors.entities.Partie;
import com.carbon.cartetresors.repositories.PartieRepository;
import com.carbon.cartetresors.repositories.exceptions.InitException;
import com.carbon.cartetresors.repositories.implementations.PartieRepositoryImplementation;
import com.carbon.cartetresors.services.PartieService;
import com.carbon.cartetresors.services.implementations.PartieServiceImplementation;
import com.carbon.cartetresors.utils.FileProcessor;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private FileProcessor fileProcessor = new FileProcessor();
    private PartieRepository partieRepository = new PartieRepositoryImplementation(fileProcessor);
    private PartieService partieService = new PartieServiceImplementation(partieRepository);
    private Partie partie;
    private String inputFile = "src/test/resources/file.txt";
    private String outputFile = "src/test/resources/output.txt";

    @Test
    public void shouldAnswerWithTrue() throws InitException, IOException {
        partie = partieService.creerPartie(inputFile);
        partie =  partieService.jouerPartie(partie);
        assertEquals(6,partieService.imprimerPartie(partie, outputFile).size());
    }
}
