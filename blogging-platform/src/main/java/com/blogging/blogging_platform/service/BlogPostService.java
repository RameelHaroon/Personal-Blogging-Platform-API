package com.blogging.blogging_platform.service;

import com.blogging.blogging_platform.dtos.BlogPostDto;

import java.util.List;
import java.util.UUID;

public interface BlogPostService {
    BlogPostDto createPost(BlogPostDto blogPostDto);
    List<BlogPostDto> getAllPosts();
    BlogPostDto getPostById(UUID id);
    List<BlogPostDto> getPostsByAuthor(String author);
    BlogPostDto getPostByTitle(String title);
    BlogPostDto updatePost(UUID id, BlogPostDto blogPostDto);
    void deletePost(UUID id);
}
