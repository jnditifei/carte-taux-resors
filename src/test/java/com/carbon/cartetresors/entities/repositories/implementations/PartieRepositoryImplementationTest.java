package com.carbon.cartetresors.entities.repositories.implementations;

import com.carbon.cartetresors.entities.repositories.exceptions.InitException;
import com.carbon.cartetresors.utils.FileProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PartieRepositoryImplementationTest {
    private List<String> lines;
    @Mock
    private FileProcessor fileProcessor;
    @InjectMocks
    private PartieRepositoryImplementation partieRepository;

    @Test
    public void getCarte() throws InitException {
        lines = List.of("C-3-4", "M-1-0", "M-2-1", "T-0-3-2", "A - Lara - 1 - 1 - S- AADADA", "A - Indiana - 1 - 2 - S - AADADA");
        when(fileProcessor.read()).thenReturn(lines);
        assertTrue(partieRepository.getCarte().getGrille()[0][3].getTresor()==2);
    }

    @Test
    public void getAventuriers() {
        lines = List.of("C-3-4", "M-1-0", "M-2-1", "T-0-3-2", "A - Lara - 1 - 1 - S- AADADA", "A - Indiana - 1 - 2 - S - AADADA");
        when(fileProcessor.read()).thenReturn(lines);
        assertEquals(2, partieRepository.getAventuriers().size());
    }

    @Test
    public void getCarteEmptyFileException() {
        lines = new ArrayList<>();
        when(fileProcessor.read()).thenReturn(lines);
        Exception exception = assertThrows(InitException.class, ()-> partieRepository.getCarte());
        assertEquals("Le fichier est vide",exception.getMessage());
    }

    @Test
    public void getIllegalFirstline(){
        lines = List.of("");
        when(fileProcessor.read()).thenReturn(lines);
        Exception exception = assertThrows(InitException.class, ()-> partieRepository.getCarte());
        assertEquals("Le fichier doit commencer par les dimensions de la grille", exception.getMessage());
    }
}