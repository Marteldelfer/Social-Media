package com.martel.social.comments;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.martel.social.posts.Post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String content;
    private Date commentDate;
    
    @OneToOne
    private Post post;

    @OneToMany
    @JoinColumn(name = "comment_responses")
    private List<Response> responses;

    /*
    TODO Add Users

    @DBRef
    private User sender;
     */

    
}
