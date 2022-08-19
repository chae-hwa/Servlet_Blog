package com.example.sbb;

import com.example.sbb.question.Question;
import com.example.sbb.question.QuestionRepository;
import com.example.sbb.user.UserRepository;
import com.example.sbb.user.UserService;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void beforeEach() {
        clearData();
        createSampleData();
    }

    public static void createSampleData(UserService userService) {
        userService.create("admin", "admin@test.com", "1234");
    }

    private void createSampleData() {
        createSampleData(userService);
    }

    public static void clearData(UserRepository userRepository) {
        userRepository.deleteAll(); // DELETE FROM question;
        userRepository.truncateTable();
    }

    private void clearData() {
        clearData(userRepository);
    }


    @Test
    @DisplayName("회원가입이 가능하다.")
    public void t1() {
        userService.create("user1", "user1@email.com", "1234");
    }
}