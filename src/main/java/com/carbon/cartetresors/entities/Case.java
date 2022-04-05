package com.carbon.cartetresors.entities;

import com.carbon.cartetresors.entities.enumerations.CaseType;

public class Case {
    private CaseType caseType;
    private int tresor;
    private Case(String type){
        this.caseType=CaseType.valueOf(type);
    }
    public Case(String type, String tresor) {
        this(type);
        this.tresor=Integer.parseInt(tresor);
    }
}
