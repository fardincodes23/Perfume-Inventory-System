package ca.hccis.perfume.controllers;

import ca.hccis.perfume.jpa.entity.PerfumeTransaction;
import ca.hccis.perfume.repositories.CodeValueRepository;
import ca.hccis.perfume.repositories.PerfumeTransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PerfumeTransactionController.class)
public class PerfumeTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PerfumeTransactionRepository perfumeTransactionRepository;

    @MockBean
    private CodeValueRepository codeValueRepository;

    @Test
    public void testListTransactions() throws Exception {
        PerfumeTransaction mockRecord = new PerfumeTransaction();
        mockRecord.setCustomerName("Test User");
        Mockito.when(perfumeTransactionRepository.findAll()).thenReturn(Arrays.asList(mockRecord));
        mockMvc.perform(get("/perfumetransaction/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("perfumetransaction/list"))
                .andExpect(model().attributeExists("perfumeTransactions"));
    }
}