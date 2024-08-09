package com.as_pasa.t1_htsk1.configuration;

import com.as_pasa.t1_htsk1.aspects.LoggingAspectAround;
import com.as_pasa.t1_htsk1.aspects.LoggingAspectMultiple;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AspectConfiguration {


    private final Logger logger;

    @Autowired
    public AspectConfiguration(Logger logger) {
        this.logger = logger;
    }


    @Bean
    @ConditionalOnProperty(name = "aspect.enabled", havingValue = "around")
    public LoggingAspectAround loggingAspect() {
        return new LoggingAspectAround(logger);
    }


    @Bean
    @ConditionalOnProperty(name = "aspect.enabled", havingValue = "multiple", matchIfMissing = true)
    public LoggingAspectMultiple loggingAspectMultiple() {
        return new LoggingAspectMultiple(logger);
    }

}
