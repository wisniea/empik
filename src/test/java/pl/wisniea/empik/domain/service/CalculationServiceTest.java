package pl.wisniea.empik.domain.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CalculationServiceTest {
    @Autowired
    private CalculationService calculationService;

    @Test
    public void calculationTest() {
        assertEquals(BigDecimal.valueOf(0.005707762560).setScale(12, RoundingMode.HALF_UP), calculationService.calculateGithubDetails(10512L, 8L));
        assertEquals(BigDecimal.valueOf(33).setScale(12, RoundingMode.HALF_UP), calculationService.calculateGithubDetails(2L, 9L));
    }
}
