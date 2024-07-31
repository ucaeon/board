package boardpage.board.controller;

import boardpage.board.domain.Post;
import boardpage.board.dto.PostCreateDto;
import boardpage.board.dto.PostReadDto;
import boardpage.board.dto.PostUpdateDto;
import boardpage.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String postList(Model model) {
        List<PostReadDto> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "post_list";
    }

    @GetMapping("/post/{postId}")
    public String showPost(@PathVariable Long postId, Model model) {
        PostReadDto postReadDto = postService.findOne(postId);
        model.addAttribute("post", postReadDto);
        return "post";
    }

    @GetMapping("/post/{postId}/edit")
    public String showEditPost(@PathVariable Long postId, Model model) {
        PostReadDto post = postService.findOne(postId);
        model.addAttribute("post", post);
        return "post_edit";
    }


    @PostMapping("/post/{postId}/edit")
    public ResponseEntity<PostUpdateDto> editPost(@PathVariable Long postId, @RequestBody PostUpdateDto postUpdateDto) {
        PostUpdateDto updatedPost = postService.updatePost(postId, postUpdateDto);
        return ResponseEntity.ok(updatedPost);
    }

    @GetMapping("/post")
    public String showPostForm() {
        return "post_form";
    }

    @PostMapping("/post")
    public ResponseEntity<PostCreateDto> createPost(@RequestBody PostCreateDto postCreateDto) {
        PostCreateDto postCreate = postService.createPost(postCreateDto);
        return ResponseEntity.ok(postCreate);
    }

    @DeleteMapping("/post/{postId}/delete")
    public ResponseEntity<String> deletePost(@PathVariable("postId") Long postId) {
        try {
            postService.deletePost(postId);
            return ResponseEntity.ok("삭제 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 삭제에 실패하였습니다.");
        }
    }
}
