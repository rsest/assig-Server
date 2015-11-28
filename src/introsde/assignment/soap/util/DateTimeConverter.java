package introsde.assignment.soap.util;

import java.util.Date;
import org.dozer.DozerConverter;
import org.joda.time.DateTime;

public class DateTimeConverter extends DozerConverter<Date, DateTime> {

	public DateTimeConverter() {
		super(Date.class, DateTime.class);
	}

	@Override
	public DateTime convertTo(Date source, DateTime destination) {
		DateTime result = null;
		if (source != null) {
			result = new DateTime(source.getTime());
		}
		return result;
	}

	@Override
	public Date convertFrom(DateTime source, Date destination) {
		Date result = null;
		if (source != null) {
			result = new Date(source.getMillis());
		}
		return result;
	}

}