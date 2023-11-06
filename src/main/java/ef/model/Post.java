package ef.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "post", schema = "public")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "created")
    private Long created;

    @Column(name = "updated")
    private Long updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public Post() {
    }

    public Post(String content) {
        this.content = content;
    }

    public Post(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public String toString() {
        return "\n* ********************************************************" +
                "\n*  Post id: " + id +
                "\n* ********************************************************" +
                "\n*   content: " + content +
                "\n*   created: " + new Timestamp(created) +
                "\n*   updated: " + new Timestamp(updated) +
                "\n* ********************************************************";
    }
}
