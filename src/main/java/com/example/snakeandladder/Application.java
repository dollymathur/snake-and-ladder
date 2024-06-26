package com.example.snakeandladder;

import com.example.snakeandladder.model.*;
import com.example.snakeandladder.service.DiceService;
import com.example.snakeandladder.service.GameService;

import java.util.ArrayList;
import java.util.List;

public class Application {
    // Question - https://workat.tech/machine-coding/article/how-to-practice-for-machine-coding-kp0oj3sw2jca
    // Solution - https://workat.tech/machine-coding/editorial/how-to-design-snake-and-ladder-machine-coding-ehskk9c40x2w

    public static void main(String[] args) {
        System.out.println("Assigning values");

        List<Player> players = new ArrayList<>();
        players.add(new Player(1, "Player 1", 0));
        players.add(new Player(2, "Player 2", 0));
        players.add(new Player(3, "Player 3", 0));

        List<Snake> snakes = new ArrayList<>();
        snakes.add(new Snake(23, 10));
        snakes.add(new Snake(19, 11));
        snakes.add(new Snake(15, 3));

        List<Ladder> ladders = new ArrayList<>();
        ladders.add(new Ladder(4, 14));
        ladders.add(new Ladder(16, 24));
        ladders.add(new Ladder(9, 20));

        Game game = new GameWithMultipleDice(25, players.size(), players, snakes, ladders, 2);
        //Game game = new Game(25, players.size(), players, snakes, ladders);

        GameService gameService = new GameService(game, 0, false, new DiceService());

        gameService.startGame();

        System.out.println("Game finished");
    }
}
