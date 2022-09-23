package com.revature.taskmaster.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.taskmaster.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIntegrationTest {

    @Autowired // field level injection like this is only permitted within classes (bad practice everywhere else)
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper jsonMapper;

    @Autowired
    private UserRepository userRepo;

    private final String PATH = "/auth";
    private final String CONTENT_TYPE = "application/json";

    @Test
    @DirtiesContext // use this if your test will add/update info within the server (this tells Spring to reset the context after this test runs)
    void test_authenticate_returns201_givenValidCredentialsPayload() throws Exception {

        Credentials stubbedCredentials = new Credentials("someone", "p4$$w0RD");

        mockMvc.perform(
                    post(PATH)
                            .contentType(CONTENT_TYPE)
                            .content(jsonMapper.writeValueAsString(stubbedCredentials)))
               .andExpect(status().isOk())
               .andExpect(header().string("Content-Type", CONTENT_TYPE))
               .andExpect(jsonPath("$.id").exists())
               .andExpect(jsonPath("$.givenName").exists())
               .andExpect(jsonPath("$.surname").exists())
               .andExpect(jsonPath("$.email").exists())
               .andExpect(jsonPath("$.username").exists())
               .andExpect(jsonPath("$.role").exists());

    }

}
