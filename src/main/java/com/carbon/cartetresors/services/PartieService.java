package com.carbon.cartetresors.services;

import com.carbon.cartetresors.entities.Carte;
import com.carbon.cartetresors.entities.Partie;

public interface PartieService {
    Partie creerPartie();

    Partie jouerPartie(Partie partie);
}
