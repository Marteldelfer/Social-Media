package com.martel.social.comments;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, UUID>{
    
}
