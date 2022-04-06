package com.carbon.cartetresors.services.implementations;

import com.carbon.cartetresors.entities.*;
import com.carbon.cartetresors.entities.enumerations.CaseType;
import com.carbon.cartetresors.entities.enumerations.Orientation;
import com.carbon.cartetresors.repositories.exceptions.InitException;
import com.carbon.cartetresors.repositories.implementations.PartieRepositoryImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PartieServiceImplementationTest {
    private List<Aventurier> aventuriers;
    private Carte carte;
    Partie partie;
    @Mock
    private PartieRepositoryImplementation partieRepositoryImplementation;
    @InjectMocks
    private PartieServiceImplementation partieService;

    @Before
    public void init() {
        aventuriers = new ArrayList<>();
        Aventurier aventurier = new Aventurier("Lara",1,1, Orientation.S,"AADADAGGA");
        aventuriers.add(aventurier);
        carte = new Carte("3","4");
        for(int row = 0; row < carte.getGrille().length; row++){
            for(int col = 0; col < carte.getGrille()[row].length; col++){
                carte.getGrille()[row][col] = new Case(CaseType.V);
            }
        }
        carte.getGrille()[1][0] = new Case(CaseType.M);
        carte.getGrille()[0][3] = new Case(CaseType.T, "2");
        carte.getGrille()[1][3] = new Case(CaseType.T, "3");
        partie = new Partie(aventuriers, carte);
    }

    @Test
    public void creerPartie() throws InitException {
        when(partieRepositoryImplementation.getAventuriers(anyString())).thenReturn(aventuriers);
        when(partieRepositoryImplementation.getCarte(anyString())).thenReturn(carte);
        assertEquals(partie, partieService.creerPartie(""));
    }

    @Test
    public void jouerPartie() {
        assertTrue(partieService.jouerPartie(partie).getAventuriers().get(0).getNombretresor()==3);
    }

    @Test
    public void jouerPartieObstcaleException() {
        partie.getCarte().getGrille()[1][2]=new Case(CaseType.M);
        assertEquals(new Position(0,1), partieService.jouerPartie(partie).getAventuriers().get(0).getPosition());
    }

    @Test
    public void jouerPartieHorsLimite() {
        Aventurier aventurier = new Aventurier("Indiana",2,2, Orientation.S,"AADADAGGA");
        partie.getAventuriers().add(aventurier);
        assertEquals(new Position(1,3), partieService.jouerPartie(partie).getAventuriers().get(1).getPosition());
    }

    @Test
    public void imprimerPartie() throws IOException {
        doNothing().when(partieRepositoryImplementation).print(any(), anyString());
        assertEquals("C-3-4", partieService.imprimerPartie(partie, "").get(0));
    }
}