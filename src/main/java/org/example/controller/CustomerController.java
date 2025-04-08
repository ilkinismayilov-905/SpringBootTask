package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.example.entity.Customer;
//import org.example.exceptionHandler.GlobalExceptionHandler;
import org.example.exceptions.NotFoundByIdException;
import org.example.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> getAll(){
        return ResponseEntity.ok(customerServiceImpl.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer){
         customerServiceImpl.save(customer);
         return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get customer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer Found"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable Long id){
        Optional<Customer> customer = customerServiceImpl.getById(id);
        if (customer.isEmpty()) {
            throw new NotFoundByIdException();
        }
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Customer>> deleteCustomer(@PathVariable Long id){
        Optional<Customer> customer = customerServiceImpl.getById(id);
        if (customer.isEmpty()) {
            throw new NotFoundByIdException();
        }
        customerServiceImpl.deleteById(id);
        return ResponseEntity.ok(customer);
    }
}
