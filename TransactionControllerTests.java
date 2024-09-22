package com.example.MyProject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTests {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Before
    void setUp() {
    	
    }
       

	@Test
	public void testAddTransaction_Success() throws Exception {
	    String transactionJson = "{\"amount\":120.00, \"date\":\"2024-01-15\"}";

	    mockMvc.perform(post("/api/customers/1/transactions")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(transactionJson))
	            .andExpect(status().isCreated());
	}

	@Test
	public void testGetTransactions_Success() throws Exception {
	    mockMvc.perform(get("/api/customers/1/transactions"))
	            .andExpect(status().isOk());
	}

}
