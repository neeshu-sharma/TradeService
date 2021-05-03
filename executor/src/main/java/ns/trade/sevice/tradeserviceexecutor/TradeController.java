package ns.trade.sevice.tradeserviceexecutor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ns.trade.service.dao.Store;
import ns.trade.service.entity.Trade;
import ns.trade.service.processor.TradeProcessor;

@RequestMapping("/trade")
@RestController
public class TradeController {
	
	private final TradeProcessor tradeProcessor;
	private final Store store;
	
	@Autowired
	public TradeController(TradeProcessor tradeProcessor,Store store) {
		this.tradeProcessor=tradeProcessor;
		this.store=store;
	}
	
	@PostMapping
	public void persist(@RequestBody Trade trade) {
		tradeProcessor.process(trade);
	}
	
	@GetMapping("/all")
	public List<Trade> getAll(){
		return store.getAll();
		
	}
}
