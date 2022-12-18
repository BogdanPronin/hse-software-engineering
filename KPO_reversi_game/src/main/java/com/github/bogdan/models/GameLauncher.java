package com.github.bogdan.models;

import com.github.bogdan.Main;

import static com.github.bogdan.models.GameUI.drawBoard;

public class GameLauncher {
    public void startGameWithBot(){
        Game g = new Game();
        g.setInitialBoard();
        g.setWhiteTurn(false);
        g.setGameRunning(true);
        while (g.isGameRunning()){
            try {
                drawBoard(g);
                if(g.getAllPossibleMoves(g.isWhiteTurn()).isEmpty()){
                    if(g.getAllPossibleMoves(!g.isWhiteTurn()).isEmpty()){
                        g.setGameRunning(false);
                        if(g.getChipCount(true) > g.getChipCount(false)){
                            System.out.println("Выиграли белые");
                        }else System.out.println("Выиграли черные");
                    }
                } else if(g.isWhiteTurn()){
                    System.out.println("Ходят белые");
                    Position p = g.computeMove(g.isWhiteTurn());
                    g.move(p,g.isWhiteTurn());
                    System.out.println(MoveParser.parseMove(p));
                } else {
                    System.out.println("Ходят черные");
                    String line = Main.scanner.nextLine();
                    g.move(MoveParser.parseMove(line),g.isWhiteTurn());
                }
                g.setWhiteTurn(!g.isWhiteTurn());

            }catch (CustomException customException){
                System.err.println(customException.getMessage());
            }
        }
    }

    public void startGameWithPerson() {
        Game g = new Game();
        g.setInitialBoard();
        g.setWhiteTurn(false);
        g.setGameRunning(true);
        while (g.isGameRunning()) {
            try {
                drawBoard(g);
                if (g.getAllPossibleMoves(g.isWhiteTurn()).isEmpty()) {
                    if (g.getAllPossibleMoves(!g.isWhiteTurn()).isEmpty()) {
                        g.setGameRunning(false);
                        if (g.getChipCount(true) > g.getChipCount(false)) {
                            System.out.println("Выиграли белые");
                        } else System.out.println("Выиграли черные");
                    }
                } else if (g.isWhiteTurn()) {
                    System.out.println("Ходят белые");
                } else {
                    System.out.println("Ходят черные");
                }
                String line = Main.scanner.nextLine();
                g.move(MoveParser.parseMove(line), g.isWhiteTurn());
                g.setWhiteTurn(!g.isWhiteTurn());

            } catch (CustomException customException) {
                System.err.println(customException.getMessage());
            }
        }
    }

}
