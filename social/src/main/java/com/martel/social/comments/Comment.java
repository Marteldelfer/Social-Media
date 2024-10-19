package com.martel.social.comments;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.martel.social.posts.Post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document
public class Comment {

    @Id
    private String id;

    private String content;
    private Date commentDate;
    
    @DBRef
    private Post post;
    @DBRef
    private List<Response> responses;

    /*
    TODO Add Users

    @DBRef
    private User sender;
     */

    
}
