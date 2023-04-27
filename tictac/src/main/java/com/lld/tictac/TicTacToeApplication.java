package com.lld.tictac;

import com.lld.tictac.controller.BoardPrinter;
import com.lld.tictac.controller.GameController;
import com.lld.tictac.models.Cell;
import com.lld.tictac.models.Game;
import com.lld.tictac.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@SpringBootApplication
public class TicTacToeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicTacToeApplication.class, args);
        User user1 = new User("ANAND","acp831@gmail.com");
        User user2 = new User("ISHAAN", "ishaan.punera@gmail.com");
        Deque<String> players = new LinkedList<>();
        players.add(user1.getName());
        players.add(user2.getName());
        Map<String, Character> userMarkerMap = new HashMap<>();
        userMarkerMap.put(user1.getName(), '0');
        userMarkerMap.put(user2.getName(), 'X');
        Game game = new Game(players, userMarkerMap, new Cell<>((short) 3, (short) 3));
        GameController gameController = new GameController(game);
        gameController.playGame();
    }
}
