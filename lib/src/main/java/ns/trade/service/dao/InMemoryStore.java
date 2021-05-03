package ns.trade.service.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import ns.trade.service.entity.Trade;

public class InMemoryStore implements Store {

	private final Map<String, List<Trade>> trades = Collections.synchronizedMap(new HashMap<>());

	@Override
	public List<Trade> findActiveByTradeId(String tradeId) {
		List<Trade> existingTradeDetails = trades.getOrDefault(tradeId, Collections.emptyList());
		return existingTradeDetails.stream().filter(trade -> ("N").equals(trade.getExpired()))
				.collect(Collectors.toList());
	}

	@Override
	public void save(Trade trade) {
		Function<String, List<Trade>> mappingFunction = tradeId -> new ArrayList<>();
		List<Trade> existingTradeDetails = trades.computeIfAbsent(trade.getTradeId(), mappingFunction);
		if (existingTradeDetails.contains(trade)) {
			existingTradeDetails.remove(trade);
		}
		existingTradeDetails.add(trade);
	}

	@Override
	public void expireMaturedTrades() {
		
	}

}
