package com.company.M2ChallengeYusufHanan.controller;

import com.company.M2ChallengeYusufHanan.model.MathSolution;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MathSolutionController.class)
public class MathSolutionControllerTest {


    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp(){

    }


    @Test
    public void shouldReturn422StatusIfMissingOperandOrIfOperandsAreNotBothNumbersAdd () throws Exception {
        // ARRANGE
        MathSolution inputOperation = new MathSolution();
        inputOperation.setOperand1(6);
        inputOperation.setOperand2(6);
        inputOperation.setAnswer(12);
        inputOperation.setOperation("add");

        // Convert Java Object to JSON.
        String inputJson = mapper.writeValueAsString(inputOperation);

        // ACT
        mockMvc.perform(
                        post("/add")               // Perform the POST request.
                                .content(inputJson)           // Set the request body.
                                .contentType(MediaType.APPLICATION_JSON)    // Tell the server it's in JSON format.
                )
                .andDo(print())                                           // Print results to console.
                .andExpect(status().isUnprocessableEntity());             // ASSERT (status code is 422)
    }


    @Test
    public void shouldReturn422StatusIfMissingOperandOrIfOperandsAreNotBothNumbersSub () throws Exception {

        // ARRANGE
       MathSolution inputOperation = new MathSolution();
        inputOperation.setOperand1(8);
        inputOperation.setOperand2(2);
        //inputOperation.setAnswer(6);
        inputOperation.setOperation("sub");


        MathSolution outputOperation = new MathSolution();
        outputOperation.setOperand1(8);
        outputOperation.setOperand2(2);
        outputOperation.setAnswer(6);


        // Convert Java Object to JSON.
        String inputJson = mapper.writeValueAsString(inputOperation);
        String outputJson = mapper.writeValueAsString(outputOperation);

        // ACT
        mockMvc.perform(
                        post("/subtract")                                // Perform the POST request.
                                .content(inputJson)                               // Set the request body.
                                .contentType(MediaType.APPLICATION_JSON)          // Tell the server it's in JSON format.
                )
                .andDo(print())                                           // Print results to console.
                .andExpect(status().isUnprocessableEntity());             // ASSERT (status code is 422)
    }


    @Test
    public void shouldReturn422StatusIfMissingOperandOrIfOperandsAreNotBothNumbersMul () throws Exception {

        // ARRANGE
        MathSolution inputOperation = new MathSolution();
        inputOperation.setOperand1(6);
        inputOperation.setOperand2(3);
        inputOperation.setAnswer(9);
        inputOperation.setOperation("mul");

        // Convert Java Object to JSON.
        String inputJson = mapper.writeValueAsString(inputOperation);

        // ACT
        mockMvc.perform(
                        post("/multiply")                                // Perform the POST request.
                                .content(inputJson)                               // Set the request body.
                                .contentType(MediaType.APPLICATION_JSON)          // Tell the server it's in JSON format.
                )
                .andDo(print())                                           // Print results to console.
                .andExpect(status().isUnprocessableEntity());             // ASSERT (status code is 422)
    }

    @Test
    public void shouldReturn422StatusIfMissingOperandOrIfOperandsAreNotBothNumbersDiv () throws Exception {

        // ARRANGE
        MathSolution inputOperation = new MathSolution();
        inputOperation.setOperand1(10);
        inputOperation.setOperand2(2);
        inputOperation.setAnswer(5);
        inputOperation.setOperation("div");

        // Convert Java Object to JSON.
        String inputJson = mapper.writeValueAsString(inputOperation);

        // ACT
        mockMvc.perform(
                        post("/divide")                                // Perform the POST request.
                                .content(inputJson)                               // Set the request body.
                                .contentType(MediaType.APPLICATION_JSON)          // Tell the server it's in JSON format.
                )
                .andDo(print())                                           // Print results to console.
                .andExpect(status().isUnprocessableEntity());             // ASSERT (status code is 422)

    }

}