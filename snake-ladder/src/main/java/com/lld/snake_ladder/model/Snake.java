package com.lld.snake_ladder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Snake {
    private String id;
    private Color color;
    private short mouth;
    private short tail;
}
