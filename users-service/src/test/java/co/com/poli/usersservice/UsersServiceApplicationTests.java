package co.com.poli.usersservice;

import co.com.poli.usersservice.clientFeign.BookingClient;
import co.com.poli.usersservice.persistance.entity.User;
import co.com.poli.usersservice.persistance.repository.UserRepository;
import co.com.poli.usersservice.service.UserService;
import co.com.poli.usersservice.service.UserServiceImpl;
import com.google.inject.spi.PrivateElements;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
@SpringBootTest
class UsersServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
