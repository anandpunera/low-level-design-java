package com.lld.tictac;

import com.lld.tictac.controller.BoardPrinter;
import com.lld.tictac.controller.GameController;
import com.lld.tictac.models.Cell;
import com.lld.tictac.models.Game;
import com.lld.tictac.models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class TicTacToeApplicationTests {

    @Test
    void testHappy() {
        User user1 = new User("ANAND","acp831@gmail.com");
        User user2 = new User("ISHAAN", "ishaan.punera@gmail.com");
        Deque<String> players = new LinkedList<>();
        players.add(user1.getName());
        players.add(user2.getName());
        Map<String, Character> userMarkerMap = new HashMap<>();
        userMarkerMap.put(user1.getName(), '0');
        userMarkerMap.put(user2.getName(), 'X');
        Game game = new Game(players, userMarkerMap, new Cell<>((short) 3, (short) 3));
        game.mark(user1.getName(), new Cell<>((short) 0, (short) 1));
        game.mark(user2.getName(), new Cell<>((short) 0, (short) 0));
        game.mark(user1.getName(), new Cell<>((short) 1, (short) 1));
        game.mark(user2.getName(), new Cell<>((short) 2, (short) 0));
        game.mark(user1.getName(), new Cell<>((short) 2, (short) 1));
        assert game.getWinner().isPresent() && game.getWinner().get().equals(user1.getName());
    }
}
