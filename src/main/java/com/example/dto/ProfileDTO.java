package com.example.dto;

import com.example.entity.ContactEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {

    private Integer id;

    private String name;
    private String surname;
    private Integer contactId;
    private ContactDTO contactDTO;

    public ProfileDTO() {
    }

    public ProfileDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProfileDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", contactId=" + contactId +
                ", contactDTO=" + contactDTO +
                '}';
    }
}
