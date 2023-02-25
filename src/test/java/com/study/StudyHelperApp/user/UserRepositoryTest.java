package com.study.StudyHelperApp.user;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;


    @Test
    void testFindUserByUsernameUserExists(){
        String username = "kak";
        User user = User.builder()
                .name("dawid")
                .username(username)
                .build();
        underTest.save(user);
        boolean present = underTest.findByUsername(username).isPresent();
        assertThat(present).isTrue();
    }


    @Test
    void testFindUserByUsernameUserDoesNotExist(){
        String username = "kak";
        boolean present = underTest.findByUsername(username).isPresent();
        assertThat(present).isFalse();
    }
}
