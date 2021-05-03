package ns.trade.service.entity;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

public class CustomLocalDateDeserializer extends LocalDateDeserializer {

	private static final long serialVersionUID = 1979318467360401445L;

	public CustomLocalDateDeserializer() {
		super(getLocalDateFormatter());
	}

	private static DateTimeFormatter getLocalDateFormatter() {
		return new DateTimeFormatterBuilder().appendValue(DAY_OF_MONTH, 2).appendLiteral('/')
				.appendValue(MONTH_OF_YEAR, 2).appendLiteral('/').appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
				.toFormatter();
	}
}

