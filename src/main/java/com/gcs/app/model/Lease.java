package com.gcs.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Tenant Leases
 *
 */
public class Lease implements Serializable {
    private static final long serialVersionUID = 1L;
	private Tenant tenant;
    private String unitAddress;
    double rent;
    private LocalDate startsOn;
    private LocalDate endsOn;

    private boolean isMonthlyRentPaid;

    /**
     * @param tenant Tenant who will be signing lease
     * @param address Address of unit
     * @param rent Rent of Unit
     * @param startsOn Lease start date
     * @param endsOn Lease end date
     */
    public Lease(Tenant tenant,String address, double rent, String startsOn, String endsOn){
        this.tenant = tenant;
        this.unitAddress = address;
        this.rent = rent;
        this.startsOn = stringToLocalDate(startsOn);
        this.endsOn = stringToLocalDate(endsOn);
    }

    public void payRent(){
        this.isMonthlyRentPaid = true;
    }

    public void setRentNotPaid(){
        this.isMonthlyRentPaid = false;
    }

    public String toString(){
        return "Unit: "+unitAddress +
                "\n Tenant name: "+tenant.getName()+
                "\n Tenant phone number: "+tenant.getPhoneNumber()+
                "\n Tenant email id: "+tenant.getEmailId()+
                "\n Monthly Rent: "+rent+
                "\n Starts on: "+startsOn+
                "\n Ends on: "+endsOn;
    }

	public boolean isMonthlyRentPaid() {
		return isMonthlyRentPaid;
	}

	public Tenant getTenant() {
		return tenant;
	}

    public String getUnitAddress() {
        return unitAddress;
    }
    
	public LocalDate getStartsOn() {
		return startsOn;
	}


	public LocalDate getEndsOn() {
		return endsOn;
	}

	
	/**
	 * @param dateStr
	 * @return LocalDate
	 *  Utility method to convert String to LocalDate format.
	 */
	private static LocalDate stringToLocalDate(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		formatter = formatter.withLocale( Locale.CANADA );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
		LocalDate date = LocalDate.parse(dateStr, formatter);
		return date;
	}
}
