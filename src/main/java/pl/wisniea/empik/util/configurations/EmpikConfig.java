package pl.wisniea.empik.util.configurations;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "pl.wisniea.empik.infrastructure.repository")
@Configuration
@ComponentScan(basePackages = "pl.wisniea.empik")
@EntityScan(basePackages = "pl.wisniea.empik.infrastructure.entity")
public class EmpikConfig {

}
