package com.example.mapper;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MapperBYPost {
    private Integer profileID;
    private Integer postID;
    private LocalDate localDate;

    public MapperBYPost() {
    }

    public MapperBYPost(Integer profileID, Integer postID, LocalDate localDate) {
        this.profileID = profileID;
        this.postID = postID;
        this.localDate = localDate;
    }
}
