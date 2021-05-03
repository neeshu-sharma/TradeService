package ns.trade.sevice.tradeserviceexecutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ns.trade.service.dao.InMemoryStore;
import ns.trade.service.dao.Store;
import ns.trade.service.processor.TradeProcessor;
import ns.trade.service.scheduler.MarkMaturedTradeExpiredScheduler;

@SpringBootApplication
public class TradeServiceExecutorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeServiceExecutorApplication.class, args);
	}

	@Bean
	public Store store() {
		return new InMemoryStore();
	}

	@Bean
	public MarkMaturedTradeExpiredScheduler markMaturedTradeExpiredScheduler(Store store) {
		return new MarkMaturedTradeExpiredScheduler(store);
	}
	
	@Bean
	public TradeProcessor tradeProcessor(Store store) {
		return new TradeProcessor(store);
	}

}
