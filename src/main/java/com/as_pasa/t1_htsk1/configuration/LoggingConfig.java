package com.as_pasa.t1_htsk1.configuration;

import com.as_pasa.t1_htsk1.aspects.LoggingAspectAround;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {

    @Bean
    public Logger logger(){
        return LogManager.getLogger(LoggingAspectAround.class);
    }
}
