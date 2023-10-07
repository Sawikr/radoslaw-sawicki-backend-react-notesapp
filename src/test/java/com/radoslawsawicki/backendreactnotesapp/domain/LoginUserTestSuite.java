package com.radoslawsawicki.backendreactnotesapp.domain;

import com.radoslawsawicki.backendreactnotesapp.repository.LoginUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LoginUserTestSuite {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoginUserTestSuite.class);

    @Autowired
    private LoginUserRepository repository;

    @BeforeEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldFetchCreateLoginUserTest() {
        //Given
        log.info("Starting test: shouldFetchCreateLoginUserTest");

        LoginUser loginUser = new LoginUser("Test", true);

        //When
        repository.save(loginUser);

        //Then
        assertEquals(1, repository.count());
    }

    @Test
    void shouldFetchGetAllLoginUsersTest() {
        //Given
        log.info("Starting test: shouldFetchGetAllLoginUsersTest");

        LoginUser loginUser1 = new LoginUser("Test", true);
        LoginUser loginUser2 = new LoginUser("Test", true);

        //When
        repository.save(loginUser1);
        repository.save(loginUser2);

        //Then
        assertEquals(2, repository.findAll().size());
    }

    @Test
    void shouldFetchGetLoginUserByIdTest() {
        //Given
        log.info("Starting test: shouldFetchGetLoginUserByIdTest");

        LoginUser loginUser = new LoginUser("Test", true);

        //When
        repository.save(loginUser);

        //Then
        assertTrue(repository.existsById(loginUser.getLoginUserId()));
    }

    @Test
    void shouldFetchDeleteLoginUser() {
        //Given
        log.info("Starting test: shouldFetchDeleteLoginUser");

        LoginUser loginUser1 = new LoginUser("Test", true);
        LoginUser loginUser2 = new LoginUser("Test", true);

        //When
        repository.save(loginUser1);
        repository.save(loginUser2);
        repository.delete(loginUser1);

        //Then
        assertEquals(1, repository.count());
    }
}
