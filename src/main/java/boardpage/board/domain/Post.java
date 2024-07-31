package boardpage.board.domain;

import boardpage.board.dto.PostCreateDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId")
    private Long id;

    private String title;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    public Post() {

    }


    public Post(PostCreateDto postCreateDto) {
        this.title = postCreateDto.getTitle();
        this.content = postCreateDto.getContent();
        this.createdAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.deleteAt = null;
    }
}


