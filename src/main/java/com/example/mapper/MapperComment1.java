package com.example.mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapperComment1 {
    private Integer commnetId;
    private String content;
    private Integer profiliId;
    private String  name;

    public MapperComment1(Integer commnetId, String content, Integer profiliId, String name) {
        this.commnetId = commnetId;
        this.content = content;
        this.profiliId = profiliId;
        this.name = name;
    }
}
