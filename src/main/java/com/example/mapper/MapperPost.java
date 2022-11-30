package com.example.mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapperPost {

    private Integer id;
    private String name;
    private String title;

    public MapperPost(Integer id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }
}
