package com.be.electronic_store.controller;

import com.be.electronic_store.config.BaseTest;
import com.be.electronic_store.constant.RequestMappingConstant;
import com.be.electronic_store.exception.ForbiddenException;
import com.be.electronic_store.mockdata.ProductDiscountDealMockData;
import com.be.electronic_store.model.ProductDiscountDealDTO;
import com.be.electronic_store.service.ProductDiscountDealService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductDiscountDealControllerTest extends BaseTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ProductDiscountDealService service;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testAddDiscountDealToProduct_ShouldSuccess() throws Exception {

        // Given
        long userId = 1L;
        ProductDiscountDealDTO productDiscountDealDTO = ProductDiscountDealMockData.getProductDiscountDeal();
        String jsonRequest = objectMapper.writeValueAsString(productDiscountDealDTO);

        // When
        when(service.addProductDiscountDeal(anyLong(), any(ProductDiscountDealDTO.class)))
                .thenReturn(productDiscountDealDTO);


        mockMvc.perform( MockMvcRequestBuilders
                .post(RequestMappingConstant.PRODUCT_DISCOUNT_DEAL_PATH)
                    .param("userId", String.valueOf(userId))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest)
                ).andExpect(status().isCreated());

        // Then
        verify(service).addProductDiscountDeal(anyLong(), any(ProductDiscountDealDTO.class));
    }

    @Test
    public void testAddDiscountDealToProduct_ShouldThrowForbiddenException() throws Exception {

        // Given
        long userId = 1L;
        ProductDiscountDealDTO productDiscountDealDTO = ProductDiscountDealMockData.getProductDiscountDeal();
        String jsonRequest = objectMapper.writeValueAsString(productDiscountDealDTO);

        // When
        when(service.addProductDiscountDeal(anyLong(), any(ProductDiscountDealDTO.class)))
                .thenThrow(ForbiddenException.class);


        mockMvc.perform( MockMvcRequestBuilders
                .post(RequestMappingConstant.PRODUCT_DISCOUNT_DEAL_PATH)
                    .param("userId", String.valueOf(userId))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest)
                ).andExpect(status().isForbidden());

        // Then
    }
}