package com.stahovskyi.movieland.web.controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.stahovskyi.movieland.AbstractWebITest;
import com.stahovskyi.movieland.service.dto.request.LoginRequestDto;
import com.vladmihalcea.sql.SQLStatementCountValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DBRider
@AutoConfigureMockMvc
class LoginControllerITest extends AbstractWebITest {

    public static final String LOGIN_ENDPOINT = "/api/v1/login";

    @Autowired
    protected MockMvc mockMvc;


    @Test
    @WithMockUser(username = "test@gmmail.com", password = "test", authorities = {"ADMIN"})
    @DataSet(value = "datasets/user/users_dataset.yml")
    @DisplayName("when Get Login with Exist Credentials then Status Ok Nickname And Token Returned")
    void whenGetLogin_withExistCredentials_thenStatusOk_NicknameAndTokenReturned() throws Exception {

        SQLStatementCountValidator.reset();

        LoginRequestDto loginRequest = LoginRequestDto.builder()
                .email("darlene.edwards15@example.com")
                .password("bricks")
                .build();

        mockMvc
                .perform(post(LOGIN_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(getResponseAsString("response/users/users_response.json")));

        assertSelectCount(0); // 2 findByEmail + 1 getNickName ?? // todo need fix
    }

}