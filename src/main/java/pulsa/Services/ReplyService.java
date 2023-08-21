package pulsa.Services;

import pulsa.Persistence.Entities.Reply;
import pulsa.Persistence.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ReplyService {
    List<Reply> getReplies();

    Optional<Reply> getReplyById(Long replyId);

    List<Reply> getRepliesByUser(User user);

    Reply addNewReply(Reply reply);

    void deleteReply(Reply reply);

    Reply editReply(Reply reply);
}
