package ns.trade.service.dao;

import java.util.Optional;

import ns.trade.service.entity.Trade;

public interface Store {

	Optional<Trade> findActiveByTradeIdAndVersionLessThan(String tradeId,int version);
	void save(Trade trade);


}