package com.stahovskyi.movieland.web.controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.stahovskyi.movieland.AbstractWebITest;
import com.stahovskyi.movieland.config.TestConfigurationToCountAllQueries;
import com.vladmihalcea.sql.SQLStatementCountValidator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Disabled
@DBRider
@AutoConfigureMockMvc
@Import({
        TestConfigurationToCountAllQueries.class})
class GenreControllerITest extends AbstractWebITest {

    private static final String GET_ALL_ENDPOINT = "/api/v1/genre";


    @Test
    @DataSet(value = "datasets/genre/genre_dataset.yml")
    @DisplayName("when GetAll then All Genres And Ok Status Returned")
    void whenGetAll_ThenAllGenres_AndOkStatusReturned() throws Exception {

        SQLStatementCountValidator.reset();

        mockMvc.perform(get(GET_ALL_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(getResponseAsString("response/genre/genre_response.json")));

        assertSelectCount(1);

    }

}