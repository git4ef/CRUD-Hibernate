package ef.model;

import jakarta.persistence.*;

@Entity
@Table(name = "region", schema = "public")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_region")
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region() {
    }

    public Region(String name) {
        this.name = name;
    }

    public Region(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name + "\n";
    }
}
