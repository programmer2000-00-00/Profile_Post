package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UniversalMapperDTO {
    private PostDTO postDTO;
    private CommentDTO commentDTO;
    private ProfileDTO profileDTO;
}
