package com.example.MyProject;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper; // For converting objects to JSON

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setName("Alice");
        customer.setEmail("alice@example.com");
        customer.setPassword("password123");
    }

    @Test
    public void testRegisterCustomer_Success() throws Exception {
        when(customerService.registerCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(post("/customers/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk()) // Ensure this matches your controller's actual status
                .andExpect(content().json(objectMapper.writeValueAsString(customer))); // Ensure customer JSON is returned
    }

    @Test
    public void testRegisterCustomer_DuplicateEmail() throws Exception {
        when(customerService.registerCustomer(any(Customer.class)))
                .thenThrow(new RuntimeException("Email already exists."));

        mockMvc.perform(post("/customers/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email already exists."));
    }

    @Test
    public void testLoginCustomer_Success() throws Exception {
        String loginJson = "{\"email\":\"alice@example.com\", \"password\":\"password123\"}";

        when(customerService.login(anyString(), anyString())).thenReturn("valid-token");

        mockMvc.perform(post("/customers/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(content().string("valid-token"));
    }

    @Test
    public void testLoginCustomer_NotFound() throws Exception {
        String loginJson = "{\"email\":\"unknown@example.com\", \"password\":\"password123\"}";

        when(customerService.login(anyString(), anyString())).thenThrow(new RuntimeException("Customer not found."));

        mockMvc.perform(post("/customers/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Customer not found."));
    }
}
