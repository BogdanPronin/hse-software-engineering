package com.github.bogdan.models;

public class Chip {

    private boolean isWhite;

    public Chip() {
    }

    public Chip(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public void draw(){
        if(isWhite){
            System.out.print("⚪");
        }else {
            System.out.print("⚫");
        }
    }
}
