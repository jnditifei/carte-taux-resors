package com.carbon.cartetresors.entities.enumerations;

public enum CaseType {
    MONTAGNE("M"), TRESOR("T"), VIDE(".");

    private final String name;

    CaseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
