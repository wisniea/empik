package pl.wisniea.empik.util.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 400 -> new BadRequestException();
            case 404 -> new UserNotFoundException("User not found");
            case 503 -> new GithubServiceNotAvailableException("Github Api is unavailable");
            default -> new Exception("Exception while getting user details");
        };
    }
}
