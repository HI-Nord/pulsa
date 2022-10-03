package is.hi.hbv501g.h6.hugboverkefni.Persistence.Entities;

import is.hi.hbv501g.h6.hugboverkefni.Persistence.MappedSuperclass.Message;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class Post extends Message {
    @Id
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    private Long postId;
    private String title;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "sub_id")
    private Sub sub;

    public Post() {}

    public Post(String title,
                Sub sub,
                Content content,
                User creator,
                List<Voter> voted,
                List<Reply> replies) {
        this.title = title;
        this.sub = sub;
        this.setContent(content);
        this.setCreator(creator);
        this.setVoted(voted);
        this.setReplies(replies);
        this.setCreated();
        this.setUpdated();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long id) {
        this.postId = id;
    }

    public Sub getSub() {
        return sub;
    }

    public void setSub(Sub sub) {
        this.sub = sub;
    }
}
