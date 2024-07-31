package boardpage.board;

import boardpage.board.domain.Post;
import boardpage.board.repository.PostRepository;
import boardpage.board.dto.PostCreateDto;
import boardpage.board.dto.PostReadDto;
import boardpage.board.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PostServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @BeforeEach
    public void setup() {
        postRepository.deleteAll(); // 이전 데이터 삭제
        // 테스트 데이터 삽입
        PostCreateDto postCreateDto1 = new PostCreateDto("Title 1", "Content 1", LocalDateTime.now(), LocalDateTime.now(), null);
        PostCreateDto postCreateDto2 = new PostCreateDto("Title 2", "Content 2", LocalDateTime.now(), LocalDateTime.now().plusDays(1), null);

        Post post1 = new Post(postCreateDto1);
        Post post2 = new Post(postCreateDto2);
        postRepository.save(post1);
        postRepository.save(post2);
    }

    @Test
    @Transactional
    public void testCreatePost() {
        // 테스트 데이터 준비
        PostCreateDto postCreateDto = new PostCreateDto("Title 3", "Content 3", LocalDateTime.now(), LocalDateTime.now(), null);

        // 게시글 생성
        PostCreateDto createdPost = postService.createPost(postCreateDto);

        // 데이터 검증
        Post post = postRepository.findById(createdPost.getId()).orElse(null);
        assertThat(post).isNotNull();
        assertThat(post.getTitle()).isEqualTo("Title 3");
        assertThat(post.getContent()).isEqualTo("Content 3");
    }
}