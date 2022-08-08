package com.trylogyed.musicstorecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trylogyed.musicstorecatalog.model.Artist;
import com.trylogyed.musicstorecatalog.model.Track;
import com.trylogyed.musicstorecatalog.repository.ArtistRepository;
import com.trylogyed.musicstorecatalog.repository.TrackRepository;
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
@WebMvcTest(ArtistController.class)
public class ArtistControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArtistRepository artistRepository;

    ObjectMapper mapper = new ObjectMapper();



    @Test
    public void ShouldCreateArtist() throws Exception{

        Artist inputBody = new Artist (1,"Artist1","AAIG","AAtweet");
        Artist output = new Artist (1,"Artist1","AAIG","AAtweet");
        Optional <Artist> optionalOutput = Optional.of(inputBody);
        Mockito.when(artistRepository.save(inputBody)).thenReturn(optionalOutput.get());
        String expectedJson = mapper.writeValueAsString(optionalOutput.get());
        String inputJson = mapper.writeValueAsString(inputBody);

        mockMvc.perform(post("/artist")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                //Act
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(expectedJson));
    }

    @Test
    public void shouldReturnAllArtists() throws Exception{

        List<Artist> artistList = new ArrayList<Artist>();
        artistList.add(new Artist(1,"Artist1","AAIG","AAtweet"));
        artistList.add(new Artist (2,"Artist2","AAIG","AAtweet"));
        Mockito.when(artistRepository.findAll()).thenReturn(artistList);
        String expectedJson = mapper.writeValueAsString(artistList);

        mockMvc.perform(get("/artist"))//Act
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedJson));

    }

    @Test
    public void shouldReturnArtistById()throws Exception{
        Artist expectedArtist = new Artist (1,"Artist1","AAIG","AAtweet");
        Optional <Artist> optionalExpected = Optional.of(expectedArtist);
        Mockito.when(artistRepository.findById(1)).thenReturn(optionalExpected);
        String expectedJson = mapper.writeValueAsString(expectedArtist);

        mockMvc.perform(get("/artist/1")) //Act
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedJson));

    }

    @Test
    public void shouldUpdateArtist() throws Exception {

        Artist inputBody = new Artist ("Artist1","AAIG","AAtweet");
        Artist output = new Artist (1,"Artist1","AAIG","AAtweets");
        Optional <Artist> optionalOutput = Optional.of(inputBody);
        Mockito.when(artistRepository.save(inputBody)).thenReturn(optionalOutput.get());
        String expectedJson = mapper.writeValueAsString(optionalOutput.get());
        String inputJson = mapper.writeValueAsString(inputBody);

        when(artistRepository.save(inputBody)).thenReturn(output);

        mockMvc.perform(
                        put("/artist")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());

    }

    @Test
    public void ShouldReturn422ErrorIfArtistNotFound() throws Exception {
        Artist inputBody = new Artist (1,"AA1","AAIG","AAtweet");

        Optional<Artist> optionalArtist= Optional.of(inputBody);
        when(artistRepository.findById(3)).thenReturn(optionalArtist);

        mockMvc.perform(get("/artist/21"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }


    @Test
    public void ShouldDeleteArtist() throws Exception{


        Artist artist1 = new Artist (1,"Artist1","AAIG","AAtweets");

        Optional<Artist> optionalArtist = Optional.of(artist1);
        Mockito.when(artistRepository.findById(1)).thenReturn(optionalArtist);

        if (optionalArtist.isPresent()){
            artist1 = optionalArtist.get();
        }

        String expectedJsonValue = mapper.writeValueAsString(artist1);


        mockMvc.perform(delete("/artist/1")) //Act
                .andDo(print())
                .andExpect(status().isNoContent());

    }

}