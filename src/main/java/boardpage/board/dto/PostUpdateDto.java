package boardpage.board.dto;

import boardpage.board.domain.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PostUpdateDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime updateAt;

    public PostUpdateDto(){}


    public PostUpdateDto(Long id, String title, String content, LocalDateTime updateAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.updateAt = updateAt;
    }
}
