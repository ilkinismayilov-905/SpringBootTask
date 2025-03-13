package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.entity.Customer;
import org.example.exceptions.EmptyListExcepption;
import org.example.exceptions.NotFoundByIdException;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;
import org.example.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
       List<Customer> customerList = new ArrayList<>();
       for(Customer customer:customerRepository.findAll()){
           customerList.add(customer);
       }
       if(customerList.isEmpty()){
           throw new EmptyListExcepption("Customer List Is Empty");
       }
       return customerList;
    }

    public void createCustomer(Customer customer){
         customerRepository.save(customer);
    }


    public Optional<Customer> findById(Long customerId){
        Optional<Customer> customer = customerRepository.findById(customerId);

        if(customer.isEmpty()){
            throw new NotFoundByIdException();
        }
        return customer;

    }

    public void deleteCustomer(Long customerId){
        Optional<Customer> customer = customerRepository.findById(customerId);

        if(customer.isPresent()){
            customerRepository.deleteById(customerId);
        }
        throw new NotFoundByIdException();
    }

//    @Override
//    public void deleteById(Object id) {
//        if(customerRepository.existsById((Long) id)){
//            customerRepository.deleteById((Long) id);
//        }
//    }
//
//    @Override
//    public T save(Object entity) {
//       return (T) customerRepository.save(entity);
//    }
//
//    @Override
//    public Optional<T> getById(Object id) {
//        Optional<T> customer = (Optional<T>) customerRepository.findById((Long) id);
//
//        if(customer.isEmpty()){
//            throw  new NotFoundByIdException();
//        }
//        return customer;
//    }
//
//    @Override
//    public List<T> getAll() {
//        return (List<T>) customerRepository.findAll();
//    }
}
