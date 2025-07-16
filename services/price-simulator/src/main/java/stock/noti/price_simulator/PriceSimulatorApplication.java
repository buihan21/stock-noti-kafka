package stock.noti.price_simulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PriceSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceSimulatorApplication.class, args);
	}

}
