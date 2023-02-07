package com.study.StudyHelperApp.authentication;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String surname;
    private String password;
    private String username;
}
