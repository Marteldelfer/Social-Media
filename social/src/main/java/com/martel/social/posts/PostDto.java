package com.martel.social.posts;

import org.springframework.web.multipart.MultipartFile;

public record PostDto(
    MultipartFile image,
    String description
) {}

