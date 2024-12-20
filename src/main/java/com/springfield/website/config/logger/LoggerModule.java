package com.springfield.website.config.logger;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        OmnixHttpLogger.class,
})
public class LoggerModule {
}
