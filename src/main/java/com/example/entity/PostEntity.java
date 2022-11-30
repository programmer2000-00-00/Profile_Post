package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String title;
    @Column
    private String surname;
    @Column
    private String content;
    @Column
    private LocalDate createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profile_id")
    private ProfileEntity profile;

    public PostEntity() {
    }
    public PostEntity(String title, LocalDate createdDate) {
        this.title = title;
        this.createdDate = createdDate;
    }

    public PostEntity(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public PostEntity(String title) {
        this.title = title;
    }
    public PostEntity(Integer id, String title, String surname, String content, LocalDate createdDate, ProfileEntity profile) {
        this.id = id;
        this.title = title;
        this.surname = surname;
        this.content = content;
        this.createdDate = createdDate;
        this.profile = profile;
    }


    public PostEntity(Integer id) {
        this.id = id;
    }

    public PostEntity(ProfileEntity profile) {
        this.profile = profile;
    }

    public PostEntity(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}

