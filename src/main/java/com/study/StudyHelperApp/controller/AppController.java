package com.study.StudyHelperApp.controller;

import com.study.StudyHelperApp.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class AppController {
    @GetMapping
    public ResponseEntity<String> testResponse() {return ResponseEntity.ok("Everything works.");}
}
