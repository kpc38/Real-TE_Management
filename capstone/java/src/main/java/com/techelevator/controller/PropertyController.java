package com.techelevator.controller;

import com.techelevator.exception.ServiceException;
import com.techelevator.model.Address;
import com.techelevator.model.Property;
import com.techelevator.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated")
public class PropertyController {
    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /**
     * /properties

     * @return a list of properties that match the search parameters
     */
    @PreAuthorize("permitAll")
    @GetMapping("/properties")
    public List<Property> listProperties() {

        try {
            return propertyService.viewAllProperties();
        } catch (ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }

    /**
     *
     * @param id the id of the property
     * @return all info for a given property
     */
    @PreAuthorize("permitAll")
    @GetMapping("/properties/{id}")
    public Property fetchProperty(@PathVariable int id, Principal principal) {
        try {
            return propertyService.viewPropertyById(principal,id);
        } catch (ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }

    /**
     *
     * @return all properties managed by that user
     */
    @PreAuthorize("hasRole 'ROLE_ADMIN'")
    @GetMapping("/properties/managing")
    public List<Property> fetchMyProperties(Principal principal) {
        try {
            return propertyService.viewAllProperties();
        } catch (ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }
    //    As a property owner, I want to be able to see the data for my properties (average rent, vacancy%, etc.)

    @PreAuthorize("hasRole 'ROLE_ADMIN'")
    @PostMapping("/properties")
    public Property addNewProperty(Principal principal, Property property) {
        try {
            return propertyService.createProperty(principal, property);
        } catch (ServiceException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered.");
        }
    }

}