package co.com.poli.moviesservice.persistance.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "El titulo no puede estar vacío")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "El director no puede estar vacío")
    @Column(name = "director")
    private String director;

    @Min(value = 1, message = "El rating debe estar entre 1 a 5")
    @Max(value = 5, message = "El rating debe estar entre 1 a 5")
    @Column(name = "rating")
    private int rating;

}
