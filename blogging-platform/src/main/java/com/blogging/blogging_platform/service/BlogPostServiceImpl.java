package com.blogging.blogging_platform.service;

import com.blogging.blogging_platform.dtos.BlogPostDto;
import com.blogging.blogging_platform.entity.BlogPost;
import com.blogging.blogging_platform.mapper.BlogPostMapper;
import com.blogging.blogging_platform.repository.BlogPostRepository;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class BlogPostServiceImpl implements BlogPostService{

    private final BlogPostRepository blogPostRepository;
    private final BlogPostMapper blogPostMapper;

    public BlogPostServiceImpl(BlogPostRepository blogPostRepository, BlogPostMapper blogPostMapper) {
        this.blogPostRepository = blogPostRepository;
        this.blogPostMapper = blogPostMapper;
    }

    @Override
    public BlogPostDto createPost(BlogPostDto blogPostDto) {
        BlogPost entity = blogPostMapper.toEntity(blogPostDto);
        BlogPost saved = blogPostRepository.save(entity);
        return blogPostMapper.toDto(saved);
    }

    @Override
    public List<BlogPostDto> getAllPosts() {
        return blogPostMapper.toDtoList(blogPostRepository.findAll());
    }

    @Override
    public BlogPostDto getPostById(UUID id) {
        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        return blogPostMapper.toDto(blogPost);
    }

    @Override
    public List<BlogPostDto> getPostsByAuthor(String author) {
        return blogPostMapper.toDtoList(blogPostRepository.findByAuthor(author));
    }

    @Override
    public BlogPostDto getPostByTitle(String title){
        BlogPost blogPost = blogPostRepository.findByTitle(title);
        return blogPostMapper.toDto(blogPost);
    }

    @Override
    public BlogPostDto updatePost(UUID id, BlogPostDto blogPostDto) {
        BlogPost existing = blogPostRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));

        existing.setTitle(blogPostDto.title());
        existing.setContent(blogPostDto.content());
        existing.setAuthor(blogPostDto.author());
        existing.setTag(blogPostDto.tag());
        existing.setPublishingDate(blogPostDto.publishingDate());

        BlogPost updated = blogPostRepository.save(existing);
        return blogPostMapper.toDto(updated);
    }

    @Override
    public void deletePost(UUID id) {
        blogPostRepository.deleteById(id);
    }
}
