package com.lld.snake_ladder.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<T1, T2> {
    T1 first;
    T2 second;
}
