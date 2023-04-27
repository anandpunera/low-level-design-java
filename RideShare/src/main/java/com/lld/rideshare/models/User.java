package com.lld.rideshare.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private Gender gender;
    private short age;
    private List<RideStats> rideStatsList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && name.equals(user.name) && gender == user.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, age);
    }
}
