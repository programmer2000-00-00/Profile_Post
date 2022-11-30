package com.example.mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapperByPost1 {
    private Integer postId;
    private String postTitle;
    private Integer profileId;
    private String profileName;

    public MapperByPost1(Integer postId, String postTitle, Integer profileId, String profileName) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.profileId = profileId;
        this.profileName = profileName;
    }
}
