package com.radoslawsawicki.backendreactnotesapp.watcher;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WatcherResendMailController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WatcherResendMailController.class);

    @Before("execution(* com.radoslawsawicki.backendreactnotesapp.resend.controller.ResendMailController.sendResendEmail(..))")
    public void logEventSendResendEmail() {
        LOGGER.info("Event logging: sendResendEmail method of the Controller class");
    }
}
