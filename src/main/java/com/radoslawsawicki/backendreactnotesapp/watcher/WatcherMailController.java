package com.radoslawsawicki.backendreactnotesapp.watcher;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WatcherMailController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WatcherMailController.class);

    @Before("execution(* com.radoslawsawicki.backendreactnotesapp.mail.controller.MailController.getEmail(..))")
    public void logEventGetEmail() {
        LOGGER.info("Event logging: getEmail method of the Controller class");
    }

    @Before("execution(* com.radoslawsawicki.backendreactnotesapp.mail.controller.MailController.sendEmail(..))")
    public void logEventSendEmail() {
        LOGGER.info("Event logging: sendEmail method of the Controller class");
    }
}
