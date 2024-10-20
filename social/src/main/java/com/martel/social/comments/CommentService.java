package com.martel.social.comments;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.martel.social.posts.Post;
import com.martel.social.posts.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ResponseRepository responseRepository;

    public Comment getComment(
        UUID id
    ) {
        return commentRepository.findById(id).get();
    }

    public List<Comment> getPostComments(
        UUID postId
    ) {
        return postRepository.findById(postId).get().getComments();
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Response getResponse(
        UUID id
    ) {
        return responseRepository.findById(id).get();
    }

    public List<Response> getCommentResponses(
        UUID commentId
    ) {
        return commentRepository.findById(commentId).get().getResponses();
    }

    public List<Response> getAllResponses() {
        return responseRepository.findAll();
    }

    public Comment publishComment(
        UUID postId,
        String content
    ) {
        Post post = postRepository.findById(postId).get();
        Comment comment = Comment.builder()
            .content(content)
            .post(post)
            .commentDate(new Date())
            .responses(new ArrayList<Response>())
            .build();
        comment = commentRepository.save(comment);
        post.getComments().add(comment);
        postRepository.save(post);
        
        return comment;
    }

    public Response respondComment(
        String content,
        UUID commentId
    ) {
        Comment comment = commentRepository.findById(commentId).get();
        Response response = Response.builder()
            .content(content)
            .comment(comment)
            .responseDate(new Date())
            .build();
        comment.getResponses().add(response);
        return responseRepository.save(response);
    }

    public Comment editComment(
        UUID id,
        String content
    ) {
        Comment comment = commentRepository.findById(id).get();
        comment.setContent(content);
        return commentRepository.save(comment);
    }

    public Response editResponse(
        UUID id,
        String content
    ) {
        Response response = responseRepository.findById(id).get();
        response.setContent(content);
        return responseRepository.save(response);
    }

    public void deleteComment(
        UUID commentId
    ) {
        commentRepository.deleteById(commentId);
    }

    public void deleteResponse(
        UUID responseId
    ) {
        responseRepository.deleteById(responseId);
    }
    
}
