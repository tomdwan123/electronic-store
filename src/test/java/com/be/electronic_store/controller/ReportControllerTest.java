package com.be.electronic_store.controller;

import com.be.electronic_store.config.BaseTest;
import com.be.electronic_store.constant.RequestMappingConstant;
import com.be.electronic_store.exception.ForbiddenException;
import com.be.electronic_store.mockdata.ReceiptProductMockData;
import com.be.electronic_store.model.ReceiptProductDTO;
import com.be.electronic_store.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReportControllerTest extends BaseTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ReportService reportService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetReceiptProducts_ShouldSuccess() throws Exception {

        // Given
        long customerId = 1L;
        long userId = 2L;
        ReceiptProductDTO receiptProductDTO = ReceiptProductMockData.getReceiptProduct();

        // When
        when(reportService.getReceiptProducts(anyLong(), anyLong()))
                .thenReturn(receiptProductDTO);

        mockMvc.perform( MockMvcRequestBuilders
                .get(RequestMappingConstant.REPORT_PATH + "/receipt-products/" + customerId)
                    .param("userId", String.valueOf(userId))
                ).andExpect(status().isOk());

        // Then
        verify(reportService).getReceiptProducts(anyLong(), anyLong());
    }

    @Test
    public void testGetReceiptProducts_ShouldThrowForbiddenException() throws Exception {

        // Given
        long customerId = 1L;
        long userId = 2L;

        // When
        when(reportService.getReceiptProducts(anyLong(), anyLong()))
                .thenThrow(ForbiddenException.class);

        mockMvc.perform( MockMvcRequestBuilders
                .get(RequestMappingConstant.REPORT_PATH + "/receipt-products/" + customerId)
                    .param("userId", String.valueOf(userId))
                ).andExpect(status().isForbidden());

        // Then
    }
}