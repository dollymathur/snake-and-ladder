package com.example.snakeandladder.model;

import java.util.List;

public class GameWithMultipleDice extends Game {

    public int diceCount = 1;

    public GameWithMultipleDice(int boardSize, int numberOfPlayers, List<Player> players, List<Snake> snakes, List<Ladder> ladders, int diceCount) {
        super(boardSize, numberOfPlayers, players, snakes, ladders);
        this.diceCount = diceCount;
    }

    public GameWithMultipleDice(Game game, int diceCount) {
        super(game.getBoardSize(), game.getNumberOfPlayers(), game.getPlayers(), game.getSnakes(), game.getLadders());
        this.diceCount = diceCount;
    }

    public GameWithMultipleDice(Game game) {
        super(game.getBoardSize(), game.getNumberOfPlayers(), game.getPlayers(), game.getSnakes(), game.getLadders());
    }

    public int getDiceCount() {
        return diceCount;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }
}

