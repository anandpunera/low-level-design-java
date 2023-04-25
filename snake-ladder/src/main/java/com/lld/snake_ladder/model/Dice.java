package com.lld.snake_ladder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dice {
    private short max = 6;

    public short roll() {
        return (short) (new Random().nextInt(6) + 1);
    }
}
