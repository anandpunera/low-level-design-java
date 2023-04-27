package com.lld.tictac.controller;

import com.lld.tictac.models.Cell;
import com.lld.tictac.models.Game;
import com.lld.tictac.models.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Data
@AllArgsConstructor
@Slf4j
public class GameController {
    private Game game;

    public Optional<String> makeMove(Cell<Short, Short> position, String userId) {
        game.mark(userId, position);
        return game.getWinner();
    }

    public void playGame() {
        if(game.getGameStatus() == GameStatus.NOT_STARTED)
            game.setGameStatus(GameStatus.RUNNING);
        while(game.getGameStatus() != GameStatus.COMPLETED) {
            String turn = game.getPlayers().getFirst();
            Random random = new Random();
            Cell<Short, Short> pos = new Cell<>((short) (Math.abs(random.nextInt())%game.getBoard().length),
                    (short) (Math.abs(random.nextInt()%game.getBoard()[0].length)));
            try {
                game.mark(turn, pos);
            } catch (Exception e) {
                log.error(" Exception:: {}", e.getMessage());
                continue;
            }
            System.out.println(turn+" marked "+ pos + "  "+game.getUserMarkerMap().get(turn));
            BoardPrinter.printGameBoard(game);
            if(game.getWinner().isPresent()) {
                System.out.println(game.getWinner().get()+" is the winner ");
            }
            game.getPlayers().removeFirst();
            game.getPlayers().addLast(turn);
        }
        if(!game.getWinner().isPresent()) {
            System.out.println(" Game tied ");
            BoardPrinter.printGameBoard(game);
        }
    }
}
