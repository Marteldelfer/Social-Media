package com.martel.social.posts;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    
    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(
        @PathVariable("id") String id
    ) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @PostMapping("/")
    public ResponseEntity<Post> publishPost(
        @ModelAttribute PostDto postDto
    ) {
        return ResponseEntity.ok(postService.publish(postDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> editPost(
        @ModelAttribute PostDto postDto,
        @PathVariable("id") String id
    ) {
        return ResponseEntity.ok(postService.edit(postDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
        @PathVariable("id") String id
    ) {
        postService.delete(id);
        return ResponseEntity.accepted().build();
    }
 
}
