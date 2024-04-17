package com.example.snakeandladder.service;

public class DiceService {

    public int getTotalRollValue(int diceCount) {
        int value = 0;
        for (int i = 0; i < diceCount; i++) {
            value += rollDice();
        }

        if (value == 6 * diceCount) {
            for (int i = 0; i < diceCount; i++) {
                value += rollDice();
            }
        }
        if (value == 12 * diceCount) {
            for (int i = 0; i < diceCount; i++) {
                value += rollDice();
            }
        }
        if (value == 18 * diceCount) {
            return 0;
        }
        return value;
    }

    public int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }
}
