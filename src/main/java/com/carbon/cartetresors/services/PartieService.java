package com.carbon.cartetresors.services;

import com.carbon.cartetresors.entities.Carte;
import com.carbon.cartetresors.entities.Partie;
import com.carbon.cartetresors.entities.repositories.exceptions.InitException;

public interface PartieService {
    Partie creerPartie() throws InitException;

    Partie jouerPartie(Partie partie);
}
