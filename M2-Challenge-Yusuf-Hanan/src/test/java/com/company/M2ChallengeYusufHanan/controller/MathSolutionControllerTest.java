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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    public void shouldReturnSuccessfulResponsewhenAddTwoInt() throws Exception {
        // ARRANGE
        MathSolution inputOperation = new MathSolution();
        inputOperation.setOperand1(4);
        inputOperation.setOperand2(5);
        inputOperation.setOperation("add");

        MathSolution outputOperation = new MathSolution();
        outputOperation.setOperand1(4);
        outputOperation.setOperand2(5);
        outputOperation.setAnswer(9);


        // Convert Java Object to JSON.
        String inputJson = mapper.writeValueAsString(inputOperation);
        String outputJson = mapper.writeValueAsString(outputOperation);

        // ACT
        mockMvc.perform(
                        post("/add")                                    // Perform the POST request.
                                .content(inputJson)                               // Set the request body.
                                .contentType(MediaType.APPLICATION_JSON)          // Tell the server it's in JSON format.
                )
                .andDo(print())                                                     // Print results to console.
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"operand1\":4,\"operand2\":5,\"operation\":\"add\",\"answer\":9}"));
    }

    @Test
    public void shouldReturnSuccessfulResponsewhenSubTwoInt() throws Exception {

        // ARRANGE
        MathSolution inputOperation = new MathSolution();
        inputOperation.setOperand1(12);
        inputOperation.setOperand2(5);
        inputOperation.setOperation("sub");

        MathSolution outputOperation = new MathSolution();
        outputOperation.setOperand1(12);
        outputOperation.setOperand2(5);
        outputOperation.setAnswer(7);

        // Convert Java Object to JSON.
        String inputJson = mapper.writeValueAsString(inputOperation);
        String outputJson = mapper.writeValueAsString(outputOperation);

        // ACT
        mockMvc.perform(
                        post("/subtract")                                // Perform the POST request.
                                .content(inputJson)                               // Set the request body.
                                .contentType(MediaType.APPLICATION_JSON)          // Tell the server it's in JSON format.
                )
                .andDo(print())                                                     // Print results to console.
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"operand1\":12,\"operand2\":5,\"operation\":\"subtract\",\"answer\":7}"));       // ASSERT (status code is 422)
    }
    @Test
    public void shouldReturnSuccessfulResponsewhenMulTwoInt() throws Exception {

        // ARRANGE
        MathSolution inputOperation = new MathSolution();
        inputOperation.setOperand1(6);
        inputOperation.setOperand2(3);
        inputOperation.setOperation("mul");

        MathSolution outputOperation = new MathSolution();
        outputOperation.setOperand1(6);
        outputOperation.setOperand2(3);
        outputOperation.setAnswer(18);

        // Convert Java Object to JSON.
        String inputJson = mapper.writeValueAsString(inputOperation);
        String outputJson = mapper.writeValueAsString(outputOperation);

        // ACT
        mockMvc.perform(
                        post("/multiply")                                // Perform the POST request.
                                .content(inputJson)                               // Set the request body.
                                .contentType(MediaType.APPLICATION_JSON)          // Tell the server it's in JSON format.
                )
                .andDo(print())                                                     // Print results to console.
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"operand1\":6,\"operand2\":3,\"operation\":\"multiply\",\"answer\":18}"));       // ASSERT (status code is 422)
    }

    @Test
    public void shouldReturnSuccessfulResponsewhenDivTwoInt() throws Exception {

        // ARRANGE
        MathSolution inputOperation = new MathSolution();
        inputOperation.setOperand1(10);
        inputOperation.setOperand2(2);
        inputOperation.setOperation("div");

        MathSolution outputOperation = new MathSolution();
        outputOperation.setOperand1(10);
        outputOperation.setOperand2(2);
        outputOperation.setAnswer(5);

        // Convert Java Object to JSON.
        String inputJson = mapper.writeValueAsString(inputOperation);
        String outputJson = mapper.writeValueAsString(outputOperation);

        // ACT
        mockMvc.perform(
                        post("/division")                                // Perform the POST request.
                                .content(inputJson)                               // Set the request body.
                                .contentType(MediaType.APPLICATION_JSON)          // Tell the server it's in JSON format.
                )
                .andDo(print())                                                     // Print results to console.
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"operand1\":10,\"operand2\":2,\"operation\":\"division\",\"answer\":5}"));       // ASSERT (status code is 422)
    }

    @Test
    public void shouldReturn422StatusIfOperand2IsZero() throws Exception {
        // ARRANGE
        MathSolution inputOperation = new MathSolution();
        inputOperation.setOperand1(10);
        inputOperation.setOperand2(0);
        // Convert Java Object to JSON.
        String inputJson = mapper.writeValueAsString(inputOperation);

        // ACT
        mockMvc.perform(
                        post("/division")                                    // Perform the POST request.
                                .content(inputJson)                               // Set the request body.
                                .contentType(MediaType.APPLICATION_JSON)          // Tell the server it's in JSON format.
                )
                .andDo(print())                                                     // Print results to console.
                .andExpect(status().isUnprocessableEntity());                      // ASSERT (status code is 422)

    }

    @Test
    public void shouldReturn422StatusIfOperand1OrOperand2AreNotBothNumbersAdd() throws Exception {
        // ARRANGE
        MathSolution solution1 = new MathSolution();

        // Convert Java Object to JSON.
        String inputJson = mapper.writeValueAsString(solution1);

        // ACT
        mockMvc.perform(
                        post("/add")                                // Perform the POST request.
                                .content(inputJson)                               // Set the request body.
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"operand1\":\"something\",\"operand2\":19}")// Tell the server it's in JSON format.
                )
                .andDo(print())                                           // Print results to console.
                .andExpect(status().isUnprocessableEntity());             // ASSERT (status code is 422)
    }

    @Test
    public void shouldReturn422StatusIfOperand1OrOperand2AreNotBothNumbersSub () throws Exception {

        // ARRANGE
       MathSolution inputOperation = new MathSolution();

        // Convert Java Object to JSON.
        String inputJson = mapper.writeValueAsString(inputOperation);
        //String outputJson = mapper.writeValueAsString(outputOperation);

        // ACT
        mockMvc.perform(
                        post("/subtract")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"operand1\":something,\"operand2\":19}")   // input
                )
                .andDo(print())                                           // Print results to console.
                .andExpect(status().isUnprocessableEntity());            // ASSERT (status code is 422)
    }


    @Test
    public void shouldReturn422StatusIfOperand1OrOperand2AreNotBothNumbersMul () throws Exception {
        // ARRANGE
        MathSolution inputOperation = new MathSolution();

        // Convert Java Object to JSON.
        String inputJson = mapper.writeValueAsString(inputOperation);


        // ACT
        mockMvc.perform(
                        post("/multiply")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"operand1\":something,\"operand2\":19}")   // input
                )
                .andDo(print())                                           // Print results to console.
                .andExpect(status().isUnprocessableEntity());            // ASSERT (status code is 422)
    }

    @Test
    public void shouldReturn422StatusIfOperand1OrOperand2AreNotBothNumbersDiv() throws Exception {
        // ARRANGE
        MathSolution inputOperation = new MathSolution();

        // Convert Java Object to JSON.
        String inputJson = mapper.writeValueAsString(inputOperation);


        // ACT
        mockMvc.perform(
                        post("/division")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"operand1\":something,\"operand2\":19}")   // input
                )
                .andDo(print())                                           // Print results to console.
                .andExpect(status().isUnprocessableEntity());            // ASSERT (status code is 422)
    }



   }



