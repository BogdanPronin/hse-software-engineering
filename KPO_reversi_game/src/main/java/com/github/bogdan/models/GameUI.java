package com.github.bogdan.models;

public class GameUI {
    public static void drawBoard(Game game){
        System.out.println("\ta\tb\tc\td\te\tf\tg\th");
        for (int y = 0; y < 8; y++) {
            System.out.print(y + 1 + "." + "\t");
            for (int x = 0; x < 8; x++) {
                Position pos = new Position(x,y);
                if(game.getBoard()[y][x] != null){
                    game.getBoard()[y][x].draw();
                } else{
                    boolean possibleForWhite = false;
                    boolean possibleForBlack = false;
                    for(Line l:game.getAllPossibleMoves(true)){
                        if(pos.equals(l.getPossiblePosition())){
                            possibleForWhite = true;
                            break;
                        }
                    }
                    for(Line l:game.getAllPossibleMoves(false)){
                        if(pos.equals(l.getPossiblePosition())){
                            possibleForBlack = true;
                            break;
                        }
                    }

                    if(possibleForBlack && possibleForWhite) {
                        System.out.print("◐");
                    } else if(possibleForBlack){
                        System.out.print("◘");
                    } else if(possibleForWhite){
                        System.out.print("◉");
                    } else {
                        System.out.print("◦");
                    }
                }
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println("Белых фишек: " + game.getChipCount(true) + "\n"
                + "Черных фишек: " + game.getChipCount(false));
    }
}
