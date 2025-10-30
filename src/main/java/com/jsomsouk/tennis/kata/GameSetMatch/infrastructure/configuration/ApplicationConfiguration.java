package com.jsomsouk.tennis.kata.GameSetMatch.infrastructure.configuration;

import com.jsomsouk.tennis.kata.GameSetMatch.domain.repository.ScoreboardRepository;
import com.jsomsouk.tennis.kata.GameSetMatch.infrastructure.persistence.InMemoryScoreboardRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationConfiguration {
    @Bean
    @Primary
    public ScoreboardRepository scoreboardRepository() {
        return new InMemoryScoreboardRepository();
    }
}
