package ns.trade.service.processor;

import java.util.Optional;

import ns.trade.service.dao.Store;
import ns.trade.service.entity.Trade;
import ns.trade.service.exception.LowerVersionException;

public class TradeProcessor {

	private final Store store;

	public TradeProcessor(Store store) {
		this.store = store;
	}

	public void process(Trade trade) {
		Optional<Trade> activeTrade = store.findActiveByTradeIdAndVersionGreaterThan(trade.getTradeId(),trade.getVersion());
		activeTrade.ifPresent(at -> throwLowerVersionException(trade, at));
		store.save(trade);
	}

	private void throwLowerVersionException(Trade trade, Trade at) {
		String message = String.format("Existing Trade Version [%s] Received Trade Version [%s] ", at.getVersion(),
				trade.getVersion());
		throw new LowerVersionException(message);
	}
}
