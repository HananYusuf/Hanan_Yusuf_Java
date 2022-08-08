package com.trylogyed.musicstorecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trylogyed.musicstorecatalog.model.Label;
import com.trylogyed.musicstorecatalog.model.Track;
import com.trylogyed.musicstorecatalog.repository.LabelRepository;
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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LabelController.class)
public class LabelControllerTest {

    @MockBean
    LabelRepository labelRepository;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    private Label label;
    private String labelJson;
    private List<Label> allLabels = new ArrayList<>();
    private String allLabelsJson;

    @Before
    public void setup() throws Exception {
        setUpLabelMock();
    }

    public void setUpLabelMock() {
        label = new Label();
        label.setName("label1");
        label.setWebsite("www.website.com");

        Label labelWithId = new Label();
        labelWithId.setId(1);
        labelWithId.setName("label1");
        labelWithId.setWebsite("www.website.com");

        Label label1 = new Label();
        label1.setId(2);
        label1.setName("label2");
        label1.setWebsite("www.website2.com");

        allLabels.add(labelWithId);
        allLabels.add(label1);

        doReturn(allLabels).when(labelRepository).findAll();
        doReturn(labelWithId).when(labelRepository).save(label);

    }


    @Test
    public void ShouldFindLabelByIdAndReturnStatus200() throws Exception {
        Label label2 = new Label();
        label2.setId(1);
        label2.setName("label1");
        label2.setWebsite("www.website.com");

        Optional<Label> thisLabel = Optional.of(label2);
        when(labelRepository.findById(1)).thenReturn(thisLabel);

        if (thisLabel.isPresent()){
            label2 = thisLabel.get();
        }

        String expectedJsonValue = mapper.writeValueAsString(label2);

        mockMvc.perform(get("/label/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedJsonValue));

    }


    @Test
    public void shouldReturnAllLabels() throws Exception{

        List<Label> labelList = new ArrayList<Label>();
        labelList.add(new Label(1, "label1", "www.label1.com"));
        labelList.add(new Label(2, "label2", "www.label2.com"));

        Mockito.when(labelRepository.findAll()).thenReturn(labelList);

        String expectedJson = mapper.writeValueAsString(labelList);

        mockMvc.perform(get("/label"))//Act
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedJson));

    }


    @Test
    public void shouldCreateLabelAndReturnNewLabelAndStatus200() throws Exception {

        Label actualLabel = new Label();
        actualLabel.setId(1);
        actualLabel.setName("Label1");
        actualLabel.setWebsite("wwww.website.com");

        Label expectedLabel = new Label();
        expectedLabel.setId(1);
        expectedLabel.setName("Label1");
        expectedLabel.setWebsite("www.website.com");

        String outputLabelJson = mapper.writeValueAsString(expectedLabel);
        String inputLabelJson = mapper.writeValueAsString(actualLabel);

        when(labelRepository.save(actualLabel)).thenReturn(expectedLabel);

        mockMvc.perform(post("/label")
                        .content(inputLabelJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(outputLabelJson));

    }

    @Test
    public void shouldUpdateTrackAndReturnStatus204() throws Exception {

        Label actualLabel = new Label();
        actualLabel.setId(1);
        actualLabel.setName("Label1");
        actualLabel.setWebsite("www.website.com");

        Label expectedLabel = new Label();
        actualLabel.setId(1);
        actualLabel.setName("Label1");
        actualLabel.setWebsite("www.website.com");

        String outputJson = mapper.writeValueAsString(expectedLabel);
        String inputJson = mapper.writeValueAsString(actualLabel);

        when(labelRepository.save(actualLabel)).thenReturn(expectedLabel);

        mockMvc.perform(
                        put("/label")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }


    @Test
    public void ShouldDeleteLabel() throws Exception {
        Label label1 = new Label(1,  "Label1", "www.website.com");

        Optional<Label> optionalLabel = Optional.of(label1);
        Mockito.when(labelRepository.findById(1)).thenReturn(optionalLabel);

        if (optionalLabel.isPresent()){
            label1 = optionalLabel.get();
        }

        String expectedJsonValue = mapper.writeValueAsString(label1);

        mockMvc.perform(delete("/label/1")) //Act
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}