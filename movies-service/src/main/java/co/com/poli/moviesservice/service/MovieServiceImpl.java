package co.com.poli.moviesservice.service;

import co.com.poli.moviesservice.persistance.entity.Movie;
import co.com.poli.moviesservice.persistance.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    @Transactional(readOnly = true)
    public Movie findById(String id) {
        return movieRepository.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        movieRepository.deleteById(Long.valueOf(id));
    }
}
