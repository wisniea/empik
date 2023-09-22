package pl.wisniea.empik.util.exceptions;

public class GithubServiceNotAvailableException extends RuntimeException {

    public GithubServiceNotAvailableException(String message) {
        super(message);
    }
}