package com.stahovskyi.movieland.web.controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.stahovskyi.movieland.AbstractWebITest;
import com.stahovskyi.movieland.config.TestConfigurationToCountAllQueries;
import com.stahovskyi.movieland.web.controller.request.MovieRequest;
import com.vladmihalcea.sql.SQLStatementCountValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@AutoConfigureMockMvc
@Import({
        TestConfigurationToCountAllQueries.class})
class MovieControllerITest extends AbstractWebITest {

    private static final String GET_ALL_ENDPOINT = "/api/v1/movie";

    private static final String GET_BY_ID_ENDPOINT = "/api/v1/movie/2";
    private static final String GET_ALL_BY_GENRE_ID_ENDPOINT = "/api/v1/movie/genre/1";


    @Test
    @DataSet(value = "datasets/movie/movie_dataset.yml",
            cleanAfter = true, cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("when Get All then All Movies and Ok Status Returned")
    void whenGetAll_ThenAllMovies_andOkStatusReturned() throws Exception {

        SQLStatementCountValidator.reset();

        mockMvc.perform(get(GET_ALL_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(getResponseAsString("response/movie/movie_response.json")));

        assertSelectCount(1);
    }

    @Test
    @DataSet("datasets/movie/movie_dataset.yml")
    @DisplayName("when Get All Sorted By Rating Param then All Sorted Movies and Ok Status Returned")
    void whenGetAllSortedByRating_thenAllSortedMovies_andOkStatusReturned() throws Exception {

        SQLStatementCountValidator.reset();

        var movieRequest = new MovieRequest(null, "desc");

        mockMvc.perform(post(GET_ALL_ENDPOINT)
                        .content(objectMapper.writeValueAsString(movieRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(getResponseAsString("response/movie/sorted_movie_response.json")));

        assertSelectCount(1);

    }

    @Test
    @DataSet(value = "datasets/movie/movie_dataset.yml",
            cleanAfter = true, cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("when Get All Sorted By Price Param then All Sorted Movies and Ok Status Returned")
    void whenGetAllSortedByPrice_thenAllSortedMovies_andOkStatusReturned() throws Exception {

        SQLStatementCountValidator.reset();

        var movieRequest = new MovieRequest("desc", null);

        mockMvc.perform(post(GET_ALL_ENDPOINT)
                        .content(objectMapper.writeValueAsString(movieRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(getResponseAsString("response/movie/sorted_movie_response.json")));

        assertSelectCount(1);
    }


    @Test
    @DataSet("datasets/movie/movie_by_genre_id_dataset.yml")
    @DisplayName("when Get All Movie By Genre Id then Movie By Specified Genre and Ok Status Returned")
    void whenGetAllByGenreId_thenMovieBySpecifiedGenre_andOkStatusReturned() throws Exception {

        SQLStatementCountValidator.reset();

        mockMvc.perform(get(GET_ALL_BY_GENRE_ID_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(getResponseAsString("response/movie/movie_by_genre_id_response.json")));

        assertSelectCount(1);
    }

    @Test
    @DataSet(value = "datasets/movie/movie_by_id_dataset.yml")
    @DisplayName("when Get Movie By Id then Movie With Country Genre Review and Ok Status Returned")
    void whenGetMovieById_thenMovie_andOkStatusReturned() throws Exception {

        SQLStatementCountValidator.reset();

        mockMvc.perform(get(GET_BY_ID_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(getResponseAsString("response/movie/movie_by_id_response.json")));

        assertSelectCount(6);  // todo --> need fix N + 1
    }

}