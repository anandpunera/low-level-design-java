package com.lld.snake_ladder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private String id;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private Dice dice;
}
