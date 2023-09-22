package pl.wisniea.empik.domain.builder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.wisniea.empik.communication.dto.client.response.GithubUserDetails;
import pl.wisniea.empik.communication.dto.response.UserResponse;
import pl.wisniea.empik.domain.service.CalculationService;

@Component
@RequiredArgsConstructor
public class UserBuilder {
    private final CalculationService calculationService;
    public UserResponse toUserResponse(GithubUserDetails githubUserDetails) {
        return UserResponse.builder()
                .id(githubUserDetails.getId())
                .login(githubUserDetails.getLogin())
                .name(githubUserDetails.getName())
                .type(githubUserDetails.getType())
                .avatarUrl(githubUserDetails.getAvatar_url())
                .createdAt(githubUserDetails.getCreated_at())
                .calculations(calculationService.calculateGithubDetails(githubUserDetails.getFollowers(), githubUserDetails.getPublic_repos()))
                .build();
    }
}
