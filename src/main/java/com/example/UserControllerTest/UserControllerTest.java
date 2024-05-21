package com.example.UserControllerTest;

import com.example.userRegister.controller.UserController;
import com.example.userRegister.dto.UserDTO;
import com.example.userRegister.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
                        .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"password\":\"password123\",\"confirmPassword\":\"password123\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFailWhenPasswordsDoNotMatch() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("João Antônio");
        userDTO.setEmail("joao.antonio@example.com");
        userDTO.setPassword("senha123");
        userDTO.setConfirmPassword("senha124");

        mockMvc.perform(post("/api/users/register")
                        .contentType("application/json")
                        .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"password\":\"password123\",\"confirmPassword\":\"password124\"}"))
                .andExpect(status().isBadRequest());
    }
}