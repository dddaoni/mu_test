package com.example.mu.homework;

import com.example.mu.homework.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class ApiProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void 구현_1_API() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/product/getLowestCategoryList")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        assertEquals("34,100", jsonObject.get("totalPrice"));
        Assertions.assertNotNull(jsonObject.get("brandProductList"));
    }

    @Test
    public void 구현_2_API() throws Exception {
        mockMvc.perform(get("/api/product/getLowestBrandList")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void 구현_3_API() throws Exception {
        ProductDTO.ReqCategory reqParam = new ProductDTO.ReqCategory();
        reqParam.setCategory("상의");

        mockMvc.perform(post("/api/product/getLowestHighest")
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(reqParam))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andDo(MockMvcResultHandlers.print());
    }

}
