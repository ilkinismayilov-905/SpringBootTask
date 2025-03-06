package org.example.service;

import jakarta.transaction.Transactional;
import org.example.entity.Customer;
import org.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
       List<Customer> customerList = new ArrayList<>();
       for(Customer customer:customerRepository.findAll()){
           customerList.add(customer);
       }
       return customerList;
    }

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Optional<Customer> findById(Long customerId){
        if(customerRepository.existsById(customerId)){
           return customerRepository.findById(customerId);
        }
        return null;
    }

    public void deleteCustomer(Long customerId){
        if(customerRepository.existsById(customerId)){
            customerRepository.deleteById(customerId);
        }
    }

}
