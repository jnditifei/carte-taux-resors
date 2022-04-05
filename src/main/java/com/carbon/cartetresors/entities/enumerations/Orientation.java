package com.carbon.cartetresors.entities.enumerations;

public enum Orientation {
    N("NORD"), O("OUEST"), E("EST"), S("SUD");

    private final String name;

    Orientation(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
