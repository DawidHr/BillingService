package com.dawidhr.BillingService.service;

import com.dawidhr.BillingService.config.GsonConfig;
import com.dawidhr.BillingService.controller.BillController;

import com.dawidhr.BillingService.dto.bill.BillDto;
import com.dawidhr.BillingService.dto.bill.BillItemDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;;

import com.google.gson.Gson;

@AutoConfigureMockMvc
@SpringBootTest
class BillServiceMvcTest {

    @Autowired
    MockMvc mockMvc;

/*    @MockitoBean
    BillService billService;*/

    @Autowired
    Gson gson;

    @Test
    public void createTest()  throws Exception {
         BillDto request = new BillDto();
         request.setName("Test");
         request.setDescription(" Some description");
         request.setBuyDate(LocalDateTime.now());
         request.setTotalPrice(26.45);
         request.setItems(List.of(new BillItemDto("test 1", 22.12)));
         mockMvc.perform(post("/api/bill").content(gson.toJson(request)).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk());
    }

    @Test
    public void createBadRequestTest()  throws Exception {
        BillDto request = new BillDto();
        request.setBuyDate(LocalDateTime.now());
        request.setTotalPrice(26.45);
        request.setItems(List.of(new BillItemDto("test 1", 22.12)));
        mockMvc.perform(post("/api/bill").content(gson.toJson(request)).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

}