package yu.softwareDesign.TripTip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TripTipApplication {
	public static void main(String[] args) {
		SpringApplication.run(TripTipApplication.class, args);
	}

}
