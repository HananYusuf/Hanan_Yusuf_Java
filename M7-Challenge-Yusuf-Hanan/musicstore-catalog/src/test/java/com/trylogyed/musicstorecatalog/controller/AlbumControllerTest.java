package com.trylogyed.musicstorecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trylogyed.musicstorecatalog.model.Album;
import com.trylogyed.musicstorecatalog.model.Artist;
import com.trylogyed.musicstorecatalog.model.Track;
import com.trylogyed.musicstorecatalog.repository.AlbumRepository;
import javassist.NotFoundException;
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

import java.math.BigDecimal;
import java.time.LocalDate;
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
@WebMvcTest(AlbumController.class)
public class AlbumControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AlbumRepository albumRepository;

    ObjectMapper mapper = new ObjectMapper();

    private Album album;
    private List<Album> allAlbums = new ArrayList<>();
    private String albumJson;
    private String allAlbumsJson;

    @Before
    public void setup() throws Exception {
        setUpAlbumMock();
    }

    public void setUpAlbumMock() {
        album = new Album();
        album.setTitle("Title");
        album.setLabelId(1);
        album.setArtistId(1);
        album.setListPrice(new BigDecimal("10.30"));
        album.setReleaseDate(LocalDate.ofEpochDay(2022 - 01 - 02));

        Album albumWithId = new Album();
        albumWithId.setId(1);
        albumWithId.setTitle("Title");
        albumWithId.setLabelId(1);
        albumWithId.setArtistId(1);
        albumWithId.setListPrice(new BigDecimal("10.30"));
        albumWithId.setReleaseDate(LocalDate.ofEpochDay(2022 - 01 - 02));

        Album otherAlbum = new Album();
        otherAlbum.setId(2);
        otherAlbum.setTitle("Title2");
        otherAlbum.setLabelId(1);
        otherAlbum.setArtistId(2);
        otherAlbum.setListPrice(new BigDecimal("10.19"));
        otherAlbum.setReleaseDate(LocalDate.ofEpochDay(2022 - 01 - 02));

        allAlbums.add(albumWithId);
        allAlbums.add(otherAlbum);

        doReturn(allAlbums).when(albumRepository).findAll();
        doReturn(albumWithId).when(albumRepository).save(album);

    }

    @Test
    public void shouldCreateAlbum() throws Exception {
        Album inputAlbum = new Album();
        inputAlbum.setTitle("Title1");
        inputAlbum.setLabelId(1);
        inputAlbum.setArtistId(1);
        inputAlbum.setListPrice(new BigDecimal("10.99"));
        inputAlbum.setReleaseDate(LocalDate.ofEpochDay(2022 - 01 - 01));

        Album outputAlbum = new Album();
        outputAlbum.setId(1);
        outputAlbum.setTitle("Title1");
        outputAlbum.setLabelId(1);
        outputAlbum.setArtistId(1);
        outputAlbum.setListPrice(new BigDecimal("10.99"));
        outputAlbum.setReleaseDate(LocalDate.ofEpochDay(2022 - 01 - 01));

        String outputAlbumJson = mapper.writeValueAsString(outputAlbum);
        String inputAlbumJson = mapper.writeValueAsString(inputAlbum);

        doReturn(outputAlbum).when(albumRepository).save(inputAlbum);

        mockMvc.perform(post("/album")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputAlbumJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputAlbumJson));


    }


    @Test
    public void shouldReturnAllAlbums() throws Exception{

        List<Album> albumList = new ArrayList<Album>();
        albumList.add(new Album("Title1",1,LocalDate.ofEpochDay(2022 - 01 - 01),1,new BigDecimal("9.99")));
        albumList.add(new Album("Title1",2,LocalDate.ofEpochDay(2022 - 02 - 02),2,new BigDecimal("9.99")));

        Mockito.when(albumRepository.findAll()).thenReturn(albumList);
        String expectedJson = mapper.writeValueAsString(albumList);

        mockMvc.perform(get("/album"))//Act
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedJson));

    }


    @Test
    public void ShouldFindAlbumByIdAndReturnStatus200() throws Exception {
        Album lostAlbum = new Album();
        lostAlbum.setId(1);
        lostAlbum.setTitle("Title");
        lostAlbum.setLabelId(1);
        lostAlbum.setArtistId(2);
        lostAlbum.setListPrice(new BigDecimal("10.99"));
        lostAlbum.setReleaseDate(LocalDate.ofEpochDay(2022 - 01 - 01));

        Optional<Album> thisAlbum = Optional.of(lostAlbum);
        when(albumRepository.findById(1)).thenReturn(thisAlbum);

        String expectedJsonValue = mapper.writeValueAsString(lostAlbum);

        mockMvc.perform(get("/album/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedJsonValue));

    }


    @Test
    public void shouldUpdateAlbumAndReturnStatus204() throws Exception {

        Album inputAlbum = new Album();
        inputAlbum.setId(1);
        inputAlbum.setTitle("Title1");
        inputAlbum.setLabelId(1);
        inputAlbum.setArtistId(2);
        inputAlbum.setListPrice(new BigDecimal("10.99"));
        inputAlbum.setReleaseDate(LocalDate.ofEpochDay(2022 - 01- 01));

        Album outputAlbum = new Album();
        outputAlbum.setId(1);
        outputAlbum.setTitle("Title1");
        outputAlbum.setLabelId(1);
        outputAlbum.setArtistId(2);
        outputAlbum.setListPrice(new BigDecimal("10.99"));
        outputAlbum.setReleaseDate(LocalDate.ofEpochDay(2022 - 01 - 01));

        String inputJson = mapper.writeValueAsString(inputAlbum);
        String outputJson = mapper.writeValueAsString(outputAlbum);

        when(albumRepository.save(inputAlbum)).thenReturn(outputAlbum);

        mockMvc.perform(
                        put("/album")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    public void ShouldFindAndDeleteAlbum() throws Exception {
        Album album1 = new Album("Title1",1,LocalDate.ofEpochDay(2022 - 01 - 01),1,new BigDecimal("9.99"));

        Optional<Album> optionalAlbum = Optional.of(album1);
        Mockito.when(albumRepository.findById(1)).thenReturn(optionalAlbum);

        if (optionalAlbum.isPresent()){
            album1 = optionalAlbum.get();
        }

        String expectedJsonValue = mapper.writeValueAsString(album1);

        mockMvc.perform(delete("/album/1")) //Act
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}
