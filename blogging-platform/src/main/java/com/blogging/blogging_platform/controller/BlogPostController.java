package com.blogging.blogging_platform.controller;

import com.blogging.blogging_platform.dtos.BlogPostDto;
import com.blogging.blogging_platform.service.BlogPostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogging/api")
@CrossOrigin
public class BlogPostController {

    private final BlogPostServiceImpl blogPostServiceImpl;

    public BlogPostController(BlogPostServiceImpl blogPostServiceImpl) {
        this.blogPostServiceImpl = blogPostServiceImpl;
    }

    @GetMapping("/v1/posts")
    public List<BlogPostDto> findAll() {
        return this.blogPostServiceImpl.getAllPosts();
    }

    @GetMapping("/v1/posts/{id}")
    public BlogPostDto findById(@PathVariable UUID id) {
        return this.blogPostServiceImpl.getPostById(id);
    }

    @GetMapping("/v1/posts/author/{author}")
    public List<BlogPostDto> findByAuthor(@PathVariable String author) {
        return this.blogPostServiceImpl.getPostsByAuthor(author);
    }

    @GetMapping("/v1/posts/title/{title}")
    public BlogPostDto findByTitle(@PathVariable String title) {
        return this.blogPostServiceImpl.getPostByTitle(title);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/v1/posts")
    public BlogPostDto createPost(@RequestBody BlogPostDto post){
        return this.blogPostServiceImpl.createPost(post);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/v1/posts/{id}")
    public void update(@RequestBody BlogPostDto post, @PathVariable UUID id) {
        this.blogPostServiceImpl.updatePost(id, post);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/v1/posts/{id}")
    public void delete(@PathVariable UUID id) {
        this.blogPostServiceImpl.deletePost(id);
    }
}
