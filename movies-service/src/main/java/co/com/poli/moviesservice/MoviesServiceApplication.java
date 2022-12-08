package co.com.poli.moviesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MoviesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesServiceApplication.class, args);
	}

}
