package com.lld.snake_ladder;

import com.lld.snake_ladder.controller.GameController;
import com.lld.snake_ladder.model.*;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@SpringBootApplication
public class SnakeLadderApplication {

    private static final String EMPTY_STRING_DELIMITER = " ";

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(SnakeLadderApplication.class, args);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String numberOfSnakesStr = bufferedReader.readLine();
        short numberOfSnakes = Short.parseShort(numberOfSnakesStr);
        List<Snake> snakes = new ArrayList<>();
        while(numberOfSnakes-- > 0) {
            String headAndTailOfSnakes = bufferedReader.readLine();
            String[] headAndTailOfSnakesPair = headAndTailOfSnakes.split(" ");
            if(headAndTailOfSnakesPair.length != 2) {
                numberOfSnakes++;
                throw new Exception("please enter head and tail position correctly");
            }
            short mouth = Short.parseShort(headAndTailOfSnakesPair[0]);
            short tail = Short.parseShort(headAndTailOfSnakesPair[1]);
            if(mouth > 100 || tail > 99 || tail >=mouth) {
                numberOfSnakes++;
                throw new Exception("please enter head and tail position correctly");
            }
            Snake snake = new Snake();
            snake.setColor(Color.GREEN);
            snake.setId(UUID.randomUUID().toString());
            snake.setMouth(Short.parseShort(headAndTailOfSnakesPair[0]));
            snake.setTail(Short.parseShort(headAndTailOfSnakesPair[1]));
            snakes.add(snake);
        }

        String numberOfLaddersStr = bufferedReader.readLine();
        short numberOfLadders = Short.parseShort(numberOfLaddersStr);
        List<Ladder> ladders = new ArrayList<>();
        while(numberOfLadders-- > 0) {
            String startEndLadder = bufferedReader.readLine();
            String[] startEndLadderPair = startEndLadder.split(EMPTY_STRING_DELIMITER);
            if(startEndLadderPair.length != 2) {
                numberOfLadders++;
                throw new Exception("please enter head and tail position correctly");
            }
            short start = Short.parseShort(startEndLadderPair[0]);
            short end = Short.parseShort(startEndLadderPair[1]);
            if(start > 99 || end > 100 || start >=end) {
                numberOfLadders++;
                throw new Exception("please enter head and tail position correctly");
            }
            Ladder ladder = new Ladder();
            ladder.setId(UUID.randomUUID().toString());
            ladder.setStart(start);
            ladder.setEnd(end);
            ladders.add(ladder);
        }

        String numberOfPlayersStr = bufferedReader.readLine();
        short numberOfPlayers = Short.parseShort(numberOfPlayersStr);
        Deque<User> players = new LinkedList<>();
        Set<User> playersSet = new HashSet<>();
        while(numberOfPlayers-- > 0) {
            String name = bufferedReader.readLine();
            if(playersSet.contains(name)) {
                numberOfPlayers++;
                throw new Exception("user with same name is already part of this game... please reenter user name");
            }
            if(name.isBlank()) {
                numberOfPlayers++;
                throw new Exception("please enter user name correctly");
            }
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setName(name);
            players.add(user);
        }
        Board board = new Board(UUID.randomUUID().toString(), snakes, ladders, new Dice((short) 6));
        Map<User, Short> playerToPositionMap = new HashMap<>();
        for(User user : players) {
            playerToPositionMap.put(user, (short) 0);
        }
        Game game = Game.builder().board(board).id(UUID.randomUUID().toString()).players(players).
                status(Game.Status.LOADING).playerToPositionMap(playerToPositionMap).build();
        GameController gameController = new GameController(game);
        gameController.play();
    }
}
