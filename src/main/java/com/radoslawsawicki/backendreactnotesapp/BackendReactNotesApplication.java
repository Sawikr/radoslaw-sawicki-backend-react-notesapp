package com.radoslawsawicki.backendreactnotesapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "NotesApp is a REST API based application",
                description = "This is an application for creating, editing, deleting and emailing personal notes. The application also includes current exchange rates and weather forecast.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Radosław Sawicki",
                        email = "sawikr10@gmail.com",
                        url = "https://github.com/Sawikr/radoslaw-sawicki-backend-react-notesapp"
                ),
                license = @License(
                        name = "Radosław Sawicki (LinkedIn)",
                        url = "https://www.linkedin.com/in/rados%C5%82aw-sawicki-806204252/"
                )
        )
)
public class BackendReactNotesApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BackendReactNotesApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BackendReactNotesApplication.class);
    }
}
