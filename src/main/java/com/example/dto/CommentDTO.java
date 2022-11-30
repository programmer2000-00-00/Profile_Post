package com.example.dto;

import com.example.entity.PostEntity;
import com.example.entity.ProfileEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {

    private Integer id;

    private String content;

    private PostDTO postDTO;
    private Integer postID;

    private ProfileDTO profileDTO;
    private  Integer profileID;

    private LocalDate createdDate;

    public CommentDTO() {
    }

    public CommentDTO(Integer id, String content, LocalDate createdDate) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
    }


    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", postDTO=" + postDTO +
                ", postID=" + postID +
                ", profileDTO=" + profileDTO +
                ", profileID=" + profileID +
                ", createdDate=" + createdDate +
                '}';
    }
}
