package com.techelevator.service;

import com.techelevator.model.Address;
import com.techelevator.model.Property;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface PropertyService {

    List<Property> viewAllProperties();

    Property viewPropertyById(Principal principal, int propertyId);

//    List<Property> viewPropertiesByUsername(Principal principal);

    Property createProperty(Principal principal, Property property);

    Property updateProperty(Principal principal, int propertyId, Property updatedProperty);


//    List<Property> viewPropertiesByNumberOfRooms(Principal principal, int numberOfRooms);
//    List<Property> viewPropertiesByZipcode(Principal principal, String zip);
//    List<Property> viewPropertiesByAvailability(Principal principal, boolean isAvailable);

//    List<Property> searchPropertiesByZipcode (String zipcode);
//    List<Property> searchPropertiesByStateAndCity (String state, String city);
//    List<Property> searchPropertiesByNumberOfRooms (int numberOfRooms);
//    List<Property> searchPropertiesByRent (BigDecimal rent);
//    List<Property> searchPropertiesByAvailability (boolean isAvailable);


}