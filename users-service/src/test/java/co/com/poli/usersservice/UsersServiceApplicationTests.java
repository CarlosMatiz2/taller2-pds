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
public class UserServiceMockTest {


	@Mock
	private UserRepository userRepository;
	private UserService userServices;

	@BeforeEach
	public void begin(){
		MockitoAnnotations.openMocks(this);
		userServices = new UserServiceImpl(userRepository);
		// User user = User.builder().id("100029859").name("Carlos").lastname("Bolaños").build();
		Mockito.when(userRepository.findById("100029859")).thenReturn(Optional.of(user));
	}

	@Test
	public void when_findById_return_user(){
		User user = userServices.findById("100029859");
		Assertions.assertThat(user.getName()).isEqualTo("Carlos");
	}
}