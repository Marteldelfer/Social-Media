package com.martel.social.posts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.martel.social.DependencyFactory;
import com.martel.social.comments.Comment;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class PostService {
    
    private final PostRepository postRepository;
    private final S3Client s3Client = DependencyFactory.s3Client();
    private final String urlBase = "https://martel-social-media.s3.sa-east-1.amazonaws.com/";
    private final String bucket = "martel-social-media";
    
    public Post getPost(String id) {
        return postRepository.findById(id).get();
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    private void save(MultipartFile file, String key) throws IOException {
        RequestBody requestBody = RequestBody.fromInputStream(
            file.getInputStream(),
            file.getSize()
        );
        PutObjectRequest request = PutObjectRequest.builder()
            .acl(ObjectCannedACL.PUBLIC_READ)
            .bucket(bucket)
            .key(key)
            .contentType(file.getContentType())
            .build();
        s3Client.putObject(request, requestBody);
    }

    public Post publish(PostDto postDto) {
        try {
            String key = Long.toString(System.currentTimeMillis());
            save(postDto.image(), key);

            Post post = Post.builder()
                .key(key)
                .imageUrl(urlBase + key)
                .description(postDto.description())
                .postDate(new Date())
                .views(0)
                .comments(new ArrayList<Comment>())
                .build();

            return postRepository.save(post);
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Post edit(PostDto postDto, String id) {
        try {
            Post post = postRepository.findById(id).get();
            post.setDescription(postDto.description());

            save(postDto.image(), post.getKey());
  
            return postRepository.save(post);
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    public void delete(String id) {
        postRepository.deleteById(id);
    }


}
