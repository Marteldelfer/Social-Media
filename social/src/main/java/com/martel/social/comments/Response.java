package com.martel.social.comments;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document
public class Response {

    @Id
    private String id;

    private String content;
    private Date responseDate;

    @DBRef
    private Comment comment;
    
}
