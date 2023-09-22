package pl.wisniea.empik.communication.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.wisniea.empik.communication.dto.client.response.GithubUserDetails;
import pl.wisniea.empik.util.configurations.FeignConfig;

@FeignClient(value = "github", url = "${feign.client.config.github}", configuration = FeignConfig.class)
@Component
public interface GithubFeignClient {
    @GetMapping(value = "/users/{login}")
    GithubUserDetails getGithubUserDetails(@PathVariable String login);
}
