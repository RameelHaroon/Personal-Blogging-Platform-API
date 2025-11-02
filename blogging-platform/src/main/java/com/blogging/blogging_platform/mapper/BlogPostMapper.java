package com.blogging.blogging_platform.mapper;

import com.blogging.blogging_platform.dtos.BlogPostDto;
import com.blogging.blogging_platform.entity.BlogPost;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlogPostMapper {
    BlogPostDto toDto(BlogPost blogPost);
    BlogPost toEntity(BlogPostDto blogPost);
    List<BlogPostDto> toDtoList(List<BlogPost> blogPosts);
}
