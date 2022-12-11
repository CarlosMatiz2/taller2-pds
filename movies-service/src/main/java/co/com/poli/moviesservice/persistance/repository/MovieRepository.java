package co.com.poli.moviesservice.persistance.repository;

import co.com.poli.moviesservice.persistance.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
