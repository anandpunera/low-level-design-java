package com.lld.snake_ladder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;


@Data
@Builder
public class Game {
    private String id;
    private Board board;
    private Deque<User> players;
    private Map<User, Short> playerToPositionMap = new HashMap<>();
    private User winner;
    private Status status;

    public enum Status {
        LOADING,
        RUNNING,
        COMPLETED,
        ABANDONED
    }
}
