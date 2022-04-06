package com.carbon.cartetresors.entities.repositories;

import com.carbon.cartetresors.entities.Aventurier;
import com.carbon.cartetresors.entities.Carte;
import com.carbon.cartetresors.entities.repositories.exceptions.InitException;

import java.util.List;

public interface PartieRepository {

    Carte getCarte() throws InitException;

    List<Aventurier> getAventuriers();
}
