package com.blogging.blogging_platform.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record BlogPostDto(
        UUID id,
        String title,
        String content,
        String author,
        String tag,
        LocalDateTime publishingDate
) {
}
