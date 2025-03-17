//package org.example;
//
//import org.example.controller.CustomerController;
//import org.example.exceptions.NotFoundByIdException;
//import org.example.service.CustomerService;
//import org.example.service.impl.CustomerServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class ExceptionTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private CustomerController customerController;
//
//    @MockBean
//    private CustomerService customerService;
//
//    @InjectMocks
//    private NotFoundByIdException notFoundByIdException;
//
//    @BeforeEach
//    void setup(){
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void userNotFoundByIdTest() throws Exception{
//        Long userId= 95L;
//
//        when(customerService.getById(anyLong())).thenReturn(Optional.empty());
//
//        assertThrows(NotFoundByIdException.class, () ->customerService.getById(userId));
//    }
//}
