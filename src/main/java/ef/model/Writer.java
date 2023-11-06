package ef.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "writer", schema = "public")
public class Writer {

    @Id
    @Column(name = "id_writer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="writer_id",referencedColumnName = "id_writer")
    private List<Post> posts;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id",referencedColumnName = "id_region")
    private Region region;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Writer(String firstName, String lastName, List<Post> posts, Region region) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
        this.region = region;
    }

    public Writer(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Writer() {
    }

    @Override
    public String toString() {
        return ("\n* ********************************************************" +
                "\n*                          Writer" +
                "\n*                     ID: " + id +
                "\n*                     FIRSTNAME: " + firstName +
                "\n*                     LASTNAME: " + lastName +
                "\n*                     REGION: " + region + ".") +
                "\n*                     " + posts;
    }
}
