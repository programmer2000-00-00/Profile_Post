package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String surname;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contact_id")
    private ContactEntity contact;


    @OneToMany(mappedBy = "profile")
    private List<PostEntity> post;

    public ProfileEntity() {
    }

    public ProfileEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
