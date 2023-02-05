package com.study.StudyHelperApp.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    List<Role> roleList = new ArrayList<>(){{add(Role.ADMIN);
        add(Role.STUDENT);
        add(Role.TEACHER);
    }};
    User user = new User(1,"dawid","dawid","dawid@mail.com",roleList);

    @Test
    void getAuthoritiesRoleCheck() {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) user.getAuthorities();
        assertTrue("ADMIN".equals(authorities.get(0).getAuthority()));
        assertTrue("STUDENT".equals(authorities.get(1).getAuthority()));
        assertTrue("TEACHER".equals(authorities.get(2).getAuthority()));
    }

    @Test
    void getAuthoritiesSize() {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) user.getAuthorities();
        assertEquals(authorities.size(),3);
    }

    @Test
    void getAuthoritiesClass() {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) user.getAuthorities();
        assertInstanceOf(GrantedAuthority.class,authorities.get(2));
    }

    @Test
    void isAccountNonExpired() {
    }

    @Test
    void isAccountNonLocked() {
    }

    @Test
    void isCredentialsNonExpired() {
    }

    @Test
    void isEnabled() {
    }
}