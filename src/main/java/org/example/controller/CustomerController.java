package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.entity.Customer;
//import org.example.exceptionHandler.GlobalExceptionHandler;
import org.example.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;

    @Autowired
    public CustomerController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll(){
        return ResponseEntity.ok(customerServiceImpl.getAllCustomers());
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer){
         customerServiceImpl.createCustomer(customer);
         return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get customer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer Found"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        Optional<Customer> customer = customerServiceImpl.findById(id);
        return customer.map(ResponseEntity::ok).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id){
        Optional<Customer> customer = customerServiceImpl.findById(id);
        if(customer.isPresent()){
            customerServiceImpl.deleteCustomer(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
