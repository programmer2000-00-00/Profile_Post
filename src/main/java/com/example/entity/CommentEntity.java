package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private PostEntity post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profile_id")
    private ProfileEntity profile;

    @Column
    private LocalDate createdDate;

    public CommentEntity() {
    }

    public CommentEntity(Integer id, String content, LocalDate createdDate) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
    }
}

