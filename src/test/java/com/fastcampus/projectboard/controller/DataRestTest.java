package com.fastcampus.projectboard.controller;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@AutoConfigureMockMvc
@Transactional
@DisplayName("DataRestTest")
@SpringBootTest
@Disabled("Spring Data Rest 통합테스트는 불필요하므로 비활성화")
public class DataRestTest {

    private MockMvc mockMvc;

    public DataRestTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("Get Article")
    void givenNothing_whenRequestArticle_then() throws Exception {

        //given

        //when & then
        mockMvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
                .andDo(print());
    }

    @Test
    @DisplayName("Get Articles")
    void givenNothing_whenRequestArticles_thenSelectArticles() throws Exception {

        //given
        //application/hal+json
        mockMvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
                .andDo(print());

    }

    @Test
    @DisplayName("Get Article comments")
    void givenNothing_whenRequestArticleComment_thenSelectArticleComments() throws Exception {

        //given
        //application/hal+json
        mockMvc.perform(get("/api/articles/1/articleComment"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")))
                .andDo(print());

    }
}
