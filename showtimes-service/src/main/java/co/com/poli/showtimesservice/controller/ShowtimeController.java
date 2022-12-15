package co.com.poli.showtimesservice.controller;

import co.com.poli.showtimesservice.clientFeign.MovieClient;
import co.com.poli.showtimesservice.helpers.Response;
import co.com.poli.showtimesservice.helpers.ResponseBuild;
import co.com.poli.showtimesservice.helpers.ResponseFormat;
import co.com.poli.showtimesservice.model.Movie;
import co.com.poli.showtimesservice.persistance.entity.Showtime;
import co.com.poli.showtimesservice.service.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    private final MovieClient movieClient;

    private final ResponseBuild builder;

    @GetMapping
    public Response findAll(){
        return builder.success(showtimeService.findAll());
    }

    @PostMapping
    public Response save(@Valid @RequestBody Showtime showtime, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(ResponseFormat.formatMessage(result));
        }
        if(!validMovies(showtime.getMovies())){
            return builder.failed("Movies not found");
        }
        showtimeService.save(showtime);
        return builder.success(showtime);
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable("id") String id){
        Showtime showtime = showtimeService.findById(id);
        if(showtime == null){
            return builder.failed("Not found");
        }
        return builder.success(showtime);
    }

    @PutMapping("/{id}")
    public Response updateById(@PathVariable("id") String id, @RequestBody Showtime showtime){
        Showtime showtimeToUpdate = showtimeService.findById(id);
        if(showtimeToUpdate == null){
            return builder.failed("Not found");
        }
        if(!validMovies(showtime.getMovies())){
            return builder.failed("Movies not found");
        }
        showtimeService.updateById(showtimeToUpdate, showtime);
        return builder.success();
    }

    @GetMapping("/movie/{id}")
    public Response checkIfMovieIsAssigned(@PathVariable("id") String id){
        Boolean movieIsNotAssigned = showtimeService.checkIfMovieIsAssigned(id);
        return builder.success(movieIsNotAssigned);
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
