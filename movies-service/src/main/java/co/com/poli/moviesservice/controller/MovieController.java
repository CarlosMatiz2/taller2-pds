package co.com.poli.moviesservice.controller;

import co.com.poli.moviesservice.helpers.Response;
import co.com.poli.moviesservice.helpers.ResponseBuild;
import co.com.poli.moviesservice.helpers.ResponseFormat;
import co.com.poli.moviesservice.persistance.entity.Movie;
import co.com.poli.moviesservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    private final ResponseBuild builder;

    @GetMapping
    public Response findAll(){
        return builder.success(movieService.findAll());
    }

    @PostMapping
    public Response save(@Valid @RequestBody Movie movie, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(ResponseFormat.formatMessage(result));
        }
        movieService.save(movie);
        return builder.success(movie);
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable("id") String id){
        Movie movie = movieService.findById(id);
        if(movie == null){
            return builder.failed("Not found");
        }
        return builder.success(movie);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") String id){
        Movie movie = movieService.findById(id);
        if(movie == null){
            return builder.failed("Not found");
        }
        Boolean wasDeleted = movieService.delete(id);
        if(wasDeleted){
            return builder.success("Deleted successfully");
        }else{
            return builder.failed("Movie cannot be deleted because is assigned on booking or showtime");
        }
    }

}
