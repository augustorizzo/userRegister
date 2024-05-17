package com.example.UserControllerTest;

import com.example.userRegister.controller.UserController;
import com.example.userRegister.dto.UserDTO;
import com.example.userRegister.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void shouldRegisterUserSuccessfully() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Maria Antônia");
        userDTO.setEmail("maria.antonia@gmail.com");
        userDTO.setPassword("password123");
        userDTO.setConfirmPassword("password123");

        mockMvc.perform(post("/api/users/register")
                        .contentType("application/json")
                        .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"password\":\"password123\",\"confirmPassword\":\"password123\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFailWhenPasswordsDoNotMatch() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John Doe");
        userDTO.setEmail("john.doe@example.com");
        userDTO.setPassword("password123");
        userDTO.setConfirmPassword("password124");

        mockMvc.perform(post("/api/users/register")
                        .contentType("application/json")
                        .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"password\":\"password123\",\"confirmPassword\":\"password124\"}"))
                .andExpect(status().isBadRequest());
    }
}