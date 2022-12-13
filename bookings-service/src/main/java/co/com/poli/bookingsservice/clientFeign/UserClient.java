package co.com.poli.bookingsservice.clientFeign;

import co.com.poli.bookingsservice.helpers.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users-service", fallback = UserClientImplHystrixFallBack.class)
public interface UserClient {

    @GetMapping("/api/v1/users/{id}")
    Response findByID(@PathVariable("id") String id);

}
