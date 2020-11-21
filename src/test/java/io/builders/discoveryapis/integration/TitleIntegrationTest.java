package io.builders.discoveryapis.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.builders.discoveryapis.DiscoveryapisApplication;
import io.builders.discoveryapis.dto.CreateTitleDTO;
import io.builders.discoveryapis.dto.TitleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {DiscoveryapisApplication.class})
@AutoConfigureMockMvc
@Import(DeleteAllEndpoint.class)
public class TitleIntegrationTest {

    private static final String TITLES_PATH = "/titles";
    private static final String MOVIES_PATH= TITLES_PATH.concat("/movies");
    private static final String SERIES_PATH = TITLES_PATH.concat("/series");

    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setup() throws Exception {
        mvc.perform(delete("/clear")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void should_create_movie_and_list_all_clients() throws Exception {
        mvc.perform(get(TITLES_PATH)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content", hasSize(0)));

        CreateTitleDTO titleDTO = CreateTitleDTO.builder()
                .title("simonini chronicles")
                .rating("TV-MA")
                .build();

        saveMovie(titleDTO);

        mvc.perform(get(TITLES_PATH)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content", hasSize(1)));
    }

    private TitleDTO saveMovie(CreateTitleDTO createTitleDTO) throws Exception {
        return saveTitle(MOVIES_PATH, createTitleDTO);
    }

    private TitleDTO saveSeries(CreateTitleDTO createTitleDTO) throws Exception {
        return saveTitle(SERIES_PATH, createTitleDTO);
    }

    private TitleDTO saveTitle(String path, CreateTitleDTO createTitleDTO) throws Exception {
        String response = mvc.perform(post(path)
                .content(objectMapper.writeValueAsString(createTitleDTO))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title", is(createTitleDTO.getTitle())))
                .andExpect(jsonPath("$.rating", is(createTitleDTO.getRating())))
                .andReturn()
                .getResponse()
                .getContentAsString();
        return objectMapper.readValue(response, TitleDTO.class);
    }
}
