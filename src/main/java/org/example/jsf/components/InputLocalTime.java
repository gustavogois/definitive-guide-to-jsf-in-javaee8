package org.example.jsf.components;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;

@FacesComponent("inputLocalTime")
public class InputLocalTime extends UIInput implements NamingContainer {

	private static final List<String> HOURS = IntStream.rangeClosed(0, 23).boxed().map(InputLocalTime::pad).collect(Collectors.toList());
	private static final List<String> MINUTES = IntStream.rangeClosed(0, 59).boxed().map(InputLocalTime::pad).collect(Collectors.toList());

	private UIInput hour;
	private UIInput minute;

	@Override
	public String getFamily() {
		return UINamingContainer.COMPONENT_FAMILY;
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		LocalTime localTime = (LocalTime) getValue();

		if (localTime != null) {
			hour.setValue(pad(localTime.getHour()));
			minute.setValue(pad(localTime.getMinute()));
		}

		super.encodeBegin(context);
	}

	@Override
	public Object getSubmittedValue() {
		return hour.getSubmittedValue() + ":" + minute.getSubmittedValue();
	}

	@Override
	protected Object getConvertedValue(FacesContext context, Object submittedValue) {
		try {
			return LocalTime.parse((String) submittedValue, DateTimeFormatter.ISO_LOCAL_TIME);
		}
		catch (DateTimeParseException e) {
			throw new ConverterException(e);
		}
	}

	private static String pad(Integer value) {
		return String.format("%02d", value);
	}

	public UIInput getHour() {
		return hour;
	}

	public void setHour(UIInput hour) {
		this.hour = hour;
	}

	public UIInput getMinute() {
		return minute;
	}

	public void setMinute(UIInput minute) {
		this.minute = minute;
	}

	public List<String> getHours() {
		return HOURS;
	}

	public List<String> getMinutes() {
		return MINUTES;
	}

}