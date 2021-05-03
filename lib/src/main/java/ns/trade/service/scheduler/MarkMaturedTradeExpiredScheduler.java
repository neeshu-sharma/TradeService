package ns.trade.service.scheduler;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ns.trade.service.dao.Store;

public class MarkMaturedTradeExpiredScheduler {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private final Store store;

	public MarkMaturedTradeExpiredScheduler(Store store) {
		this.store = store;
	}

	public void schedule() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nextDate = LocalDate.now().plusDays(1).atStartOfDay();
		long initialDelay = Duration.between(now, nextDate).toNanos();
		long period = Duration.ofDays(1).toNanos();

		scheduler.scheduleAtFixedRate(store::expireMaturedTrades, initialDelay, period, TimeUnit.NANOSECONDS);
	}

}
