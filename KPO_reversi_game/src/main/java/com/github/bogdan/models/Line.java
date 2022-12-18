package com.github.bogdan.models;

import java.util.ArrayList;

public class Line {
    private Position chipPosition;

    private Position possiblePosition;

    private ArrayList<Position> closedCellsPositions;

    public Line() {
    }

    public Line(Position chipPosition, Position possiblePosition, ArrayList<Position> closedCellsPositions) {
        this.chipPosition = chipPosition;
        this.possiblePosition = possiblePosition;
        this.closedCellsPositions = closedCellsPositions;
    }

    public Position getChipPosition() {
        return chipPosition;
    }

    public void setChipPosition(Position chipPosition) {
        this.chipPosition = chipPosition;
    }

    public Position getPossiblePosition() {
        return possiblePosition;
    }

    public void setPossiblePosition(Position possiblePosition) {
        this.possiblePosition = possiblePosition;
    }

    public ArrayList<Position> getClosedCellsPositions() {
        return closedCellsPositions;
    }

    public void setClosedCellsPositions(ArrayList<Position> closedCellsPositions) {
        this.closedCellsPositions = closedCellsPositions;
    }

    @Override
    public String toString() {
        return "Line{" +
                "chipPosition=" + chipPosition +
                ", possiblePosition=" + possiblePosition +
                ", closedCellsPositions=" + closedCellsPositions +
                '}';
    }
}
