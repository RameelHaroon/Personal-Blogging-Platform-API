package com.blogging.blogging_platform.bootstrap;

import com.blogging.blogging_platform.entity.BlogPost;
import com.blogging.blogging_platform.repository.BlogPostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class to load data on startup for development
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final BlogPostRepository blogPostRepository;

    public DataLoader(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Create sample blog posts
        BlogPost post1 = new BlogPost();
        post1.setTitle("Getting Started with Spring Boot");
        post1.setContent("Spring Boot makes it easy to create stand-alone, production-grade applications...");
        post1.setAuthor("James");
        post1.setTag("Spring");
        post1.setPublishingDate(LocalDateTime.now());

        BlogPost post2 = new BlogPost();
        post2.setTitle("Understanding JPA and Hibernate");
        post2.setContent("JPA is a specification for accessing, persisting, and managing data between Java objects...");
        post2.setAuthor("Charlie");
        post2.setTag("JPA");
        post2.setPublishingDate(LocalDateTime.now());

        BlogPost post3 = new BlogPost();
        post3.setTitle("Dockerizing Your Spring Boot App");
        post3.setContent("Learn how to containerize your Spring Boot applications using Docker and Docker Compose...");
        post3.setAuthor("Tina");
        post3.setTag("Docker");
        post3.setPublishingDate(LocalDateTime.now());

        // Save all posts
        blogPostRepository.saveAll(List.of(post1, post2, post3));
    }
}
