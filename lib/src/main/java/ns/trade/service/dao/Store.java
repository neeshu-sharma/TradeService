package ns.trade.service.dao;

import java.util.Optional;

import ns.trade.service.entity.Trade;

public interface Store {

	Optional<Trade> findActiveByTradeIdAndVersionGreaterThan(String tradeId,int version);
	void save(Trade trade);


}