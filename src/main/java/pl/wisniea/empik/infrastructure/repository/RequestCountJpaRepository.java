package pl.wisniea.empik.infrastructure.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.wisniea.empik.infrastructure.entity.RequestCountEntity;

public interface RequestCountJpaRepository extends JpaRepository<RequestCountEntity, String> {
    @Modifying
    @Transactional
    @Query("UPDATE RequestCountEntity r SET r.requestCount = COALESCE(r.requestCount, 0) + 1 WHERE r.login = :login")
    int incrementRequestCount(String login);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO REQUEST_COUNT (login, request_count) VALUES (:login, 1)", nativeQuery = true)
    void createNewRequestCount(String login);
}
