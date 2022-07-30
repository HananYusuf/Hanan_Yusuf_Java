package com.company.M2ChallengeYusufHanan.controller;

import com.company.M2ChallengeYusufHanan.model.Month;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//testing a spring application
@RunWith(SpringRunner.class)
@WebMvcTest(MonthController.class)//running a test spring model view app on routes on this controller
public class MonthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //truns java obj in memory into Json obj
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp(){

    }


    @Test
    public void shouldReturnMonthByMonthNum() throws Exception {//the jackson mapper has a checked exception so we have to declare that my method throws the exception

        Month inputMonth = new Month();
        inputMonth.setName("MARCH");
        inputMonth.setNumber(3);


        String inputJson = mapper.writeValueAsString(inputMonth);
//performing a get to month endpoint
        mockMvc.perform(
                        get("/month/3")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(content().json(inputJson)); // ASSERT that the record was updated successfully.
    }

    @Test
    public void shouldReturnMonthById() throws Exception {

        // ARRANGE
        Month outputRecord = new Month();
        outputRecord.setName("FEBRUARY");
        outputRecord.setNumber(2);


        String outputJson = mapper.writeValueAsString(outputRecord);

        // ACT
        mockMvc.perform(get("/month/2"))
                .andDo(print())
                .andExpect(status().isOk())                     // ASSERT that we got back 200 OK.
                .andExpect(content().json(outputJson));         // ASSERT that what we're expecting is what we got back.
    }


    @Test
    public void shouldReturn404StatusCodeIfMonthNumNotFound() throws Exception {
        mockMvc.perform(get("/month/19"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void shouldReturn422StatusCodeIfInputOutOfRange() throws Exception {
        mockMvc.perform(get("/month/something"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());             // ASSERT (status code is 422)
    }

    @Test
    public void shouldReturnNonEmptyValueForRandomMonth() throws Exception {
        mockMvc.perform(get("/randomMonth"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty()
                );
    }

    @Test
    public void shouldReturnAValueFromMonthList() throws Exception {
        mockMvc.perform(get("/randomMonth"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.monthName").isNotEmpty())//$ placeholder string should not be empty
                .andExpect(jsonPath("$.monthNumber").isNotEmpty());
    }

}