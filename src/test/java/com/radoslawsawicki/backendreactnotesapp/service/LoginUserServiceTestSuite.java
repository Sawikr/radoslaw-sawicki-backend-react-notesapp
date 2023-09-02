package com.radoslawsawicki.backendreactnotesapp.service;

import com.radoslawsawicki.backendreactnotesapp.domain.LoginUser;
import com.radoslawsawicki.backendreactnotesapp.exception.LoginUserNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.repository.LoginUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LoginUserServiceTestSuite {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoginUserServiceTestSuite.class);

    @Autowired
    private LoginUserRepository repository;

    @Autowired
    private LoginUserService service;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldFetchCreateLoginUserTest() {
        //Given
        log.info("Starting test: shouldFetchCreateLoginUserTest");

        LoginUser loginUser = new LoginUser("Test", true);

        // When
        service.saveLoginUser(loginUser);

        //Then
        assertTrue(repository.existsById(loginUser.getLoginUserId()));
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
        long size = service.getAllLoginUsers().size();

        //Then
        assertEquals(2, size);
    }

    @Test
    void shouldFetchGetLoginUserByIdTest() throws LoginUserNotFoundException {
        //Given
        log.info("Starting test: shouldFetchGetLoginUserByIdTest");

        LoginUser loginUser = new LoginUser("Test", true);

        // When
        repository.save(loginUser);
        LoginUser login = service.getLoginUser(loginUser.getLoginUserId());

        //Then
        assertTrue(login.isLogin());
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
        service.deleteLoginUser(loginUser1.getLoginUserId());

        //Then
        assertEquals(1, repository.findAll().size());
    }
}
