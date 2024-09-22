package com.example.MyProject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;

@WebMvcTest(RewardPointController.class)
public class RewardPointControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private RewardPointsService rewardPointsService;

    @Autowired
    private ObjectMapper objectMapper; // To convert objects to JSON

    @BeforeEach
    void setUp() {
       
    }

    @Test
    public void testCalculateRewardPoints() {
        // You should ideally mock this service or move it to a separate service test
        when(rewardPointsService.calculatePoints(120.00)).thenReturn(90);
        when(rewardPointsService.calculatePoints(80.00)).thenReturn(30);
        when(rewardPointsService.calculatePoints(150.00)).thenReturn(200);

        int pointsFor120 = rewardPointsService.calculatePoints(120.00);
        assertEquals(90, pointsFor120);

        int pointsFor80 = rewardPointsService.calculatePoints(80.00);
        assertEquals(30, pointsFor80);

        int pointsFor150 = rewardPointsService.calculatePoints(150.00);
        assertEquals(200, pointsFor150);
    }

    @Test
    public void testAddTransaction_InvalidAmount() throws Exception {
        String transactionJson = "{\"amount\":-50.00, \"date\":\"2024-01-15\"}";

        mockMvc.perform(post("/api/customers/1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid transaction amount."));
    }

    @Test
    public void testGetRewardsForNonExistingCustomer() throws Exception {
        when(customerService.getCustomerById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/customers/999/rewards"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Customer not found."));
    }
}
