package org.example;

import org.example.controller.UserController;
import org.example.entity.User;
import org.example.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() throws Exception {
        User user = new User("Ilkin","ilkin2006@example.com",19L);
        when(userServiceImpl.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Ilkin\", \"email\": \"ilkin2006@example.com\",\"age\": \"19\"}"))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.name").value("Ilkin"))
//                .andExpect(jsonPath("$.email").value("ilkin2006@example.com"));
        verify(userServiceImpl,times(1)).createUser(user);

    }

}
