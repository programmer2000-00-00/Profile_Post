package com.example.dto;

import com.example.entity.ProfileEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDTO {

    private Integer id;
    private String title;
    private String surname;
    private String content;
    private LocalDate createdDate;
    private ProfileDTO profileDTO;
    private Integer profileID;

    public PostDTO() {
    }

    public PostDTO(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", surname='" + surname + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", profileDTO=" + profileDTO +
                ", profileID=" + profileID +
                '}';
    }
}
