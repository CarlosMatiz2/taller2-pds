package co.com.poli.showtimesservice.persistance.repository;

import co.com.poli.showtimesservice.persistance.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
}
