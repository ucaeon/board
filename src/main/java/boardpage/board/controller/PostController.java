package boardpage.board.controller;

import boardpage.board.dto.PostCreateDto;
import boardpage.board.dto.PostReadDto;
import boardpage.board.dto.PostUpdateDto;
import boardpage.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public ResponseEntity<HashMap> postList() {
        List<PostReadDto> posts = postService.findPosts();
        HashMap<String, Object> a = new LinkedHashMap<>();
        a.put("status", "200");
        a.put("data", posts);
        return ResponseEntity.ok(a);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostReadDto> showPost(@PathVariable Long postId) {
        PostReadDto postRead = postService.findOne(postId);
//        HashMap<String, Object> a = new LinkedHashMap<>();
//        a.put("status", "200");
//        a.put("data", postRead);
        return ResponseEntity.ok(postRead);
    }


    @PostMapping("/post/{postId}/edit")
    public ResponseEntity<Map> editPost(@PathVariable Long postId, @RequestBody PostUpdateDto postUpdateDto) {
        PostUpdateDto updatedPost = postService.updatePost(postId, postUpdateDto);
        Map<String, String> updatedResponse = new LinkedHashMap<>();
        updatedResponse.put("status", "200");
        updatedResponse.put("message", "게시글 수정 성공");
        return ResponseEntity.ok(updatedResponse);
    }


    @PostMapping("/post")
    public ResponseEntity<Map> createPost(@RequestBody PostCreateDto postCreateDto) {
        PostCreateDto postCreate = postService.createPost(postCreateDto);
        Map<String, String> createResponse = new LinkedHashMap<>();
        createResponse.put("status", "200");
        createResponse.put("message", "게시글 작성 성공");
        return ResponseEntity.ok(createResponse);
    }

    @DeleteMapping("/post/{postId}/delete")
    public ResponseEntity<Map> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
//        Map<String, String> deleteResponse = new LinkedHashMap<>();
//        deleteResponse.put("status", "200");
//        deleteResponse.put("message", "게시글 삭제 성공");
        return ResponseEntity.ok(deleteResponse);
    }
}
