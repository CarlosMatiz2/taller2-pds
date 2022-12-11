package co.com.poli.moviesservice.service;

import co.com.poli.moviesservice.persistance.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> findAll();
    void save(Movie user);
    Movie findById(String id);
    void delete(Movie user);
}
