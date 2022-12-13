package co.com.poli.bookingsservice.persistance.entity;

import co.com.poli.bookingsservice.model.Movie;
import co.com.poli.bookingsservice.model.Showtime;
import co.com.poli.bookingsservice.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "userid", nullable = false)
    private Long userid;

    @Column(name = "showtimeid", nullable = false)
    private Long showtimeid;

    @ElementCollection(targetClass = Long.class)
    @Column(name = "movies")
    private Collection<Long> movies;

    @Transient
    private User user;

    @Transient
    private Showtime showtime;


    @Transient
    private List<Movie> movies_list;

}
