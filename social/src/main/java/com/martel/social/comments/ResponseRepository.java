package com.martel.social.comments;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResponseRepository extends MongoRepository<Response, String>{
    
}
