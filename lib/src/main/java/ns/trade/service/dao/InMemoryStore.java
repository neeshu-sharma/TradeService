package ns.trade.service.dao;

import java.util.Optional;

import ns.trade.service.entity.Trade;

public class InMemoryStore implements Store {

	
	@Override
	public Optional<Trade> findActiveByTradeIdAndVersionGreaterThan(String tradeId, int version) {
		return Optional.empty();
		
	}

	@Override
	public void save(Trade trade) {
		
	}

}
