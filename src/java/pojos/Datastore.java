package pojos;
// Generated 11-Jul-2015 21:51:04 by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Datastore generated by hbm2java
 */
@Entity
@Table(name = "datastore", catalog = "filmcatalog"
)
public class Datastore implements java.io.Serializable {

    private int id;
    private String name;
    private String type;
    private String place;
    private Set<Film> films = new HashSet<Film>(0);

    public Datastore() {
    }

    public Datastore(int id) {
        this.id = id;
    }

    public Datastore(int id, String name, String type, String place, Set<Film> films) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.place = place;
        this.films = films;
    }

    @Id

    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", length = 45)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type", length = 45)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "place", length = 45)
    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "datastore")
    public Set<Film> getFilms() {
        return this.films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return id + " : " + name + " : " + type + " : " + place;
    }
}
