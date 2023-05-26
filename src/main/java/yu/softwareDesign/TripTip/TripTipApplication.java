package yu.softwareDesign.TripTip;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
//@EnableAdminServer
@SpringBootApplication
public class TripTipApplication {
	public static void main(String[] args) {
		SpringApplication.run(TripTipApplication.class, args);
	}

}
