package co.com.poli.bookingsservice.controller;

import co.com.poli.bookingsservice.clientFeign.MovieClient;
import co.com.poli.bookingsservice.clientFeign.ShowtimeClient;
import co.com.poli.bookingsservice.clientFeign.UserClient;
import co.com.poli.bookingsservice.helpers.Response;
import co.com.poli.bookingsservice.helpers.ResponseBuild;
import co.com.poli.bookingsservice.helpers.ResponseFormat;
import co.com.poli.bookingsservice.model.Movie;
import co.com.poli.bookingsservice.model.Showtime;
import co.com.poli.bookingsservice.model.User;
import co.com.poli.bookingsservice.persistance.entity.Booking;
import co.com.poli.bookingsservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    private final ResponseBuild builder;

    private final UserClient userClient;

    private final ShowtimeClient showtimeClient;

    private final MovieClient movieClient;

    @GetMapping
    public Response findAll(){
        return builder.success(bookingService.findAll());
    }

    @PostMapping
    public Response save(@Valid @RequestBody Booking booking, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(ResponseFormat.formatMessage(result));
        }
        if(!validUser(booking.getUserid())){
            return builder.failed("User not found");
        }
        if(!validShowtime(booking.getShowtimeid())){
            return builder.failed("Showtime not found");
        }
        if(!validMovies(booking.getMovies())){
            return builder.failed("Movies not found");
        }
        bookingService.save(booking);
        return builder.success(booking);
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable("id") String id){
        Booking booking = bookingService.findById(id);
        if(booking == null){
            return builder.failed("Not found");
        }
        return builder.success(booking);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") String id){
        Booking booking = bookingService.findById(id);
        if(booking == null){
            return builder.failed("Not found");
        }
        bookingService.delete(id);
        return builder.success(booking);
    }

    @GetMapping("/user/{id}")
    public Response getByUserId(@PathVariable("id") String id){
        List<Booking> bookingList = bookingService.findByUserId(id);
        if(bookingList == null || bookingList.isEmpty()){
            return builder.failed("Not bookings found");
        }
        return builder.success(bookingList);
    }

    @GetMapping("/movie/{id}")
    public Response checkIfMovieIsAssigned(@PathVariable("id") String id){
        Boolean movieIsNotAssigned = bookingService.checkIfMovieIsAssigned(id);
        return builder.success(movieIsNotAssigned);
    }

    public Boolean validUser(Long userId) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userClient.findByID(userId.toString()).getData(), User.class);
        return user.getId() != null;
    }

    public Boolean validShowtime(Long showtimeId) {
        ModelMapper modelMapper = new ModelMapper();
        Showtime showtime = modelMapper.map(showtimeClient.findByID(showtimeId.toString()).getData(), Showtime.class);
        return showtime.getId() != null;
    }

    public Boolean validMovies(Collection<Long> moviesIds) {
        ModelMapper modelMapper = new ModelMapper();
        for (Long id : moviesIds) {
            Movie movie = modelMapper.map(movieClient.findByID(id.toString()).getData(), Movie.class);
            if (movie.getId() == null) {
                return false;
            }
        }
        return true;
    }

}
