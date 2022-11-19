package com.ordina.countingwords.word.frequency.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordina.countingwords.word.frequency.domain.WordFrequencyRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;




import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class WordFrequencyControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    private WordFrequencyRequest request;

    @BeforeEach
    void buildRequest() {
        request= new WordFrequencyRequest();
        request.setText("The sun shines over the lake");
        request.setWord("the");
        request.setNumber(3);
    }
    @Test
    void calculateHighestFrequency() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/calculateHighestFrequency")
                .content(mapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful())
            .andReturn()
            .getResponse();

        String content = response.getContentAsString();
        assertEquals( "{\"message\":\"The highest frequency  of a word  in the text The sun shines over the lake is 2\"}",content);
    }

    @Test
    void calculateFrequencyForWord() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/calculateFrequencyForWord")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful())
            .andReturn()
            .getResponse();

        String content = response.getContentAsString();
        assertEquals("{\"message\":\"The frequency of the is 2\"}", content);
    }

    @Test
    void calculateMostFrequentNWords() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/calculateMostFrequentNWords")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful())
            .andReturn()
            .getResponse();

        String content = response.getContentAsString();
        assertEquals("[{\"word\":\"the\",\"frequency\":2},{\"word\":\"shines\",\"frequency\":1},{\"word\":\"over\",\"frequency\":1}]", content);
    }

    @Test
    void errorResponse() throws Exception {
        request.setText("");
        MockHttpServletResponse response = mockMvc.perform(post("/calculateHighestFrequency")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful())
            .andReturn()
            .getResponse();

        String content = response.getContentAsString();
        assertEquals("Invalid input text", content);

        MockHttpServletResponse response1 = mockMvc.perform(post("/calculateFrequencyForWord")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful())
            .andReturn()
            .getResponse();

        String content1 = response1.getContentAsString();
        assertEquals("Invalid input text", content1);

        MockHttpServletResponse response2 = mockMvc.perform(post("/calculateMostFrequentNWords")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful())
            .andReturn()
            .getResponse();

        String content2 = response2.getContentAsString();
        assertEquals("Invalid input text", content2);
    }

}
