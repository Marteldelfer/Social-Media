package com.martel.social.posts;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.martel.social.comments.Comment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String bucketKey;
    private String imageUrl;
    private String description;
    private Date postDate;
    private long views;
    
    @OneToMany
    @JoinColumn(name = "post_comments")
    private List<Comment> comments;
    /* 
    TODO Comments, Likes and User
    @DBRef
    private Set<User> likes;

    
    @DBRef
    private User sender;
    */
}
