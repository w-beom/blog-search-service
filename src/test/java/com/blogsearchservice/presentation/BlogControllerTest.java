package com.blogsearchservice.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/search api 정상 호출")
    void searchTest() throws Exception {
        String query = "안녕";
        String size = "3";
        String page = "1";

        mockMvc.perform(get("/search")
                .queryParam("query", query)
                .queryParam("size", size)
                .queryParam("page", page))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("page가 50을 넘으면 400에러가 발생한다.")
    void searchTest2() throws Exception {
        String query = "안녕";
        String page = "52";

        mockMvc.perform(get("/search")
                .queryParam("query", query)
                .queryParam("page", page))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("size가 50을 넘으면 400 에러가 발생한다.")
    void searchTest3() throws Exception {
        String query = "안녕";
        String size = "555";

        mockMvc.perform(get("/search")
                .queryParam("query", query)
                .queryParam("size", size))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("인기검색어를 최대 10개까지 조회한다.")
    void popularTest() throws Exception {
        mockMvc.perform(get("/popular"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
