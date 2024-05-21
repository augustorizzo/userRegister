package com.example.UserControllerTest;

import com.example.userRegister.controller.UserController;
import com.example.userRegister.dto.UserDTO;
import com.example.userRegister.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
        userDTO.setPassword("senha123");
        userDTO.setConfirmPassword("senha123");

        mockMvc.perform(post("/api/users/register")
                        .contentType("application/json")
                        .content("{\"name\":\"Maria Antônia\",\"email\":\"maria.antonia@gmail.com\",\"password\":\"senha123\",\"confirmPassword\":\"senha123\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFailWhenPasswordsDoNotMatch() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("João Antônio");
        userDTO.setEmail("joao.antonio@example.com");
        userDTO.setPassword("senha123");
        userDTO.setConfirmPassword("senha123");

        mockMvc.perform(post("/api/users/register")
                        .contentType("application/json")
                        .content("{\"name\":\"João Antônio\",\"email\":\"joao.antonio@example.com\",\"password\":\"senha123\",\"confirmPassword\":\"senha123\"}"))
                .andExpect(status().isBadRequest());
    }
}