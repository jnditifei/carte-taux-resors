package com.carbon.cartetresors.repositories;

import com.carbon.cartetresors.entities.Aventurier;
import com.carbon.cartetresors.entities.Carte;
import com.carbon.cartetresors.repositories.exceptions.InitException;

import java.io.IOException;
import java.util.List;

public interface PartieRepository {

    Carte getCarte(String path) throws InitException;

    List<Aventurier> getAventuriers(String path);

    void print(List<String> lines, String path) throws IOException;
}
