package com.radoslawsawicki.backendreactnotesapp.watcher;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WatcherAuthServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(WatcherAuthServiceImpl.class);

    @Before("execution(* com.radoslawsawicki.backendreactnotesapp.security.service.AuthServiceImpl.updatePasswordByFields(..))")
    public void logEventUpdatePasswordByFields() {
        LOGGER.info("Event logging: updatePasswordByFields method of the AuthServiceImpl class");
    }
}
