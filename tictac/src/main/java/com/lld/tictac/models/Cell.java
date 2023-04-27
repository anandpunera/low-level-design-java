package com.lld.tictac.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cell<T1, T2> {
    T1 row;
    T2 column;
}
