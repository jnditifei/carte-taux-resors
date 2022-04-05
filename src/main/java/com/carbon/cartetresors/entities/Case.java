package com.carbon.cartetresors.entities;

import com.carbon.cartetresors.entities.enumerations.CaseType;
import lombok.Getter;

@Getter
public class Case {
    private CaseType caseType;
    private int tresor;
    private boolean aventurierPresent;

    public Case(){this.caseType=CaseType.V;}
    public Case(CaseType caseType){
        this.caseType=caseType;
    }
    public Case(CaseType type, String tresor) {
        this(type);
        this.tresor=Integer.parseInt(tresor);
    }

    public void decrementTresor() {
        tresor--;
    }

    public void setAventurierPresent(boolean b){
        aventurierPresent=b;
    }
}
