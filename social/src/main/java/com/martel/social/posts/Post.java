package com.martel.social.posts;


import java.util.Date;
//import java.util.List;

import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {

    @Id
    private String id;

    private String key;
    private String imageUrl;
    private String description;
    private Date postDate;
    private long views;
    
    /* 
    TODO Comments, Likes and User
    @DBRef
    private Set<User> likes;

    @DBRef
    private List<Comment> comments;
    
    @DBRef
    private User sender;
    */
}
