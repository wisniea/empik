package pl.wisniea.empik.infrastructure.repository;

public interface IUserRepository {
    void createOrIncrementRequestCount(String login);
}
