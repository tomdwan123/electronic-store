package com.be.electronic_store.controller;

import com.be.electronic_store.config.BaseTest;
import com.be.electronic_store.constant.RequestMappingConstant;
import com.be.electronic_store.exception.ForbiddenException;
import com.be.electronic_store.mockdata.ProductMockData;
import com.be.electronic_store.model.ProductDTO;
import com.be.electronic_store.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends BaseTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetProducts_ShouldSuccess() throws Exception {

        // Given
        long userId = 1L;
        Page<ProductDTO> productDTOs = ProductMockData.getProducts();

        // When
        when(productService.getProducts(ArgumentMatchers.anyLong()))
                .thenReturn(productDTOs);

        mockMvc.perform( MockMvcRequestBuilders
                .get(RequestMappingConstant.PRODUCT_PATH)
                    .param("userId", String.valueOf(userId))
                ).andExpect(status().isOk());

        // Then
        verify(productService).getProducts(ArgumentMatchers.anyLong());
    }

    @Test
    public void testGetProducts_ShouldThrowForbiddenException() throws Exception {

        // Given

        // When
        when(productService.getProducts(ArgumentMatchers.anyLong()))
                .thenThrow(ForbiddenException.class);

        mockMvc.perform( MockMvcRequestBuilders
                .get(RequestMappingConstant.PRODUCT_PATH)
                    .param("userId", "3")
                ).andExpect(status().isForbidden());

        // Then
    }

    @Test
    public void testAddProduct_ShouldSuccess() throws Exception {

        // Given
        long userId = 1L;
        ProductDTO productDTO = ProductMockData.getProduct();
        String jsonRequest = objectMapper.writeValueAsString(productDTO);

        // When
        when(productService.addProduct(ArgumentMatchers.anyLong(), ArgumentMatchers.any(ProductDTO.class)))
                .thenReturn(productDTO);

        mockMvc.perform( MockMvcRequestBuilders
                .post(RequestMappingConstant.PRODUCT_PATH)
                    .param("userId", String.valueOf(userId))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest)
                ).andExpect(status().isCreated());

        // Then
        verify(productService).addProduct(ArgumentMatchers.anyLong(), ArgumentMatchers.any(ProductDTO.class));
    }

    @Test
    public void testAddProduct_ShouldThrowForbiddenException() throws Exception {

        // Given
        long userId = 1L;
        ProductDTO productDTO = ProductMockData.getProduct();
        String jsonRequest = objectMapper.writeValueAsString(productDTO);

        // When
        when(productService.addProduct(ArgumentMatchers.anyLong(), ArgumentMatchers.any(ProductDTO.class)))
                .thenThrow(ForbiddenException.class);

        mockMvc.perform( MockMvcRequestBuilders
                .post(RequestMappingConstant.PRODUCT_PATH)
                    .param("userId", String.valueOf(userId))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest)
                ).andExpect(status().isForbidden());

        // Then
    }

    @Test
    public void testDeleteProduct_ShouldSuccess() throws Exception {

        // Given
        long userId = 1L;

        // When
        mockMvc.perform( MockMvcRequestBuilders
                .delete(RequestMappingConstant.PRODUCT_PATH + "/1")
                    .param("userId", String.valueOf(userId))
                ).andExpect(status().isOk());

        // Then
        verify(productService).deleteProduct(anyLong(), anyLong());
    }
}