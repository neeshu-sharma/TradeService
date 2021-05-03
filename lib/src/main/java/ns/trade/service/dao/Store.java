package ns.trade.service.dao;

import java.util.List;

import ns.trade.service.entity.Trade;

public interface Store {

	List<Trade> findActiveByTradeId(String tradeId);
	void save(Trade trade);
	void expireMaturedTrades();

}