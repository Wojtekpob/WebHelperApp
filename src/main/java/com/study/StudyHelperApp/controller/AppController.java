package com.study.StudyHelperApp.controller;

import com.study.StudyHelperApp.authentication.AuthenticationService;
import com.study.StudyHelperApp.user.User;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AppController {
    @GetMapping("/test")
    public ResponseEntity<String> testResponse() {return ResponseEntity.ok("Everything works.");}
}
