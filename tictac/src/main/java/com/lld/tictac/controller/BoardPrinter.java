package com.lld.tictac.controller;

import com.lld.tictac.models.Game;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BoardPrinter {

    public static void printGameBoard(Game game) {
        System.out.println(" ============================Latest Board View============================ ");
        for(int i=0; i< game.getBoard().length; i++) {
            for(int j=0; j< game.getBoard()[0].length; j++) {
                System.out.print(game.getBoard()[i][j]+"   ");
            }
            System.out.println(" ");
        }
    }
}
