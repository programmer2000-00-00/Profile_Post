package com.example.mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Mapper {
    private Integer profileId;
    private String profileName;
    private String phone;

    public Mapper() {
    }

    public Mapper(Integer profileId, String profileName, String phone) {
        this.profileId = profileId;
        this.profileName = profileName;
        this.phone = phone;
    }
}
