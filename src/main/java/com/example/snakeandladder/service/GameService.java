package com.example.snakeandladder.service;

import com.example.snakeandladder.model.Game;
import com.example.snakeandladder.model.GameWithMultipleDice;

import java.util.ArrayList;
import java.util.List;

public class GameService {

    private final Game game;
    private int currentPlayer;
    private final List<Integer> finishedPlayers = new ArrayList<>();
    private boolean isGameCompleted;
    private final DiceService diceService;

    public GameService(Game gameWithMultipleDice, int currentPlayer, boolean isGameCompleted, DiceService diceService) {
        this.game = gameWithMultipleDice;
        this.currentPlayer = currentPlayer;
        this.isGameCompleted = isGameCompleted;
        this.diceService = diceService;
    }

    public void startGame() {
        System.out.println("Game started");
        while (!isGameCompleted) {
            takeTurn();
        }
    }

    public void takeTurn() {
        int diceValue;
        if (game instanceof GameWithMultipleDice) {
            diceValue = diceService.getTotalRollValue(((GameWithMultipleDice) game).diceCount);
        } else {
            diceValue = diceService.getTotalRollValue(1);
        }

        System.out.println("Player " + game.getPlayers().get(currentPlayer).getId() + " rolled a " + diceValue);

        if (game.getPlayers().get(currentPlayer).getPosition() + diceValue <= game.getBoardSize()) {

            int positionAfterDiceRoll = game.getPlayers().get(currentPlayer).getPosition() + diceValue;

            int actionMove;
            actionMove = isSnakePresent(positionAfterDiceRoll);
            if (actionMove == positionAfterDiceRoll) {
                actionMove = isLadderPresent(positionAfterDiceRoll);
            }

            game.getPlayers().get(currentPlayer).setPosition(actionMove);

            System.out.println(" moved to " + game.getPlayers().get(currentPlayer).getPosition());
            if (game.getPlayers().get(currentPlayer).getPosition() == game.getBoardSize()) {
                System.out.println("Player " + game.getPlayers().get(currentPlayer).getId() + " wins");
                finishedPlayers.add(game.getPlayers().get(currentPlayer).getId());
                if (finishedPlayers.size() == game.getNumberOfPlayers() - 1) {
                    isGameCompleted = true;
                }
            }
        } else {
            System.out.println("Cannot move");
        }

        if (isGameCompleted) {
            System.out.println("Game completed");
            return;
        }

        currentPlayer = (currentPlayer + 1) % game.getNumberOfPlayers();

        while (finishedPlayers.contains(game.getPlayers().get(currentPlayer).getId())) {
            currentPlayer = (currentPlayer + 1) % game.getNumberOfPlayers();
        }
    }

    private int isSnakePresent(int position) {
        for (int i = 0; i < game.getSnakes().size(); i++) {
            if (game.getSnakes().get(i).getStart() == position) {
                System.out.println("Snake present at " + position);
                return game.getSnakes().get(i).getEnd();
            }
        }
        return position;
    }

    private int isLadderPresent(int position) {
        for (int i = 0; i < game.getLadders().size(); i++) {
            if (game.getLadders().get(i).getStart() == position) {
                System.out.println("Ladder present at " + position);
                return game.getLadders().get(i).getEnd();
            }
        }
        return position;
    }
}
