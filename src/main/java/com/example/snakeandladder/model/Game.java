package com.example.snakeandladder.model;

import java.util.List;

public class Game {
    public int boardSize;
    public int numberOfPlayers;
    List<Player> players;
    public List<Snake> snakes;
    public List<Ladder> ladders;

    public Game(int boardSize, int numberOfPlayers, List<Player> players, List<Snake> snakes, List<Ladder> ladders) {
        this.boardSize = boardSize;
        this.numberOfPlayers = numberOfPlayers;
        this.players = players;
        this.snakes = snakes;
        this.ladders = ladders;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }


    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }
}
