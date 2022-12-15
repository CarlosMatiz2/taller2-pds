package co.com.poli.showtimesservice.service;

import co.com.poli.showtimesservice.persistance.entity.Showtime;

import java.util.List;

public interface ShowtimeService {

    List<Showtime> findAll();
    void save(Showtime user);
    Showtime findById(String id);
    void updateById(Showtime showtimeToUpdate, Showtime showtime);
    Boolean checkIfMovieIsAssigned(String id);

}
