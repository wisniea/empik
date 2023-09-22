package pl.wisniea.empik.domain.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculationService {
    private static final BigDecimal FOLLOWERS_DIVIDEND = BigDecimal.valueOf(6);
    private static final BigDecimal PUBLIC_REPOS_SUM_COMPONENT = BigDecimal.valueOf(2);
    public BigDecimal calculateGithubDetails(Long followers, Long publicRepos) {
        BigDecimal followersBigDecimal = BigDecimal.valueOf(followers);
        BigDecimal publicReposBigDecimal = BigDecimal.valueOf(publicRepos);

        return FOLLOWERS_DIVIDEND
                .divide(followersBigDecimal, 12, RoundingMode.HALF_UP)
                .multiply(PUBLIC_REPOS_SUM_COMPONENT.add(publicReposBigDecimal));
    }
}
