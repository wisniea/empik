package pl.wisniea.empik.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.wisniea.empik.EmpikApplication;
import pl.wisniea.empik.communication.client.GithubFeignClient;
import pl.wisniea.empik.communication.dto.client.response.ErrorResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.wisniea.empik.GithubStub.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmpikApplication.class)
@ActiveProfiles("test")
public class UserControllerIntegrationTest {

    @Autowired
    private GithubFeignClient githubFeignClient;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private WireMockServer wireMockServer;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        wireMockServer = new WireMockServer(8088);
        configureFor("localhost", 8088);
        wireMockServer.start();
    }

    @After
    public void stop() {
        wireMockServer.stop();
    }

    @Test
    public void givenGithubApiIsNotAvailable_whenGetUserCalled_ThenReturnInternalServerError() throws Exception {
        String login = "test";

        stubForInternalServerError(login);

        ErrorResponse expectedError = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
          "Github Api is unavailable","uri=/users/" + login);

        MvcResult result = mockMvc.perform(get("/users/" + login))
            .andExpect(status().isInternalServerError()).andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponse.class);

        assertEquals(expectedError.getCode(), errorResponse.getCode());
        assertEquals(expectedError.getMessage(), errorResponse.getMessage());
        assertEquals(expectedError.getStatus(), errorResponse.getStatus());
        assertEquals(expectedError.getDetails(), errorResponse.getDetails());
    }

    @Test
    public void givenUserIsNotFound_whenGetUsersCalled_ThenReturnUserNotFoundError() throws Exception {
        String login = "test";

        stubForNotFound(login);

        ErrorResponse expectedError = new ErrorResponse(HttpStatus.NOT_FOUND,
          "User not found","uri=/users/" + login);

        MvcResult result = mockMvc.perform(get("/users/" + login))
                .andExpect(status().isNotFound()).andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponse.class);

        assertEquals(expectedError.getCode(), errorResponse.getCode());
        assertEquals(expectedError.getMessage(), errorResponse.getMessage());
        assertEquals(expectedError.getStatus(), errorResponse.getStatus());
        assertEquals(expectedError.getDetails(), errorResponse.getDetails());
    }

    @Test
    public void givenUserIsFound_WhenGetUsersCalled_ThenReturnUserResponse() throws Exception {
        String login = "test";

        stubForOkBody(login);

        mockMvc.perform(get("/users/" + login))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(383316)))
                .andExpect(jsonPath("$.login", is("test")))
                .andExpect(jsonPath("$.name", is(nullValue())))
                .andExpect(jsonPath("$.type", is("User")))
                .andExpect(jsonPath("$.avatarUrl", is("https://avatars.githubusercontent.com/u/383316?v=4")))
                .andExpect(jsonPath("$.createdAt", is("2010-09-01T10:39:12.000+00:00")))
                .andExpect(jsonPath("$.calculations", is(0.857142857144)));
    }
}