package com.radoslawsawicki.backendreactnotesapp.domain;

import com.radoslawsawicki.backendreactnotesapp.repository.LoginUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LoginUserTestSuite {

    @Autowired
    private LoginUserRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void createLoginUserTest() {
        //Given
        LoginUser loginUser = new LoginUser("Test", true);

        //When
        repository.save(loginUser);

        //Then
        assertEquals(1, repository.count());
    }


    @Test
    void getAllLoginUsersTest() {
        //Given
        LoginUser loginUser1 = new LoginUser("Test", true);
        LoginUser loginUser2 = new LoginUser("Test", true);

        //When
        repository.save(loginUser1);
        repository.save(loginUser2);

        //Then
        assertEquals(2, repository.findAll().size());
    }


    @Test
    void getLoginUserByIdTest() {

        //Given
        LoginUser loginUser = new LoginUser("Test", true);

        //When
        repository.save(loginUser);

        //Then
        assertTrue(repository.existsById(loginUser.getLoginUserId()));
    }

    @Test
    void deleteLoginUser() {
        //Given
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
