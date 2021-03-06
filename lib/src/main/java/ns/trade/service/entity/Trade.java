package ns.trade.service.entity;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Trade {

	private String tradeId;
	private int version;
	private String counterPartyId;
	private String bookId;
	@JsonDeserialize(using = CustomLocalDateDeserializer.class)  
	private LocalDate maturityDate;
	@JsonDeserialize(using = CustomLocalDateDeserializer.class)  
	private LocalDate createdDate =LocalDate.now();
	private String expired;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tradeId, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Trade))
			return false;
		Trade other = (Trade) obj;
		return Objects.equals(tradeId, other.tradeId) && version == other.version;
	}

}
