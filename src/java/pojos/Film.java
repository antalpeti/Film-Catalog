package pojos;
// Generated 11-Jul-2015 21:51:04 by Hibernate Tools 3.6.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Film generated by hbm2java
 */
@Entity
@Table(name="film"
    ,catalog="filmcatalog"
)
public class Film  implements java.io.Serializable {


     private int id;
     private Datastore datastore;
     private String title;
     private String director;
     private String time;
     private String genre;
     private Integer year;

    public Film() {
    }

	
    public Film(int id, Datastore datastore) {
        this.id = id;
        this.datastore = datastore;
    }
    public Film(int id, Datastore datastore, String title, String director, String time, String genre, Integer year) {
       this.id = id;
       this.datastore = datastore;
       this.title = title;
       this.director = director;
       this.time = time;
       this.genre = genre;
       this.year = year;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="datastore_id", nullable=false)
    public Datastore getDatastore() {
        return this.datastore;
    }
    
    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }

    
    @Column(name="title", length=90)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    
    @Column(name="director", length=45)
    public String getDirector() {
        return this.director;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }

    
    @Column(name="time", length=45)
    public String getTime() {
        return this.time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }

    
    @Column(name="genre", length=45)
    public String getGenre() {
        return this.genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }

    
    @Column(name="year")
    public Integer getYear() {
        return this.year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }




}


