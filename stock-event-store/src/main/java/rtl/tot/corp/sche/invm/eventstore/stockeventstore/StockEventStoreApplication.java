package rtl.tot.corp.sche.invm.eventstore.stockeventstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class StockEventStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockEventStoreApplication.class, args);
	}

}
