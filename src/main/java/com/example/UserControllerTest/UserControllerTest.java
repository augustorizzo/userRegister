package com.example.UserControllerTest;

import com.example.userRegister.controller.UserController;
import com.example.userRegister.dto.UserDTO;
import com.example.userRegister.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
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
        userDTO.setPassword("senha123");
        userDTO.setConfirmPassword("senha123");

        mockMvc.perform((RequestBuilder) post("/api/users/register")
                        .contentType(MediaType.valueOf("application/json"))
                        .contentType(MediaType.valueOf("{\"name\":\"Maria Antônia\",\"email\":\"maria.antonia@gmail.com\",\"password\":\"senha123\",\"confirmPassword\":\"senha123\"}")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFailWhenPasswordsDoNotMatch() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("João Antônio");
        userDTO.setEmail("joao.antonio@example.com");
        userDTO.setPassword("senha123");
        userDTO.setConfirmPassword("senha123");

        mockMvc.perform((RequestBuilder) post("/api/users/register")
                        .contentType(MediaType.valueOf("application/json"))
                        .contentType(MediaType.valueOf("{\"name\":\"João Antônio\",\"email\":\"joao.antonio@example.com\",\"password\":\"senha123\",\"confirmPassword\":\"senha123\"}")))
                .andExpect(status().isBadRequest());
    }
}