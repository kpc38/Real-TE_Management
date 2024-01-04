package com.techelevator.controller;

import com.techelevator.exception.ServiceException;
import com.techelevator.model.Application;
import com.techelevator.model.RentTransaction;
import com.techelevator.service.RentTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class RentTransactionController {
    private RentTransactionService rentTransactionService;

    public RentTransactionController(RentTransactionService rentTransactionService) {
        this.rentTransactionService = rentTransactionService;
    }

    /**
     *
     * @param principal
     * @return rent transactions for properties owned by user
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/rent-transactions/managing")
    public List<RentTransaction> getAllRentTransactions(@Valid Principal principal){
        try{
            List<RentTransaction> rentTransactions = rentTransactionService.viewAllRentTransactions(principal);
            return rentTransactions;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }

    /**
     *
     * @param principal
     * @param propertyId
     * @return rent transactions associated with a property id
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/rent-transactions/managing/{propertyId}")
    public List<RentTransaction> getAllRentTransactionsPropertyId(@Valid Principal principal, @PathVariable("propertyId")
                                                                  int propertyId) {
        try{
            List<RentTransaction> rentTransactions = rentTransactionService.viewRentTransactionsByPropertyId(principal, propertyId);
            return rentTransactions;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }

    /**
     *
     * @param principal
     * @return rent transactions associated with user
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/rent-transactions")
        public List<RentTransaction> getMyRentTransactions(@Valid Principal principal){
        try{
            List<RentTransaction> rentTransactions = rentTransactionService.viewAllRentTransactions(principal);
            return rentTransactions;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }

    /**
     *
     * @param principal
     * @param isPastDue
     * @return past due rents associated with properties user owns
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/rent-transactions/past-due/{boolean}")
    public List<RentTransaction> getRentTransactionsByPastDue(@Valid Principal principal, @PathVariable("boolean")
                                                              boolean isPastDue){
        try{
            List<RentTransaction> rentTransactions = rentTransactionService.viewRentTransactionsByPastDue(principal, isPastDue);
            return rentTransactions;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }

    /**
     *
     * @param principal
     * @param rentTransactionId
     * @return rent transaction by id
     */
    @GetMapping("/rent-transactions/{id}")
    public RentTransaction getRentTransactionById(@Valid Principal principal, @PathVariable("id") int rentTransactionId){
        try{
            RentTransaction rentTransaction = rentTransactionService.viewRentTransactionById(principal, rentTransactionId);
            if(rentTransaction == null){
                throw new ServiceException("No Rent Transaction found with ID: " + rentTransactionId);
            }
            return rentTransaction;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }

    /**
     *
     * @param principal
     * @param newRentTransaction
     * @return created rent transactions
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/rent-transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RentTransaction> sendRentTransaction(@Valid Principal principal, @RequestBody RentTransaction
                                                               newRentTransaction){
        RentTransaction createdRentTransaction= null;
        try{
            createdRentTransaction = rentTransactionService.createRentTransaction(principal,newRentTransaction);
            if(createdRentTransaction == null){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return ResponseEntity.ok(createdRentTransaction);
            }
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete rent transaction
     * @param principal
     * @param rentTransactionId
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/rent-transactions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletedRentTransactions(@Valid Principal principal,@PathVariable("id") int rentTransactionId){
        try{
            rentTransactionService.deleteRentTransaction(principal,rentTransactionId);
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param principal
     * @param rentTransaction
     * @param rentTransactionId
     * @return updated rent transaction
     */
    @PreAuthorize("hasRole('ROLE_USER')") //TODO: send notification here ??????
    @PutMapping("rent-transactions/update/{id}")
    public RentTransaction updateRentTransaction(@Valid Principal principal, @RequestBody RentTransaction rentTransaction,
                                                 @PathVariable("id") int rentTransactionId){
        rentTransaction.setTransactionId(rentTransactionId);
        try{
            RentTransaction updatedRentTransaction = rentTransactionService.updateRentTransaction(principal,rentTransaction);
            if(updatedRentTransaction == null){
                throw new ServiceException("No Rent Transaction found with ID: " + rentTransactionId);
            }
            return updatedRentTransaction;
        }catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error encountered");
        }
    }


}
