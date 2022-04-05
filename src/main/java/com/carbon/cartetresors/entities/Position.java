package com.carbon.cartetresors.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Position {
    private int axeX;
    private int axeY;

    public Position(int x, int y){
        this.axeX=x;
        this.axeY=y;
    }
}
