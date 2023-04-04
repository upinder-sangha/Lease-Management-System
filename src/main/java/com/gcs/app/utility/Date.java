package com.gcs.app.utility;

import com.gcs.app.contoller.PropertyController;
import com.gcs.app.model.Property;
import com.gcs.app.model.RentableUnit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

;

/**
 * Utility class for Date related operation
 *
 */
public class Date {

	private static LocalDate date = LocalDate.now();

	/**
	 * @param dateStr String date
	 */
	public static String setDate(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		formatter = formatter.withLocale( Locale.CANADA ); 
		LocalDate previous_date = date;
		date = LocalDate.parse(dateStr, formatter);

		String alertMessage = null;
		for(Property property : PropertyController.getProperties()) {
			for(RentableUnit rentableUnit: property.getUnits()) {
				if(rentableUnit.getLease()!=null && date.isAfter(rentableUnit.getLease().getEndsOn())) {
					alertMessage = rentableUnit.endLease();
				}
				if(rentableUnit.getLease()!=null && date.isAfter(previous_date) && !(date.getYear()==previous_date.getYear() && date.getMonth().equals(previous_date.getMonth()))){
					rentableUnit.getLease().setRentNotPaid();
				}
			}
		}
		return alertMessage;
		
	}
}
