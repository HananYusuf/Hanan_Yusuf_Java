package com.trylogyed.musicstorerecommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trylogyed.musicstorerecommendations.model.AlbumRecommendation;
import com.trylogyed.musicstorerecommendations.repository.AlbumRecommendationRepository;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(AlbumRecommendationController.class)
public class AlbumRecommendationControllerTest {

    @Autowired
    MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    AlbumRecommendationRepository albumRepository;

    private AlbumRecommendation album;


    @Before
    public void setup() throws Exception {
        setUpAlbumRecommendationMock();
    }

    public void setUpAlbumRecommendationMock() {
        album = new AlbumRecommendation();
        album.setAlbumId(1);
        album.setUserId(1);
        album.setLiked(true);

        AlbumRecommendation albumWithId = new AlbumRecommendation();
        albumWithId.setAlbumRecommendationId(1);
        albumWithId.setAlbumId(1);
        albumWithId.setUserId(1);
        albumWithId.setLiked(true);

        AlbumRecommendation otherAlbum = new AlbumRecommendation();
        otherAlbum.setAlbumRecommendationId(2);
        otherAlbum.setAlbumId(1);
        otherAlbum.setUserId(1);
        otherAlbum.setLiked(false);

        List<AlbumRecommendation> albumRecList = new ArrayList<>();
        albumRecList.add(albumWithId);
        albumRecList.add(otherAlbum);

        doReturn(albumRecList).when(albumRepository).findAll();
        doReturn(albumWithId).when(albumRepository).save(album);

    }

    @Test
    public void shouldCreateAlbumRecommendationAndReturnStatus201() throws Exception {
        album = new AlbumRecommendation();
        album.setAlbumId(1);
        album.setUserId(1);
        album.setLiked(true);

        AlbumRecommendation albumWithId = new AlbumRecommendation();
        albumWithId.setAlbumRecommendationId(1);
        albumWithId.setAlbumId(1);
        albumWithId.setUserId(1);
        albumWithId.setLiked(true);

        String outputAlbumJson = mapper.writeValueAsString(albumWithId);
        String inputAlbumJson = mapper.writeValueAsString(album);

        doReturn(albumWithId).when(albumRepository).save(album);

        mockMvc.perform(post("/albumR")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputAlbumJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputAlbumJson));


    }

    @Test
    public void shouldReturnAllAlbumsRecommendation() throws Exception{

        List<AlbumRecommendation> albumList = new ArrayList<AlbumRecommendation>();
        albumList.add(new AlbumRecommendation(1,1, 1, true));
        albumList.add(new AlbumRecommendation(1,1, 1, false));

        Mockito.when(albumRepository.findAll()).thenReturn(albumList);
        String expectedJson = mapper.writeValueAsString(albumList);

        mockMvc.perform(get("/albumR"))//Act
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedJson));

    }


    @Test
    public void shouldUpdateAlbumRecommendationAndReturnStatus204() throws Exception {

        AlbumRecommendation inputAlbum = new AlbumRecommendation();
        inputAlbum.setAlbumRecommendationId(1);
        inputAlbum.setAlbumId(1);
        inputAlbum.setUserId(1);
        inputAlbum.setLiked(true);

        AlbumRecommendation outputAlbum = new AlbumRecommendation();
        outputAlbum.setAlbumRecommendationId(1);
        outputAlbum.setAlbumId(1);
        outputAlbum.setUserId(1);
        outputAlbum.setLiked(false);

        String inputJson = mapper.writeValueAsString(inputAlbum);
        String outputJson = mapper.writeValueAsString(outputAlbum);

        when(albumRepository.save(inputAlbum)).thenReturn(outputAlbum);

        mockMvc.perform(
                        put("/albumR")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }


    @Test
    public void ShouldFindAlbumRecommendationByIdAndReturnStatus200() throws Exception {

        AlbumRecommendation thisAlbum = new AlbumRecommendation();
        thisAlbum.setAlbumRecommendationId(2);
        thisAlbum.setAlbumId(5);
        thisAlbum.setUserId(8);
        thisAlbum.setLiked(false);

        Optional<AlbumRecommendation> found = Optional.of(thisAlbum);
        when(albumRepository.findById(2)).thenReturn(found);

        String expectedJsonValue = mapper.writeValueAsString(thisAlbum);

        mockMvc.perform(get("/albumR/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedJsonValue));

    }

    @Test
    public void ShouldReturn422ErrorIfAlbumRecommendationNotFound() throws Exception {
        AlbumRecommendation thisAlbum = new AlbumRecommendation();
        thisAlbum.setAlbumRecommendationId(2);
        thisAlbum.setAlbumId(5);
        thisAlbum.setUserId(8);
        thisAlbum.setLiked(false);

        Optional<AlbumRecommendation> found = Optional.of(thisAlbum);
        when(albumRepository.findById(2)).thenReturn(found);

        String expectedJsonValue = mapper.writeValueAsString(thisAlbum);

        mockMvc.perform(get("/albumR/5"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }


    @Test
    public void ShouldReturn422ErrorOnDeleteIfAlbumRecommendationNotExists() throws Exception {
        AlbumRecommendation badRecommendation = new AlbumRecommendation();
        badRecommendation.setAlbumRecommendationId(12);
        badRecommendation.setAlbumId(5);
        badRecommendation.setUserId(8);
        badRecommendation.setLiked(true);

        Optional<AlbumRecommendation> found = Optional.of(badRecommendation);
        when(albumRepository.findById(12)).thenReturn(found);

        albumRepository.delete(badRecommendation);
        Mockito.verify(albumRepository, times(1)).delete(badRecommendation);

        String expectedJsonValue = mapper.writeValueAsString(badRecommendation);

        mockMvc.perform(delete("/albumR/10")) //Act
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void ShouldFindAndDeleteAlbum() throws Exception {
        AlbumRecommendation album1 = new AlbumRecommendation(1, 1, 1, false);

        Optional<AlbumRecommendation> optionalAlbum = Optional.of(album1);
        Mockito.when(albumRepository.findById(1)).thenReturn(optionalAlbum);

        if (optionalAlbum.isPresent()){
            album1 = optionalAlbum.get();
        }

        String expectedJsonValue = mapper.writeValueAsString(album1);

        mockMvc.perform(delete("/albumR/1")) //Act
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}