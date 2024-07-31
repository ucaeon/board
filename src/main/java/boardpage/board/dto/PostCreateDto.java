package boardpage.board.dto;

import boardpage.board.domain.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PostCreateDto {

    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    public PostCreateDto(String title, String content, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt) {
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
    }
}
