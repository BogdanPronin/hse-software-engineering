package com.github.bogdan.models;

import java.util.ArrayList;

public class Game {
    private Chip[][] board;
    private ArrayList<Chip[][]> boards;
    private boolean gameRunning;
    private boolean isWhiteTurn;

    public void setInitialBoard(){
        board = new Chip[8][8];
        boards = new ArrayList<>();
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                if(x == 3){
                    if(y == 3){
                        Chip chip = new Chip(true);
                        board[y][x] = chip;
                    }else if (y == 4){
                        Chip chip = new Chip(false);
                        board[y][x] = chip;
                    }
                }else if(x == 4){
                    if(y == 4){
                        Chip chip = new Chip(true);
                        board[y][x] = chip;
                    }else if (y == 3){
                        Chip chip = new Chip(false);
                        board[y][x] = chip;
                    }
                }else board[y][x] = null;
            }
        }
        boards.add(board);
    }

//    public Chip[][] cancelMove(){
//        if(boards.size() - 2 < 0){
//            throw new CustomException("Вы не можете отменить ход");
//        }else {
//            return boards.get(boards.size() - 3);
//        }
//    }

    public int getChipCount(boolean isWhite){
        int count = 0;
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                if(board[y][x] != null){
                    if(board[y][x].isWhite() == isWhite){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public ArrayList<Line> getAllPossibleMoves(boolean isWhite){
        ArrayList<Line> lines = new ArrayList<>();
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                Position pos = new Position(x,y);
                if(board[y][x] != null){
                    if(board[y][x].isWhite() == isWhite){
                        lines.addAll(getPossibleMoves(pos));
                    }
                }
            }
        }
        return lines;
    }

    public void move(Position position, boolean isWhite){
        boolean changed = false;
        for(Line l:getAllPossibleMoves(isWhite)){
            if(l.getPossiblePosition().equals(position)){
                board[position.getY()][position.getX()] = new Chip(isWhite);
                for(Position chipPos:l.getClosedCellsPositions()){
                    board[chipPos.getY()][chipPos.getX()].setWhite(isWhite);
                }
                changed = true;
            }
        }
        boards.add(board);
        if(!changed){
            throw new CustomException("Вы не можете ходить сюда");
        }
    }

    public Position computeMove(boolean isWhite){
        Line bestLine = new Line();
        double max = 0;
        for(Line line:getAllPossibleMoves(isWhite)){
            double sumSI = 0;
            double ss = 0;
            for(Position position:line.getClosedCellsPositions()){
                if(position.getX() == 0 || position.getX() == 7 || position.getY() == 0 || position.getY() == 7){
                    sumSI += 2;
                } else sumSI += 1;
            }
            if(line.getPossiblePosition().getX() == 0 && line.getPossiblePosition().getY() == 0
                    || line.getPossiblePosition().getX() == 7 && line.getPossiblePosition().getY() == 7
                    || line.getPossiblePosition().getX() == 0 && line.getPossiblePosition().getY() == 7
                    || line.getPossiblePosition().getX() == 7 && line.getPossiblePosition().getY() == 0){
                ss = 0.8;
            } else if(line.getPossiblePosition().getX() == 0 || line.getPossiblePosition().getX() == 7
                    || line.getPossiblePosition().getY() == 0 || line.getPossiblePosition().getY() == 7){
                ss = 0.4;
            }

            if(sumSI + ss > max){
                max = sumSI + ss;
                bestLine = line;
            }
        }
        return bestLine.getPossiblePosition();
    }

    public ArrayList<Line> getPossibleMove(Position chipPos, Position vector){
        if(board[chipPos.getY()][chipPos.getX()] == null){
            return null;
        }
        ArrayList<Line> lines = new ArrayList<>();
        ArrayList<Position> closedCellsPositions = new ArrayList<>();

        boolean color = board[chipPos.getY()][chipPos.getX()].isWhite();
        boolean order = false;

        int x = vector.getX() + chipPos.getX();
        int y = vector.getY() + chipPos.getY();


        while (true){
            if(x > 7 || y > 7 || x < 0 || y < 0){
                break;
            }
            if(board[y][x] == null && order){
                lines.add(new Line(chipPos,new Position(x,y), new ArrayList<>(closedCellsPositions)));
                break;
            }else if(board[y][x] == null){
                break;
            }else if(board[y][x].isWhite() == color){
                break;
            }else {
                closedCellsPositions.add(new Position(x,y));
                order = true;
            }
            x += vector.getX();
            y += vector.getY();
        }
        return lines;
    }

    public ArrayList<Line> getPossibleMoves(Position chipPos){
        ArrayList<Line> lines = new ArrayList<>();
        if(getPossibleMove(chipPos,new Position(1,0)) != null){
            lines.addAll(getPossibleMove(chipPos,new Position(1,0)));
        }
        if(getPossibleMove(chipPos,new Position(-1,0)) != null){
            lines.addAll(getPossibleMove(chipPos,new Position(-1,0)));
        }
        if(getPossibleMove(chipPos,new Position(0,1)) != null){
            lines.addAll(getPossibleMove(chipPos,new Position(0,1)));
        }
        if(getPossibleMove(chipPos,new Position(0,-1)) != null){
            lines.addAll(getPossibleMove(chipPos,new Position(0,-1)));
        }
        if(getPossibleMove(chipPos,new Position(1,1)) != null){
            lines.addAll(getPossibleMove(chipPos,new Position(1,1)));
        }
        if(getPossibleMove(chipPos,new Position(-1,-1)) != null){
            lines.addAll(getPossibleMove(chipPos,new Position(-1,-1)));
        }
        if(getPossibleMove(chipPos,new Position(1,-1)) != null){
            lines.addAll(getPossibleMove(chipPos,new Position(1,-1)));
        }
        if(getPossibleMove(chipPos,new Position(-1,1)) != null){
            lines.addAll(getPossibleMove(chipPos,new Position(-1,1)));
        }
        return lines;
    }


    public Chip[][] getBoard() {
        return board;
    }

    public void setBoard(Chip[][] board) {
        this.board = board;
    }


    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        isWhiteTurn = whiteTurn;
    }

    public ArrayList<Chip[][]> getBoards() {
        return boards;
    }

    public void setBoards(ArrayList<Chip[][]> boards) {
        this.boards = boards;
    }
}
