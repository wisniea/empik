package pl.wisniea.empik.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.wisniea.empik.communication.client.GithubFeignClient;
import pl.wisniea.empik.communication.dto.client.response.GithubUserDetails;
import pl.wisniea.empik.communication.dto.response.UserResponse;
import pl.wisniea.empik.domain.builder.UserBuilder;
import pl.wisniea.empik.infrastructure.repository.IUserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final GithubFeignClient githubFeignClient;
    private final IUserRepository userRepository;
    private final UserBuilder userBuilder;

    public ResponseEntity<UserResponse> getUser(String login) {
        //w zadaniu dosyć mocno zaakcentowane było słowo "każdego" dlatego zapis loginu oraz inkrementacja jest stosowana nawet dla niepoprawnych loginów
        userRepository.createOrIncrementRequestCount(login);
        GithubUserDetails githubUserDetails = githubFeignClient.getGithubUserDetails(login);
        return new ResponseEntity<> (userBuilder.toUserResponse(githubUserDetails), HttpStatus.OK);
    }
}
