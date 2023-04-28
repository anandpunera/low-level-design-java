package com.lld.clear.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddUserDto {
    private String name;
    private String email;
}
