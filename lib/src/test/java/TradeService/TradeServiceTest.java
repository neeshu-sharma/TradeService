package TradeService;

import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ns.trade.service.dao.Store;
import ns.trade.service.entity.Trade;
import ns.trade.service.processor.TradeProcessor;

class TradeServiceTest {
	static Trade[] trades;

	@BeforeAll
	static void init() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String tradeJson = "trades.json";
		ClassLoader classLoader = TradeServiceTest.class.getClassLoader();
		File file = new File(classLoader.getResource(tradeJson).getFile());
		trades = objectMapper.readValue(file, Trade[].class);

	}

	@Test
	void testStoreTrade() {
		Store store = mock(Store.class);
		TradeProcessor tradeProcessor = new TradeProcessor(store);
		tradeProcessor.process(trades[0]);
		Mockito.verify(store).save(trades[0]);
	}

}
