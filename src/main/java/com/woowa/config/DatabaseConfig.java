package com.woowa.config;

import com.woowa.database.QuestionDatabase;
import com.woowa.database.QuestionMemoryDatabase;
import com.woowa.database.UserDatabase;
import com.woowa.database.UserMemoryDatabase;
import com.woowa.framework.Bean;

public class DatabaseConfig {

    @Bean
    public UserDatabase userDatabase() {
        return new UserMemoryDatabase();
    }

    @Bean
    public QuestionDatabase questionDatabase() {
        return new QuestionMemoryDatabase();
    }
}
