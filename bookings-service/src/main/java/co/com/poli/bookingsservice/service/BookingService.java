package co.com.poli.bookingsservice.service;

import co.com.poli.bookingsservice.persistance.entity.Booking;

import java.util.List;

public interface BookingService {

    List<Booking> findAll();
    void save(Booking booking);
    Booking findById(String id);
    void delete(String id);
    List<Booking> findByUserId(String id);
    Boolean checkIfMovieIsAssigned(String id);
}
