package com.trylogyed.musicstorerecommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trylogyed.musicstorerecommendations.model.LabelRecommendation;
import com.trylogyed.musicstorerecommendations.repository.LabelRecommendationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LabelRecommendatonController.class)
public class LabelRecommendatonControllerTest {

    @MockBean
    LabelRecommendationRepository labelRepository;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    private LabelRecommendation label;
    private String labelJson;
    private List<LabelRecommendation> allLabels = new ArrayList<>();
    private String allLabelsJson;

    @Before
    public void setup() throws Exception {
        setUpLabelRecommendationMock();
    }

    public void setUpLabelRecommendationMock() {
        label = new LabelRecommendation();
        label.setLabelId(1);
        label.setLabelId(1);
        label.setUserId(1);
        label.setLiked(true);

        LabelRecommendation labelWithId = new LabelRecommendation();
        labelWithId.setLabelId(2);
        labelWithId.setLabelId(2);
        labelWithId.setUserId(2);
        label.setLiked(false);

        LabelRecommendation label1 = new LabelRecommendation();
        label1.setLabelId(3);
        label1.setLabelId(3);
        label1.setUserId(3);
        label1.setLiked(true);

        allLabels.add(labelWithId);
        allLabels.add(label1);

        doReturn(allLabels).when(labelRepository).findAll();
        doReturn(labelWithId).when(labelRepository).save(label);

    }

    @Test
    public void shouldReturnAllLabelRecommendation() throws Exception{

        List<LabelRecommendation> labelList = new ArrayList<LabelRecommendation>();
        labelList.add(new LabelRecommendation(1,1, 1, true));
        labelList.add(new LabelRecommendation(2,2, 2, false));

        Mockito.when(labelRepository.findAll()).thenReturn(labelList);

        String expectedJson = mapper.writeValueAsString(labelList);

        mockMvc.perform(get("/labelR"))//Act
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedJson));

    }


    @Test
    public void ShouldFindLabelRecommendationsByIdAndReturnStatus200() throws Exception {
        LabelRecommendation label = new LabelRecommendation();
        label.setLabelId(1);
        label.setLabelId(1);
        label.setUserId(1);
        label.setLiked(true);

        Optional<LabelRecommendation> thisLabel = Optional.of(label);
        when(labelRepository.findById(1)).thenReturn(thisLabel);

        if (thisLabel.isPresent()){
            label = thisLabel.get();
        }

        String expectedJsonValue = mapper.writeValueAsString(label);

        mockMvc.perform(get("/labelR/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedJsonValue));

    }

    @Test
    public void shouldReturnAllLabelRecommendationsAndStatus200() throws Exception {
        List<LabelRecommendation> labelList = new ArrayList<>();

        LabelRecommendation label = new LabelRecommendation();
        label.setLabelId(1);
        label.setLabelId(1);
        label.setUserId(1);
        label.setLiked(true);

        LabelRecommendation label1 = new LabelRecommendation();
        label1.setLabelId(2);
        label1.setLabelId(2);
        label1.setUserId(2);
        label1.setLiked(false);

        labelList.add(label);
        labelList.add(label1);

        doReturn(labelList).when(labelRepository).findAll();

        String expectedJsonValue = mapper.writeValueAsString(labelList);

        mockMvc.perform(get("/labelR"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedJsonValue));

    }

    @Test
    public void shouldCreateLabelRecommendationAndReturnNewLabelRecommendationAndStatus200() throws Exception {

        LabelRecommendation actualLabel = new LabelRecommendation();
        actualLabel.setLabelId(1);
        actualLabel.setLabelId(1);
        actualLabel.setUserId(1);
        actualLabel.setLiked(true);

        LabelRecommendation expLabel = new LabelRecommendation();
        expLabel.setLabelId(1);
        expLabel.setLabelId(1);
        expLabel.setUserId(1);
        expLabel.setLiked(true);

        String outputLabelJson = mapper.writeValueAsString(expLabel);
        String inputLabelJson = mapper.writeValueAsString(actualLabel);

        when(labelRepository.save(actualLabel)).thenReturn(expLabel);

        mockMvc.perform(post("/labelR")
                        .content(inputLabelJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(outputLabelJson));

    }

    @Test
    public void shouldUpdateLabelRecommendationAndReturnStatus204() throws Exception {

        LabelRecommendation actualLabel = new LabelRecommendation();
        actualLabel.setLabelId(1);
        actualLabel.setLabelId(1);
        actualLabel.setUserId(1);
        actualLabel.setLiked(true);


        LabelRecommendation expLabel = new LabelRecommendation();
        expLabel.setLabelId(1);
        expLabel.setLabelId(2);
        expLabel.setUserId(1);
        actualLabel.setLiked(false);


        String outputJson = mapper.writeValueAsString(expLabel);
        String inputJson = mapper.writeValueAsString(actualLabel);

        when(labelRepository.save(actualLabel)).thenReturn(expLabel);

        mockMvc.perform(
                        put("/labelR")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    public void ShouldFindAndDeleteLabelRecommendationAndReturnStatus204() throws Exception {

        LabelRecommendation label = new LabelRecommendation();
        label.setLabelId(1);
        label.setLabelId(1);
        label.setUserId(1);
        label.setLiked(true);

        Optional<LabelRecommendation> thisLabel = Optional.of(label);
        when(labelRepository.findById(1)).thenReturn(thisLabel);

        labelRepository.delete(label);
        //verify that label repo invokes the method .delete. number of times we tell the method 1 in this case
        Mockito.verify(labelRepository, times(1)).delete(label);

        String expectedJsonValue = mapper.writeValueAsString(label);

        mockMvc.perform(delete("/labelR/1")) //Act
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}