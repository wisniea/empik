package pl.wisniea.empik.domain.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.wisniea.empik.EmpikApplication;
import pl.wisniea.empik.infrastructure.repository.IUserRepository;
import pl.wisniea.empik.infrastructure.repository.RequestCountJpaRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = EmpikApplication.class)
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    RequestCountJpaRepository requestCountJpaRepository;

    @Test
    public void shouldCreateNewRequestCount() {
        final String login = "createTest";
        userRepository.createOrIncrementRequestCount(login);
        var result = requestCountJpaRepository.findById(login);
        assertTrue(result.isPresent());
        assertEquals(result.get().getLogin(), login);
        assertEquals(Long.valueOf(1L), result.get().getRequestCount());
    }

    @Test
    public void shouldIncrementExistingRequestCount() {
        final String login = "testIncrement";
        userRepository.createOrIncrementRequestCount(login);
        var result = requestCountJpaRepository.findById(login);
        assertTrue(result.isPresent());
        assertEquals(result.get().getLogin(), login);
        assertEquals(Long.valueOf(456898L), result.get().getRequestCount());
    }
}
