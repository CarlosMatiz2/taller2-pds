package co.com.poli.usersservice.clientFeign;

import co.com.poli.usersservice.helpers.Response;
import co.com.poli.usersservice.helpers.ResponseBuild;
import co.com.poli.usersservice.model.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingClientImplHystrixFallBack implements BookingClient {

    private final ResponseBuild build;

    @Override
    public Response findByUserID(String id) {
        return build.success(new Booking());
    }
}
