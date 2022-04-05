package com.carbon.cartetresors.entities.enumerations;

public enum CaseType {
    M("MONTAGNE"), T("TRESOR"), V(".");

    private final String name;

    CaseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
