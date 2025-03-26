//package org.example;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.example.controller.CustomerController;
//import org.example.entity.Customer;
//import org.example.entity.User;
//import org.example.service.impl.CustomerServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@WebMvcTest(CustomerController.class)
//public class CustomerControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private CustomerServiceImpl customerServiceImpl;
//
//    @InjectMocks
//    private CustomerController customerController;
//
//    @BeforeEach
//    void setup(){
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getAllCustomersTest() throws Exception{
//        List<Customer> customerList = Arrays.asList(
//                new Customer("ilkin","ilkin@example.com","0559059055")
//        );
//
//        when(customerServiceImpl.getAll()).thenReturn(customerList);
//
//        mockMvc.perform(get("/customers")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()").value(customerList.size()))
//                .andExpect(jsonPath("$[0].name").value("ilkin"))
//                .andExpect(jsonPath("$[0].email").value("ilkin@example.com"))
//                .andExpect(jsonPath("$[0].phone").value("0559059055"));
//    }
//
//    @Test
//    void addCustomerTest() throws Exception{
//        Customer customer = new Customer("ilkin","ilkin@example.com","0559059055");
//
//        when(customerServiceImpl.save(any(Customer.class))).thenReturn(customer);
//
//        mockMvc.perform(post("/customers/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(customer)))
//                .andExpect(status().isOk());
//
//        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
//        verify(customerServiceImpl).save(customerCaptor.capture());
//
////        Customer capturedCustomer = customerCaptor.getValue();
////        assertEquals(customer.getName(), customerCaptor.);
////        assertEquals(customer.getEmail(), customerCaptor.getEmail());
//
//
//        verify(customerServiceImpl).save(any(Customer.class));
//    }
//}
