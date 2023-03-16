package com.stahovskyi.movieland.web.controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.stahovskyi.movieland.AbstractWebITest;
import com.stahovskyi.movieland.config.TestConfigurationToCountAllQueries;
import com.stahovskyi.movieland.web.controller.request.MovieRequest;
import com.stahovskyi.movieland.web.controller.request.SortDirection;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@AutoConfigureMockMvc
@Import({
        TestConfigurationToCountAllQueries.class})
class MovieControllerITest extends AbstractWebITest {

    private static final String GET_ALL_ENDPOINT = "/api/v1/movie";
    private static final String GET_RANDOM_ENDPOINT = "/api/v1/movie/random";
    private static final String GET_BY_MOVIE_ID_ENDPOINT = "/api/v1/movie/2?currency=UAH";
    private static final String GET_ALL_BY_GENRE_ID_ENDPOINT = "/api/v1/movie/genre/1";
    private static final String GET_MOVIE_BY_NOT_EXISTING_ID_ENDPOINT = "/api/v1/movie/101";


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

        var movieRequest = new MovieRequest(null, SortDirection.DESC);

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

        var movieRequest = new MovieRequest(SortDirection.ASC, null);

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

        mockMvc.perform(get(GET_BY_MOVIE_ID_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(getResponseAsString("response/movie/movie_by_id_response.json")));

        assertSelectCount(6);
    }

    @Test
    @DataSet(value = "datasets/movie/movie_dataset.yml")
    @DisplayName("when Get Movie By Not Existing Id then Not Found Returned")
    void whenGetMovieById_withNotExistingId_thenNotFoundReturned() throws Exception {

        mockMvc.perform(get(GET_MOVIE_BY_NOT_EXISTING_ID_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DataSet(value = "datasets/movie/movie_dataset.yml")
    @DisplayName("when Get Random then Random Movie and Ok Status Returned")
    void whenGetRandom_thenRandomMovie_andOkStatusReturned() throws Exception {

        SQLStatementCountValidator.reset();

        mockMvc.perform(get(GET_RANDOM_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[3]").doesNotExist());

        assertSelectCount(1);
    }

}