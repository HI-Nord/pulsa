package Configurations;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SubConfig {

    @Bean
    CommandLineRunner commandLineSubRunner() {
        return args -> {
        };
    }
}

