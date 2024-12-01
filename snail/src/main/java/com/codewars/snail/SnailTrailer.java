package com.codewars.snail;

import java.util.Arrays;
import java.util.List;

public class SnailTrailer {

    private static final List<Direction> DIRECTIONS_ORDER = List.of(Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTH);

    private enum Direction {
        NORTH, SOUTH, EAST, WEST
    }

    private record Position(int x, int y) {}

    private record SSnail(Position position, Direction direction) {
        private SSnail(){
            this(new Position(0, 0), DIRECTIONS_ORDER.getFirst());
        }
    }

    private Position nextPosition(SSnail snail){
        var position = snail.position();
        return switch (snail.direction()){
            case EAST -> new Position(position.x() + 1, position.y());
            case WEST -> new Position(position.x() - 1, position.y());
            case NORTH -> new Position(position.x(), position.y() - 1);
            case SOUTH -> new Position(position.x(), position.y() + 1);
        };
    }

    private Direction nextDirection(Direction currentDirection){
        return DIRECTIONS_ORDER.get((DIRECTIONS_ORDER.indexOf(currentDirection) + 1) % DIRECTIONS_ORDER.size());
    }

    private SSnail moveSnail(SSnail snail) {
        return new SSnail(nextPosition(snail), snail.direction());
    }

    private SSnail turnSnail(SSnail snail) {
        return new SSnail(snail.position(), nextDirection(snail.direction()));
    }

    private int valueAtPosition(int[][] grid, SSnail snail){
        return grid[snail.position().y()][snail.position().x()];
    }

    private int registerValue(int[][] grid, boolean[][] trailGrid, SSnail snail){
        registerPosition(trailGrid, snail);
        return valueAtPosition(grid, snail);
    }

    private void registerPosition(boolean[][] trailGrid, SSnail snail){
        trailGrid[snail.position().y()][snail.position().x()] = true;
    }

    private boolean positionInTrail(boolean[][] trailGrid, Position position){
        return trailGrid[position.y()][position.x()];
    }

    private boolean positionInbound(int[][] grid, Position position){
        return position.x() >= 0 && position.x() < grid.length && position.y() >= 0 && position.y() < grid[0].length;
    }

    private boolean nextPositionValid(int[][] grid, boolean[][] trailGrid, SSnail snail){
        var nextPosition = nextPosition(snail);
        return positionInbound(grid, nextPosition)
                && !positionInTrail(trailGrid, nextPosition);
    }

    private boolean[][] createTrailGrid(int width, int height){
        var trailGrid = new boolean[height][width];
        Arrays.stream(trailGrid).forEach(row -> Arrays.fill(row, false));
        return trailGrid;
    }

    public int[] snail(int[][] grid){
        int width = grid[0].length;
        int height = grid.length;
        var result = new int[height * width];
        var trailGrid = createTrailGrid(width, height);

        var snail = new SSnail();

        for(var i=0; i < result.length; i++){
            if(!nextPositionValid(grid, trailGrid, snail)){
                snail = turnSnail(snail);
            }
            result[i] = registerValue(grid, trailGrid, snail);
            snail = moveSnail(snail);
        }

        return result;
    }

}
