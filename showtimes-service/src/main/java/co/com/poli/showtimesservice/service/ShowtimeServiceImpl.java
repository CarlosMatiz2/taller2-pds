package co.com.poli.showtimesservice.service;

import co.com.poli.showtimesservice.clientFeign.MovieClient;
import co.com.poli.showtimesservice.model.Movie;
import co.com.poli.showtimesservice.persistance.entity.Showtime;
import co.com.poli.showtimesservice.persistance.repository.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService{

    private final ShowtimeRepository showtimeRepository;

    private final MovieClient movieClient;

    @Override
    public List<Showtime> findAll() {
        List<Showtime> showtimeList = showtimeRepository.findAll();
        for (Showtime showtime: showtimeList) {
            getMovieInfo(showtime);
        }
        return showtimeList;
    }

    @Override
    public void save(Showtime showtime) {
        showtimeRepository.save(showtime);
    }

    @Override
    public Showtime findById(String id) {
        Showtime showtime = showtimeRepository.findById(Long.valueOf(id)).orElse(null);
        if(showtime == null || showtime.getMovies() == null || showtime.getMovies().isEmpty()){
            return showtime;
        }
        getMovieInfo(showtime);
        return showtime;
    }

    @Override
    public void updateById(Showtime showtimeToUpdate, Showtime showtime) {
        showtimeToUpdate.setDate(showtime.getDate());
        showtimeToUpdate.setMovies(showtime.getMovies());
        showtimeRepository.save(showtimeToUpdate);
    }

    private void getMovieInfo(Showtime showtime) {
        List<Movie> movies = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Long movieId : showtime.getMovies()) {
            Movie movie = modelMapper.map(movieClient.findByID(movieId.toString()).getData(), Movie.class);
            movies.add(movie);
        }
        showtime.setMovies_list(movies);
    }

}
