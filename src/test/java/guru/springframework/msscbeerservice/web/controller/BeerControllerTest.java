package guru.springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
@Slf4j
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    String beerUrl = "/api/v1/beer/";
    @Test
    void getBeerById() throws Exception {
        log.info("TEST: Executing getBeerById()");
        String url = beerUrl + UUID.randomUUID().toString();
        log.info("TEST: invoke " + beerUrl);
        mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        log.info("TEST: done!");
    }

    @Test
    void saveNewBeer() throws Exception {

        log.info("TEST: Executing saveNewBeer()");
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        log.info("TEST: invoke " + beerUrl);
        log.info("TEST: beer data = " + beerDtoJson);
        mockMvc.perform(post(beerUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
            .andExpect(status().isCreated());
        log.info("TEST: done!");
    }

    @Test
    void updateBeer() throws Exception {

        String url = beerUrl + UUID.randomUUID().toString();
        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal("4.99"))
                .upc(123456L)
                .build();
    }
}