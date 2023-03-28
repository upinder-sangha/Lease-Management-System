package com.gcs.app.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Property
 *
 */
public class Property implements Serializable {
    private static final long serialVersionUID = 1L;
	private String streetNumber;
    private String streetName;
    private String city;
    private String postalCode;

    private String type;

    private ArrayList<RentableUnit> units;

    /**
     * @param type Type of the property
     * @param streetNumber Street Number of the Property
     * @param streetName Street Name of the Property
     * @param city City of Property
     * @param postalCode Postal Code of the Property
     */
    public Property(String type, String streetNumber,String streetName,String city,String postalCode){
        this.type = type;
        this.units = new ArrayList<>();
        this.streetName = streetName;
        this.streetNumber= streetNumber;
        this.city = city;
        this.postalCode = postalCode;
    }

    /**
     * @param unit Add a new Rentable unit.
     */
    public void addUnit(RentableUnit unit){
        if(this.type == "House" && units.size()>=1){
            System.out.println("you can't add any more units");
        }
        else{
            units.add(unit);
        }
    }

    public String getCivicAddress(){
        return (streetNumber+", "+streetName+", "+city+", "+postalCode);
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getType() {
        return type;
    }

    public ArrayList<RentableUnit> getUnits() {
        return units;
    }
}
