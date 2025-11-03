package com.blogging.blogging_platform.service;

import com.blogging.blogging_platform.dtos.BlogPostDto;
import com.blogging.blogging_platform.entity.BlogPost;
import com.blogging.blogging_platform.mapper.BlogPostMapper;
import com.blogging.blogging_platform.repository.BlogPostRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final BlogPostMapper blogPostMapper;

    public BlogPostServiceImpl(BlogPostRepository blogPostRepository, BlogPostMapper blogPostMapper) {
        this.blogPostRepository = blogPostRepository;
        this.blogPostMapper = blogPostMapper;
    }

    @Override
    @CachePut(value = "BLOG_POST_CACHE", key = "#result.id()")
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
    @Cacheable(value = "BLOG_POST_CACHE", key = "#id")
    public BlogPostDto getPostById(UUID id) {
        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
        return blogPostMapper.toDto(blogPost);
    }

    @Override
    public List<BlogPostDto> getPostsByAuthor(String author) {
        return blogPostMapper.toDtoList(blogPostRepository.findByAuthor(author));
    }

    @Override
    public BlogPostDto getPostByTitle(String title) {
        BlogPost blogPost = blogPostRepository.findByTitle(title);
        return blogPostMapper.toDto(blogPost);
    }

    @Override
    @CachePut(value = "BLOG_POST_CACHE", key = "#result.id()")
    public BlogPostDto updatePost(UUID id, BlogPostDto blogPostDto) {
        BlogPost existing = blogPostRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));

        existing.setTitle(blogPostDto.getTitle());
        existing.setContent(blogPostDto.getContent());
        existing.setAuthor(blogPostDto.getAuthor());
        existing.setTag(blogPostDto.getTag());
        existing.setPublishingDate(blogPostDto.getPublishingDate());

        BlogPost updated = blogPostRepository.save(existing);
        return blogPostMapper.toDto(updated);
    }

    @Override
    @CacheEvict(value = "BLOG_POST_CACHE", key = "#id")
    public void deletePost(UUID id) {

        if (!blogPostRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
        blogPostRepository.deleteById(id);
    }
}
