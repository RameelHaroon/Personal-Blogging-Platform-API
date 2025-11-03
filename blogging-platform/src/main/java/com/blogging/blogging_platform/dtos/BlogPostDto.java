package com.blogging.blogging_platform.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class BlogPostDto {
    UUID id;
    String title;
    String content;
    String author;
    String tag;
    @JsonIgnore
    LocalDateTime publishingDate;

    public BlogPostDto() {}

    public BlogPostDto( UUID id, String tag, String author, String content, String title, LocalDateTime publishingDate) {
        this.publishingDate = publishingDate;
        this.tag = tag;
        this.author = author;
        this.content = content;
        this.title = title;
        this.id = id;
    }
}
