package com.carbon.cartetresors.services;

import com.carbon.cartetresors.entities.Partie;
import com.carbon.cartetresors.repositories.exceptions.InitException;

import java.io.IOException;
import java.util.List;

public interface PartieService {
    Partie creerPartie(String path) throws InitException;

    Partie jouerPartie(Partie partie);

    List<String> imprimerPartie(Partie partie, String path) throws IOException;
}
