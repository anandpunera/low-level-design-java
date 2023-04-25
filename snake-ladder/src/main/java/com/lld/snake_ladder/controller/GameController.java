package com.lld.snake_ladder.controller;

import com.lld.snake_ladder.model.Game;
import com.lld.snake_ladder.model.Ladder;
import com.lld.snake_ladder.model.Snake;
import com.lld.snake_ladder.model.User;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class GameController {

    private Game game;

    private static final String MOVE_STR = "%s rolled a %d and moved from %d to %d";
    private static final String WINNER_STR = "%s wins the game";
    private static final String TURN_STR = "It's %s 's turn \n ----------------------------------------------------------------- \n";

    public void play() {
        while (game.getWinner() == null) {
            User turn = game.getPlayers().pollFirst();
            System.out.println(String.format(TURN_STR, turn.getName()));
            short dice = game.getBoard().getDice().roll();
            short position = game.getPlayerToPositionMap().get(turn);
            Pair<Boolean, Short> checkSnakeBite = checkSnakeBite(turn, dice);
            if(checkSnakeBite.getFirst()) {
                game.getPlayerToPositionMap().put(turn, checkSnakeBite.getSecond());
            } else {
                Pair<Boolean, Short> checkLadderClimb = checkLadderClimb(turn, dice);
                game.getPlayerToPositionMap().put(turn, checkLadderClimb.getSecond());
            }
            if(100 ==game.getPlayerToPositionMap().get(turn)) {
                System.out.println(String.format(MOVE_STR, turn.getName(), dice, position, game.getPlayerToPositionMap().get(turn)));
                game.setWinner(turn);
                System.out.println(String.format(WINNER_STR, game.getWinner().getName()));
                return;
            } else {
                System.out.println(String.format(MOVE_STR, turn.getName(), dice, position, game.getPlayerToPositionMap().get(turn)));
            }
            game.getPlayers().addLast(turn);
        }

    }

    private Pair<Boolean, Short> checkSnakeBite(User turn, short dice) {
        int afterMove = game.getPlayerToPositionMap().get(turn) + dice;
        boolean snakeBite = false;
        if(afterMove == 100) {
            return new Pair<>(snakeBite, (short) 100);
        }
        if(afterMove > 100) {
            return new Pair<>(snakeBite, game.getPlayerToPositionMap().get(turn));
        }
        for(Snake snake: game.getBoard().getSnakes()) {
            if(snake.getMouth() == afterMove) {
                afterMove = snake.getTail();
                snakeBite = true;
                break;
            }
        }
        return new Pair<>(snakeBite, (short) afterMove);
    }

    private Pair<Boolean, Short> checkLadderClimb(User turn, short dice) {
        int afterMove = game.getPlayerToPositionMap().get(turn) + dice;
        boolean ladderClimb = false;
        if(afterMove == 100) {
            return new Pair<>(ladderClimb, (short) 100);
        }
        if(afterMove > 100) {
            return new Pair<>(ladderClimb, game.getPlayerToPositionMap().get(turn));
        }
        for(Ladder ladder: game.getBoard().getLadders()) {
            if(ladder.getStart() == afterMove) {
                afterMove = ladder.getEnd();
                ladderClimb = true;
                break;
            }
        }
        return new Pair<>(ladderClimb, (short) afterMove);
    }
}
