package pl.wisniea.empik;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class GithubStub {
    public static void stubForOkBody(String login){
        stubFor(WireMock.get(urlEqualTo("/users/" + login))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                  "login": "test",
                                  "id": 383316,
                                  "node_id": "MDQ6VXNlcjM4MzMxNg==",
                                  "avatar_url": "https://avatars.githubusercontent.com/u/383316?v=4",
                                  "gravatar_id": "",
                                  "url": "https://api.github.com/users/test",
                                  "html_url": "https://github.com/test",
                                  "followers_url": "https://api.github.com/users/test/followers",
                                  "following_url": "https://api.github.com/users/test/following{/other_user}",
                                  "gists_url": "https://api.github.com/users/test/gists{/gist_id}",
                                  "starred_url": "https://api.github.com/users/test/starred{/owner}{/repo}",
                                  "subscriptions_url": "https://api.github.com/users/test/subscriptions",
                                  "organizations_url": "https://api.github.com/users/test/orgs",
                                  "repos_url": "https://api.github.com/users/test/repos",
                                  "events_url": "https://api.github.com/users/test/events{/privacy}",
                                  "received_events_url": "https://api.github.com/users/test/received_events",
                                  "type": "User",
                                  "site_admin": false,
                                  "name": null,
                                  "company": null,
                                  "blog": "",
                                  "location": null,
                                  "email": null,
                                  "hireable": null,
                                  "bio": null,
                                  "twitter_username": null,
                                  "public_repos": 5,
                                  "public_gists": 0,
                                  "followers": 49,
                                  "following": 0,
                                  "created_at": "2010-09-01T10:39:12Z",
                                  "updated_at": "2020-04-24T20:58:44Z"
                                }""")
                        .withStatus(HttpStatus.OK.value())));
    }

    public static void stubForNotFound(String login) {
        stubFor(WireMock.get(urlEqualTo("/users/" + login))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())));
    }

    public static void stubForInternalServerError(String login) {
        stubFor(WireMock.get(urlEqualTo("/users/" + login))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.SERVICE_UNAVAILABLE.value())));
    }
}
