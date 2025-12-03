package com.blogging.blogging_platform.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Blog Entity
 */
@Entity
@Data
@Table(name = "blog_posts")
public class BlogPost
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @NotBlank
  @Size(max = 150)
  private String title;

  @NotBlank
  @Size(max = 1500)
  private String content;

  @NotBlank
  @Size(max = 50)
  private String author;

  @Size(max = 30)
  private String tag;

  @Column(name = "publishing_date")
  private LocalDateTime publishingDate;
}
