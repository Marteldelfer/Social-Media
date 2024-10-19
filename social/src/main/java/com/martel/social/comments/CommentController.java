package com.martel.social.comments;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {    

    private final CommentService commentService;

    @GetMapping("/")
    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> findPostComments(
        @PathVariable("postId") String postId
    ) {
        return ResponseEntity.ok(commentService.getPostComments(postId));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getComment(
        @PathVariable("commentId") String commentId
    ) {
        return ResponseEntity.ok(commentService.getComment(commentId));
    }

    @GetMapping("/response")
    public ResponseEntity<List<Response>> getAllResponse() {
        return ResponseEntity.ok(commentService.getAllResponses());
    }

    @GetMapping("/response/comment/{commentId}")
    public ResponseEntity<List<Response>> getCommentResponse(
        @PathVariable("commentId") String commentId
    ) {
        return ResponseEntity.ok(commentService.getCommentResponses(commentId));
    }

    @GetMapping("/response/{responseId}")
    public ResponseEntity<Response> getResponse(
        @PathVariable("responseId") String responseId
    ) {
        return ResponseEntity.ok(commentService.getResponse(responseId));
    }

    @PostMapping("/post/{postId}")
    public ResponseEntity<Comment> postComment(
        @PathVariable("postId") String postId,
        @RequestBody String content
    ) {
        return ResponseEntity.ok(commentService.publishComment(postId, content));
    }

    @PostMapping("/response/{commentId}")
    public ResponseEntity<Response> postResponse(
        @PathVariable("commentId") String commentId,
        @RequestBody String content
    ) {
        return ResponseEntity.ok(commentService.respondComment(content, commentId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> editComment(
        @PathVariable("commentId") String commentId,
        @RequestBody String content
    ) {
        return ResponseEntity.ok(commentService.editComment(commentId, content));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
        @PathVariable("commentId") String commentId,
        @RequestBody String content
    ) {
        return ResponseEntity.accepted().build();
    }

}
