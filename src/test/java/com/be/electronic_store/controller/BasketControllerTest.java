package com.be.electronic_store.controller;

import com.be.electronic_store.config.BaseTest;
import com.be.electronic_store.constant.RequestMappingConstant;
import com.be.electronic_store.exception.ForbiddenException;
import com.be.electronic_store.mockdata.BasketMockData;
import com.be.electronic_store.model.BasketDTO;
import com.be.electronic_store.service.BasketService;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BasketControllerTest extends BaseTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private BasketService basketService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetBaskets_ShouldSuccess() throws Exception {

        // Given
        long userId = 1L;
        List<BasketDTO> basketDTOs = BasketMockData.getBaskets();

        // When
        when(basketService.getBasketsByUserId(anyLong()))
            .thenReturn(basketDTOs);

        mockMvc.perform( MockMvcRequestBuilders
                .get(RequestMappingConstant.PRODUCT_BASKET_PATH)
                .param("userId", String.valueOf(userId))
        ).andExpect(status().isOk());

        // Then
        verify(basketService).getBasketsByUserId(anyLong());
    }

    @Test
    public void testGetBaskets_ShouldThrowForbiddenException() throws Exception {

        // Given
        long userId = 1L;

        // When
        when(basketService.getBasketsByUserId(anyLong()))
                .thenThrow(ForbiddenException.class);

        mockMvc.perform( MockMvcRequestBuilders
                .get(RequestMappingConstant.PRODUCT_BASKET_PATH)
                    .param("userId", String.valueOf(userId))
                ).andExpect(status().isForbidden());

        // Then
    }

    @Test
    public void testAddProductToBasket_ShouldSuccess() throws Exception {

        // Given
        long userId = 1L;
        BasketDTO basketDTO = BasketMockData.getBasket();
        String jsonRequest = objectMapper.writeValueAsString(basketDTO);

        // When
        when(basketService.addProductToBasket(anyLong(), any(BasketDTO.class)))
                .thenReturn(basketDTO);

        mockMvc.perform( MockMvcRequestBuilders
                .post(RequestMappingConstant.PRODUCT_BASKET_PATH)
                    .param("userId", String.valueOf(userId))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest)
                ).andExpect(status().isCreated());

        // Then
        verify(basketService).addProductToBasket(anyLong(), any(BasketDTO.class));
    }

    @Test
    public void testAddProductToBasket_ShouldThrowForbiddenException() throws Exception {

        // Given
        long userId = 1L;
        BasketDTO basketDTO = BasketMockData.getBasket();
        String jsonRequest = objectMapper.writeValueAsString(basketDTO);

        // When
        when(basketService.addProductToBasket(anyLong(), any(BasketDTO.class)))
                .thenThrow(ForbiddenException.class);

        mockMvc.perform( MockMvcRequestBuilders
                .post(RequestMappingConstant.PRODUCT_BASKET_PATH)
                    .param("userId", String.valueOf(userId))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest)
                ).andExpect(status().isForbidden());

        // Then
    }

    @Test
    public void testDeleteProductFromBasket_ShouldSuccess() throws Exception {

        // Given
        long userId = 1L;
        BasketDTO basketDTO = BasketMockData.getBasket();
        String jsonRequest = objectMapper.writeValueAsString(basketDTO);

        // When
        mockMvc.perform( MockMvcRequestBuilders
                .delete(RequestMappingConstant.PRODUCT_BASKET_PATH)
                    .param("userId", String.valueOf(userId))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest)
                ).andExpect(status().isOk());

        // Then
        verify(basketService).deleteProductFromBasket(anyLong(), any(BasketDTO.class));
    }
}