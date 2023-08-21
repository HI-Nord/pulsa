package pulsa.Persistence.Entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table
public class Content {
    @Id
    @SequenceGenerator(
            name = "content_sequence",
            sequenceName = "content_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "content_sequence"
    )
    private Long content_id;
    @Size(min = 0, max = 400000)
    private String text;
    private String image;
    private String audio;
    private String recording;

    private LocalDateTime created;
    private LocalDateTime updated;

    public Content() {
    }

    /**
     * Container for message content
     *
     * @param text      String text input from user
     * @param image     String DataURL of uploaded image
     * @param audio     String DataURL of uploaded audio
     * @param recording String DataURL of recorded audio
     * @return Content
     */
    public Content(String text, String image, String audio, String recording) {
        this.text = text;
        this.image = image;
        this.audio = audio;
        this.recording = recording;
        this.setCreated();
        this.setUpdated();
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAudio() {
        return this.audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public Long getContent_id() {
        return this.content_id;
    }

    public void setContent_id(Long id) {
        this.content_id = id;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated() {
        this.created = LocalDateTime.now();
    }

    public LocalDateTime getUpdated() {
        return this.updated;
    }

    public void setUpdated() {
        this.updated = LocalDateTime.now();
    }

    public String getRecording() {
        return this.recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }
}
