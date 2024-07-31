package boardpage.board.service;

import boardpage.board.domain.Post;
import boardpage.board.dto.PostCreateDto;
import boardpage.board.dto.PostReadDto;
import boardpage.board.dto.PostUpdateDto;
import boardpage.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostCreateDto createPost(PostCreateDto postCreateDto) {
        Post post = new Post(postCreateDto);
        postRepository.save(post);
        return new PostCreateDto(post.getTitle(), post.getContent(), post.getCreatedAt(), post.getUpdateAt(), post.getDeleteAt());
    }

    @Transactional
    public PostUpdateDto updatePost(Long postId, PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setTitle(postUpdateDto.getTitle());
        post.setContent(postUpdateDto.getContent());
        post.setUpdateAt(LocalDateTime.now());
        return new PostUpdateDto(post.getId(), post.getTitle(), post.getContent(), post.getUpdateAt());
    }

    @Transactional
    public void deletePost(Long postId) {
        Post deletePost = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.delete(deletePost);
    }

    public List<PostReadDto> findPosts() {
        return postRepository.findAll().stream().map(PostReadDto::new).toList();
    }


    public PostReadDto findOne(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return new PostReadDto(post);
    }

}
