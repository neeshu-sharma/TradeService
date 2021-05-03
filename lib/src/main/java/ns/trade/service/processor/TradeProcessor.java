package ns.trade.service.processor;

import ns.trade.service.dao.Store;
import ns.trade.service.entity.Trade;

public class TradeProcessor {
	
	private final Store store;
	
	 public TradeProcessor(Store store) {
		this.store = store;
	}

	public void process(Trade trade){
		store.save(trade);
	 }
}
