package com.epam.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.NoSuchElementException;

import com.epam.springboot.controller.UserController;
import com.epam.springboot.model.User;
import com.epam.springboot.serivce.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.springboot.model.Error;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    void findByIdTest() throws Exception {
        User expected = new User(1L, "user1", "user1@gmail.com");
        when(userService.findById(1L)).thenReturn(expected);
        String json = mockMvc
                .perform(get("/users/" + 1L))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        User actual = objectMapper.readValue(json, User.class);
        assertEquals(expected, actual);
    }

    @Test
    void findByIdNotFoundTest() throws Exception {
        when(userService.findById(1L)).thenThrow(new NoSuchElementException("User not found"));
        String json = mockMvc
                .perform(get("/users/" + 1L))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Error error = objectMapper.readValue(json, Error.class);
        assertEquals("NOT_FOUND", error.getCode());
        assertEquals("User not found", error.getMessage());
    }

    @Test
    void findByBadRequest() throws Exception {
        when(userService.findById(1L)).thenThrow(new IllegalArgumentException());
        String json = mockMvc
                .perform(get("/users/" + 1L))
                .andExpect(status().is4xxClientError())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Error error = objectMapper.readValue(json, Error.class);
        assertEquals("BAD_REQUEST", error.getCode());
    }

    @Test
    void findByServerError() throws Exception {
        String json = mockMvc
                .perform(get("/users/" + "invalidId"))
                .andExpect(status().is5xxServerError())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Error error = objectMapper.readValue(json, Error.class);
        assertEquals("INTERNAL_SERVER_ERROR", error.getCode());
    }
}
