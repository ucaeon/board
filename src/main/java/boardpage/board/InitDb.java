package boardpage.board;

import boardpage.board.domain.Post;
import boardpage.board.dto.PostCreateDto;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

   // @PostConstruct
  //  public void init() {
   //     initService.dbInit1();
  //      initService.dbInit2();
  //  }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            PostCreateDto dto1 = new PostCreateDto("Title 1", "Content 1", null, null, null);
            em.persist(new Post(dto1));
            PostCreateDto dto2 = new PostCreateDto("Title 2", "Content 2", null, null, null);
            em.persist(new Post(dto2));
        }

        public void dbInit2() {
            PostCreateDto dto1 = new PostCreateDto("Title 3", "Content 3", null, null, null);
            em.persist(new Post(dto1));
            PostCreateDto dto2 = new PostCreateDto("Title 4", "Content 4", null, null, null);
            em.persist(new Post(dto2));
        }
    }
}