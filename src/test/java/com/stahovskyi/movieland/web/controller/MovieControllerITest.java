package com.stahovskyi.movieland.web.controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.stahovskyi.movieland.AbstractWebITest;
import com.stahovskyi.movieland.config.TestConfigurationToCountAllQueries;
import com.stahovskyi.movieland.service.dto.request.MovieRequestDto;
import com.stahovskyi.movieland.service.entity.common.CurrencyType;
import com.stahovskyi.movieland.service.entity.common.PriceSortDirection;
import com.stahovskyi.movieland.service.entity.common.RatingSortDirection;
import com.vladmihalcea.sql.SQLStatementCountValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertInsertCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@Import({
        TestConfigurationToCountAllQueries.class})
class MovieControllerITest extends AbstractWebITest {

    private static final String GET_ALL_ENDPOINT = "/api/v1/movie";
    private static final String ADD_MOVIE_ENDPOINT = "/api/v1/movie";

    private static final String UPDATE_MOVIE_ENDPOINT = "/api/v1/movie/1";
    private static final String GET_BY_MOVIE_ID_ENDPOINT = "/api/v1/movie/2";
    private static final String GET_RANDOM_ENDPOINT = "/api/v1/movie/random";
    private static final String GET_ALL_BY_GENRE_ID_ENDPOINT = "/api/v1/movie/genre/1";
    private static final String GET_MOVIE_BY_NOT_EXISTING_ID_ENDPOINT = "/api/v1/movie/101";
    private static final String UPDATE_MOVIE_BY_NOT_EXISTING_ID_ENDPOINT = "/api/v1/movie/77";

     @Autowired
    protected MockMvc mockMvc;


    @Test
    @DataSet(value = "datasets/movie/movie_dataset.yml",
            cleanAfter = true, cleanBefore = true, skipCleaningFor = "flyway_schema_history")
    @DisplayName("when Get All then All Movies and Ok Status Returned")
    void whenGetAll_ThenAllMovies_andOkStatusReturned() throws Exception {

        SQLStatementCountValidator.reset();

        mockMvc
                .perform(get(GET_ALL_ENDPOINT)
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

        mockMvc
                .perform(get(GET_ALL_ENDPOINT)
                        .queryParam("rating", RatingSortDirection.DESC.getDirectional())
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

        mockMvc
                .perform(get(GET_ALL_ENDPOINT)
                        .queryParam("price", PriceSortDirection.ASC.getDirectional())
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

        mockMvc
                .perform(get(GET_ALL_BY_GENRE_ID_ENDPOINT)
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

        mockMvc
                .perform(get(GET_BY_MOVIE_ID_ENDPOINT)
                        .queryParam("currency", CurrencyType.UAH.getType())
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

        mockMvc
                .perform(get(GET_MOVIE_BY_NOT_EXISTING_ID_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DataSet(value = "datasets/movie/movie_dataset.yml")
    @DisplayName("when Get Random then Random Movie and Ok Status Returned")
    void whenGetRandom_thenRandomMovie_andOkStatusReturned() throws Exception {

        SQLStatementCountValidator.reset();

        mockMvc
                .perform(get(GET_RANDOM_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[3]").doesNotExist());

        assertSelectCount(1);
    }

    // todo need finish test ! issue with movie id
    @Test
    @WithMockUser(username = "test@gmmail.com", password = "test", authorities = {"ADMIN"})
    @DataSet(value = "datasets/movie/movie_dataset.yml",
            cleanAfter = true,
            cleanBefore = true,
            skipCleaningFor = "flyway_schema_history")
    @ExpectedDataSet(value = "datasets/movie/expected_add_movie_dataset.yml")
    @DisplayName("when Add Movie then Movie Added and Movie Dto Returned")
    void whenAddMovie_thenMovieAdded_andMovieDtoReturned() throws Exception {

        SQLStatementCountValidator.reset();

        MovieRequestDto movieRequestDto = MovieRequestDto.builder()
                .nameRussian("Дух Часу")
                .nameNative("The spirit of time")
                .yearOfRelease(2012)
                .description("Дивовижний та заворожуючий фільм")
                .picturePath("https://images-na.ssl-images-amazon.com/images/.jpg")
                .price(155.5)
                .countries(List.of(1, 2))
                .genres(List.of(1, 2))
                .build();

        mockMvc
                .perform(MockMvcRequestBuilders.post(ADD_MOVIE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movieRequestDto)))
                .andExpect(content()
                        .json(objectMapper.writeValueAsString("response/movie/add_movie_response.json")))
                .andExpect(status().isCreated());

        assertSelectCount(3); // 1_country + 1_genre + 1_sequence
        assertInsertCount(1); // 1_insert movie

    }


    @Test
    @WithMockUser(username = "test@gmmail.com", password = "test", authorities = {"ADMIN"})
    @DataSet(value = "datasets/movie/update_movie_dataset.yml")
   // @ExpectedDataSet(value = "datasets/movie/expected_update_movie_dataset.yml")
    @DisplayName("when Add Movie then Movie Dto And OK Status Returned")
    void whenAddMovie_thenMovieDtoAndOKStatusReturned() throws Exception {

      /*  SQLStatementCountValidator.reset();

        MovieRequestDto movieRequestDto = MovieRequestDto.builder()
                .nameRussian("Побег из Шоушенка 2")
                .nameNative("The Shaw shank Redemption 2")
                .yearOfRelease(0)
                .description("")
                .picturePath("https://images-new-poster-for-movie.ssl-images-amazon.com/images/.jpg")
                .price(0.0)
                .countries(List.of(3, 4))
                .genres(List.of(3, 4))
                .build();

        mockMvc
                .perform(put(UPDATE_MOVIE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movieRequestDto)))
                .andExpect(content()
                        .json(objectMapper.writeValueAsString("response/movie/updated_movie_response.json")))
                .andExpect(status().isOk());

        assertSelectCount(3); // 1_movie + 1_country + 1_genre
        assertInsertCount(1); // 1_insert movie*/
    }

    // todo its exception test for update controller
    @Test
    @DataSet(value = "datasets/movie/update_movie_dataset.yml")
    @DisplayName("when Add Movie where Id Not Exist then Not Found Returned")
    void whenAddMovie_whereIdNotExist_thenNotFoundReturned() throws Exception {

        mockMvc
                .perform(put(UPDATE_MOVIE_BY_NOT_EXISTING_ID_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}