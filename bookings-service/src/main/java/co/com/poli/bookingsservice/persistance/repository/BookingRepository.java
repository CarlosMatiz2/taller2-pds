package co.com.poli.bookingsservice.persistance.repository;

import co.com.poli.bookingsservice.persistance.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
