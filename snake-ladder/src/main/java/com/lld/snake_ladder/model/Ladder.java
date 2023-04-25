package com.lld.snake_ladder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ladder {
    private String id;
    private short start;
    private short end;
}
