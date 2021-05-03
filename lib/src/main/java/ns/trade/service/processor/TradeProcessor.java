package ns.trade.service.processor;

import java.time.LocalDate;
import java.util.List;

import ns.trade.service.dao.Store;
import ns.trade.service.entity.Trade;
import ns.trade.service.exception.LowerVersionException;

public class TradeProcessor {

	private final Store store;

	public TradeProcessor(Store store) {
		this.store = store;
	}

	public void process(Trade trade) {
		boolean matDateLessToday = trade.getMaturityDate().isBefore(LocalDate.now());
		if (matDateLessToday) {
			return;
		}
		List<Trade> activeTrade = store.findActiveByTradeId(trade.getTradeId());
		activeTrade.stream().filter(at -> at.getVersion() > trade.getVersion()).findFirst()
				.ifPresent(at -> throwLowerVersionException(trade, at));

		store.save(trade);
	}

	private void throwLowerVersionException(Trade trade, Trade at) {
		String message = String.format("Existing Trade Version [%s] Received Trade Version [%s] ", at.getVersion(),
				trade.getVersion());
		throw new LowerVersionException(message);
	}

}
