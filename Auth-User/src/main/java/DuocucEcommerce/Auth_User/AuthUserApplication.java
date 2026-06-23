package DuocucEcommerce.Auth_User;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class AuthUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthUserApplication.class, args);
	}

}
