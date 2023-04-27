package com.lld.tictac.models;

import lombok.Data;

import java.util.*;

import static com.lld.tictac.constants.GlobalConstants.EMPTY_CHAR;

@Data
public class Game {
    private Deque<String> players;
    private char[][] board;
    private Map<String, Character> userMarkerMap;
    private Map<Character, String> markerToUserMap;
    private String winner;
    private GameStatus gameStatus;
    private short marked = 0;

    public Game(Deque<String> players, Map<String, Character> userMarkerMap, Cell<Short, Short> dimensions) {
        this.players = players;
        this.board = new char[dimensions.getRow()][dimensions.getColumn()];
        this.userMarkerMap = userMarkerMap;
        this.markerToUserMap = initReverseMarkerUserMap(userMarkerMap);
        this.gameStatus = GameStatus.NOT_STARTED;
        this.winner = null;
        initBoard();
    }

    private void initBoard() {
        for(int i=0; i< board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                board[i][j] = EMPTY_CHAR;
            }
        }
    }

    private Map<Character, String> initReverseMarkerUserMap(Map<String, Character> userMarkerMap) {
        Map<Character, String> resMap = new HashMap<>();
        for(Map.Entry<String, Character> entry : userMarkerMap.entrySet()) {
            resMap.computeIfAbsent(entry.getValue(), p -> entry.getKey());
        }
        return resMap;
    }

    public void mark(String userId, Cell<Short, Short> position) {
        if(marked >= board.length*board[0].length) {
            gameStatus = GameStatus.COMPLETED;
            return;
        }
        char marker = userMarkerMap.get(userId);
        if(position.getRow() <0 || position.getRow() >= board.length || position.getColumn() <0 ||
                position.getColumn() >= board[0].length) {
            throw new RuntimeException("InvalidMove");
        }
        if(board[position.getRow()][position.getColumn()] != EMPTY_CHAR) {
            throw new RuntimeException("Position already marked");
        }
        board[position.getRow()][position.getColumn()] = marker;
        marked++;
    }

    public Optional<String> getWinner() {
        if(null != winner) { return Optional.of(winner);}
        checkRowsForWinner();
        if(null != winner) {return Optional.of(winner);}
        checkColumnsForWinner();
        if(null != winner) {return Optional.of(winner);}
        checkDiagonalForWinner();
        if(null != winner) {return Optional.of(winner);}
        checkReverseDiagonalForWinner();
        return Optional.ofNullable(winner);
    }

    private void checkReverseDiagonalForWinner() {
        boolean reverseDiagonalMatch = true;
        char winningChar = EMPTY_CHAR;
        for(int i=0; i< board.length; i++) {
            for(int j=0; j< board[0].length; j++) {
                char curr = board[i][board.length-1];
                winningChar = curr;
                if (i+j+1 != board.length) {
                    continue;
                }
                if(EMPTY_CHAR == curr || board[i][i] != curr) {
                    reverseDiagonalMatch = false;
                    break;
                }
            }
        }
        if(reverseDiagonalMatch) {
            this.winner = markerToUserMap.get(winningChar);
            this.gameStatus = GameStatus.COMPLETED;
        }
    }

    private void checkDiagonalForWinner() {
        for(int i=1; i< board.length; i++) {
            char curr = board[0][0];
            if(EMPTY_CHAR==curr || board[i][i] != curr) {
                break;
            }
            this.winner = markerToUserMap.get(curr);
            this.gameStatus = GameStatus.COMPLETED;
        }
    }

    private void checkColumnsForWinner() {
        for(int i=0; i< board[0].length; i++) {
            boolean allCharInRowMatch = true;
            char curr = board[0][i];
            if(EMPTY_CHAR==curr) {
                continue;
            }
            for(int j=1; j< board[0].length; j++) {
                if(board[j][i] != curr) {
                    allCharInRowMatch = false;
                    break;
                }
            }
            if(allCharInRowMatch) {
                this.winner = markerToUserMap.get(curr);
                this.gameStatus = GameStatus.COMPLETED;
                break;
            }
        }
    }

    private void checkRowsForWinner() {
        for(int i=0; i< board.length; i++) {
            boolean allCharInRowMatch = true;
            char curr = board[i][0];
            if(EMPTY_CHAR==curr) {
                continue;
            }
            for(int j=1; j< board.length; j++) {
                if(board[i][j] != curr) {
                    allCharInRowMatch = false;
                    break;
                }
            }
            if(allCharInRowMatch) {
                this.winner = markerToUserMap.get(curr);
                this.gameStatus = GameStatus.COMPLETED;
                break;
            }
        }
    }
}
