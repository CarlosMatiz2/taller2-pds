package co.com.poli.usersservice.clientFeign;

import co.com.poli.usersservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "bookings-service", fallback = BookingClientImplHystrixFallBack.class)
public interface BookingClient {

    @GetMapping("/api/v1/bookings/user/{id}")
    Response findByUserID(@PathVariable("id") String id);

}
