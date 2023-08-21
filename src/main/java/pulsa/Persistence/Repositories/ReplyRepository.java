package pulsa.Persistence.Repositories;

import pulsa.Persistence.Entities.Reply;
import pulsa.Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Optional<Reply> findByReplyId(Long replyId);

    List<Reply> findByCreator(User user);
}

