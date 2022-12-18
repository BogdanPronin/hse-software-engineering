package com.github.bogdan.models;

import java.util.ArrayList;
import java.util.List;

public class MoveParser {
    public static Position parseMove(String line){
        if(line.length() != 2){
            throw new CustomException("Повторите ввод");
        }
        Position pos = new Position();
        if(line.charAt(0) >= 'a' && line.charAt(0) <= 'h'
                && line.charAt(1) > '0' && line.charAt(1) < '9') {
            pos.setX(line.charAt(0) - 96 - 1);
            pos.setY(line.charAt(1) - 48 - 1);
        } else {
            throw new CustomException("Повторите ввод");
        }
        return pos;
    }

    public static String parseMove(Position pos){
        return String.valueOf((char)(pos.getX()+ 96 + 1)) + (char) (pos.getY() + 48 + 1);
    }

}
