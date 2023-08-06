package com.radoslawsawicki.backendreactnotesapp.watcher;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WatcherNoteController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WatcherNoteController.class);

    @Before("execution(* com.radoslawsawicki.backendreactnotesapp.controller.NoteController.getNote(..))")
    public void logEventGetNote() {
        LOGGER.info("Event logging: getNote method of the Controller class");
    }

    @Before("execution(* com.radoslawsawicki.backendreactnotesapp.controller.NoteController.createNote(..))")
    public void logEventCreateNote() {
        LOGGER.info("Event logging: createNote method of the Controller class");
    }

    @Before("execution(* com.radoslawsawicki.backendreactnotesapp.controller.NoteController.updateNote(..))")
    public void logEventUpdateNote() {
        LOGGER.info("Event logging: updateNote method of the Controller class");
    }

    @Before("execution(* com.radoslawsawicki.backendreactnotesapp.controller.NoteController.deleteNote(..))")
    public void logEventDeleteNote() {
        LOGGER.info("Event logging: deleteNote method of the Controller class");
    }
}
