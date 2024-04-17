package com.example.snakeandladder.service;

import com.example.snakeandladder.model.Game;
import com.example.snakeandladder.model.GameWithMultipleDice;
import com.example.snakeandladder.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GameService {

    private final GameWithMultipleDice gameWithMultipleDice;
    private int currentPlayer;
    private final List<Integer> finishedPlayers = new ArrayList<>();
    private boolean isGameCompleted;
    private final DiceService diceService;

    public GameService(GameWithMultipleDice gameWithMultipleDice, int currentPlayer, boolean isGameCompleted, DiceService diceService) {
        this.gameWithMultipleDice = gameWithMultipleDice;
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
        int diceValue = diceService.getTotalRollValue(gameWithMultipleDice.diceCount);
        System.out.println("Player " + gameWithMultipleDice.getPlayers().get(currentPlayer).getId() + " rolled a " + diceValue);

        if (gameWithMultipleDice.getPlayers().get(currentPlayer).getPosition() + diceValue <= gameWithMultipleDice.getBoardSize()) {

            int positionAfterDiceRoll = gameWithMultipleDice.getPlayers().get(currentPlayer).getPosition() + diceValue;

            int actionMove;
            actionMove = isSnakePresent(positionAfterDiceRoll);
            if (actionMove == positionAfterDiceRoll) {
                actionMove = isLadderPresent(positionAfterDiceRoll);
            }


            gameWithMultipleDice.getPlayers().get(currentPlayer).setPosition(actionMove);

            System.out.println(" moved to " + gameWithMultipleDice.getPlayers().get(currentPlayer).getPosition());
            if (gameWithMultipleDice.getPlayers().get(currentPlayer).getPosition() == gameWithMultipleDice.getBoardSize()) {
                System.out.println("Player " + gameWithMultipleDice.getPlayers().get(currentPlayer).getId() + " wins");
                finishedPlayers.add(gameWithMultipleDice.getPlayers().get(currentPlayer).getId());
                if (finishedPlayers.size() == gameWithMultipleDice.getNumberOfPlayers() - 1) {
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

        currentPlayer = (currentPlayer + 1) % gameWithMultipleDice.getNumberOfPlayers();

        while (finishedPlayers.contains(gameWithMultipleDice.getPlayers().get(currentPlayer).getId())) {
            currentPlayer = (currentPlayer + 1) % gameWithMultipleDice.getNumberOfPlayers();
        }

    }

    private int isSnakePresent(int position) {
        for (int i = 0; i < gameWithMultipleDice.getSnakes().size(); i++) {
            if (gameWithMultipleDice.getSnakes().get(i).getStart() == position) {
                System.out.println("Snake present at " + position);
                return gameWithMultipleDice.getSnakes().get(i).getEnd();
            }
        }
        return position;
    }

    private int isLadderPresent(int position) {
        for (int i = 0; i < gameWithMultipleDice.getLadders().size(); i++) {
            if (gameWithMultipleDice.getLadders().get(i).getStart() == position) {
                System.out.println("Ladder present at " + position);
                return gameWithMultipleDice.getLadders().get(i).getEnd();
            }
        }
        return position;
    }
}
