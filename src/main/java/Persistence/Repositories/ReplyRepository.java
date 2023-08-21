package Persistence.Repositories;

import Persistence.Entities.Reply;
import Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Optional<Reply> findByReplyId(Long replyId);

    List<Reply> findByCreator(User user);
}

