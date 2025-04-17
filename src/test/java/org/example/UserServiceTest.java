//package org.example;
//
//import org.example.entity.Customer;
//import org.example.entity.MainUser;
//import org.example.repository.UserRepository;
//import org.example.service.UserService;
//import org.example.service.impl.UserServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserServiceImpl userServiceImpl;
//
//
//    @Test
//    void testAddUser(){
//        MainUser user = new MainUser("Ilkin","ilkin@gmail.com",19L);
//        when(userRepository.save(any(MainUser.class))).thenReturn(user);
//
//        MainUser createdUser = userServiceImpl.save(user);
//
//        assertNotNull(createdUser);
//        assertEquals("Ilkin",createdUser.getName());
//        assertEquals("ilkin@gmail.com",createdUser.getEmail());
//        assertEquals(19L,createdUser.getAge());
//        verify(userRepository,times(1)).save(user);
//    }
//
//    @Test
//    void testGetAll(){
//        List<MainUser> users = Arrays.asList(
//                new MainUser("Ilkin", "ilkin123@gmail.com", 19L),
//                new MainUser("Celil", "celil02@gmail.com", 18L)
//        );
//        when(userRepository.findAll()).thenReturn(users);
//
//        List<MainUser> retrievedUsers = userServiceImpl.getAll();
//
//        assertFalse(retrievedUsers.isEmpty());
//        assertEquals(2, retrievedUsers.size());
//        verify(userRepository, times(1)).findAll();
//
//    }
//
//    @Test
//    void testDeleteUser(){
//        Long userId = 1L;
//        MainUser user = new MainUser(userId, "Test MainUser", "test@example.com",19L);
//
//
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//
//
//        userServiceImpl.deleteById(userId);
//
//
//        verify(userRepository, times(1)).deleteById(userId);
//    }
//
//
//
//}
