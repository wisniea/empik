package pl.wisniea.empik.domain.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.wisniea.empik.infrastructure.repository.IUserRepository;
import pl.wisniea.empik.infrastructure.repository.RequestCountJpaRepository;
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements IUserRepository {
    private final RequestCountJpaRepository requestCountJpaRepository;
    @Override
    @Transactional
    public void createOrIncrementRequestCount(String login) {
        int count = requestCountJpaRepository.incrementRequestCount(login);
        if (count == 0) {
            requestCountJpaRepository.createNewRequestCount(login);
        }
    }
}
