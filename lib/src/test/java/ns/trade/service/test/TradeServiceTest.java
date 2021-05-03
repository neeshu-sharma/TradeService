package ns.trade.service.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ns.trade.service.dao.InMemoryStore;
import ns.trade.service.dao.Store;
import ns.trade.service.entity.Trade;
import ns.trade.service.exception.LowerVersionException;
import ns.trade.service.processor.TradeProcessor;

class TradeServiceTest {
	static Trade[] trades;

	@BeforeAll
	static void init() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String tradeJson = "tradeDetails.json";
		ClassLoader classLoader = TradeServiceTest.class.getClassLoader();
		File file = new File(classLoader.getResource(tradeJson).getFile());
		trades = objectMapper.readValue(file, Trade[].class);

	}

	@Test
	void testStoreTrade() {
		Store store = mock(Store.class);
		TradeProcessor tradeProcessor = new TradeProcessor(store);
		tradeProcessor.process(trades[0]);
		verify(store).save(trades[0]);
		assertNotNull(trades[0].getCreatedDate());
	}

	@Test
	void testRejectLowerVersionTrade() {
		Store store = mock(Store.class);
		TradeProcessor tradeProcessor = new TradeProcessor(store);
		when(store.findActiveByTradeId(trades[2].getTradeId()))
				.thenReturn(List.of(trades[1]));
		assertThrows(LowerVersionException.class, () -> tradeProcessor.process(trades[2]));
	}

	@Test
	void testOverrideSameVersionTrade() {
		assertEquals(trades[1].getTradeId(), trades[4].getTradeId());
		assertEquals(trades[1].getVersion(), trades[4].getVersion());
		InMemoryStore memoryStore = new InMemoryStore();
		memoryStore.save(trades[1]);
		memoryStore.save(trades[4]);
		List<Trade> activeTrade = memoryStore.findActiveByTradeId(trades[1].getTradeId());
		assertFalse(activeTrade.isEmpty());
		assertEquals(1,activeTrade.size());
		Trade savedTrade = activeTrade.get(0);
		assertEquals(savedTrade.getCreatedDate(),LocalDate.of(2021, 5, 02));
	}
}
