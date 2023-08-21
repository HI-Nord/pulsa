package Persistence.Entities;

import Persistence.MappedSuperclass.Message;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Embeddable
public class Reply extends Message {
    @Id
    @SequenceGenerator(
            name = "reply_sequence",
            sequenceName = "reply_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reply_sequence"
    )
    private Long replyId;

    /**
     * Reply entity
     *
     * @param content Content contains content of reply
     * @param user    User owner of reply
     * @param voted   List<Voter> List of users that have voted on reply
     * @param replies List<Reply> List of replies to Reply
     * @return Reply
     */
    public Reply(Content content, User user, List<Voter> voted, List<Reply> replies, Sub sub) {
        this.setContent(content);
        this.setCreator(user);
        this.setVoted(voted);
        this.setReplies(replies);
        this.setCreated();
        this.setUpdated();
        this.setSub(sub);
    }

    public Reply() {
    }

    public Long getReplyId() {
        return this.replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }
}
