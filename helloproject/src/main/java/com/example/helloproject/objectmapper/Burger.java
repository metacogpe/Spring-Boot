package com.example.helloproject.objectmapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor // default 생성자
@AllArgsConstructor
@ToString
@Getter
public class Burger {
    private String name;
    private int price;
    private List<String> ingredients;
}
