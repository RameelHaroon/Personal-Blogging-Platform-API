package com.blogging.blogging_platform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * Blog Entity
 */
@Entity
@Getter
@Setter
@Table(name = "blog_posts")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @Size(max = 150)
    private String title;

    @NotBlank
    @Lob
    private String content;

    @NotBlank
    @Size(max = 50)
    private String author;

    @Size(max = 30)
    private String tag;

    @Column(name = "publishing_date")
    private LocalDateTime publishingDate;
}
