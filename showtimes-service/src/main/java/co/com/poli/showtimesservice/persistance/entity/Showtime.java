package co.com.poli.showtimesservice.persistance.entity;

import co.com.poli.showtimesservice.model.Movie;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "showtimes")
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @ElementCollection(targetClass = Long.class)
    @Column(name = "movies")
    private Collection<Long> movies;

    @Transient
    private List<Movie> movies_list;

}
