package Configurations;

import Persistence.Entities.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class ReplyConfig {

    @Bean
    CommandLineRunner commandLineReplyRunner() {
        return args -> {
        };
    }
}

