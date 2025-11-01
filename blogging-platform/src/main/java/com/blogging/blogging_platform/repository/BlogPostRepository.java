package com.blogging.blogging_platform.repository;

import com.blogging.blogging_platform.entity.BlogPost;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository class for blogs
 */
public interface BlogPostRepository extends JpaRepository<BlogPost, UUID> {

    @Query("SELECT b FROM BlogPost b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<BlogPost> findByTitle(@Param("title") String title);

    @Query("SELECT b FROM BlogPost b WHERE LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))")
    List<BlogPost> findByAuthor(@Param("author") String author);

    @Query("SELECT b FROM BlogPost b WHERE b.publishingDate = :publishingDate")
    List<BlogPost> findByPublishingDate(@Param("publishingDate") LocalDateTime publishingDate);
}
