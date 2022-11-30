package com.example.mapper;

import com.example.dto.CommentDTO;
import com.example.dto.PostDTO;
import com.example.dto.ProfileDTO;
import com.example.entity.CommentEntity;
import com.example.entity.PostEntity;
import com.example.entity.ProfileEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UniversalMapper {
    /*c.post.id,c.post.title,c.id,c.content,c.createdDate,c.profile.id,c.profile.name*/

    private Integer postId;
    private String postTitle;
    private Integer commentId;
    private String content;
    private LocalDate createdDate;
    private Integer profileId;
    private String profileName;

    public UniversalMapper(Integer postId, String postTitle, Integer commentId, String content, LocalDate createdDate, Integer profileId, String profileName) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.commentId = commentId;
        this.content = content;
        this.createdDate = createdDate;
        this.profileId = profileId;
        this.profileName = profileName;
    }
}
