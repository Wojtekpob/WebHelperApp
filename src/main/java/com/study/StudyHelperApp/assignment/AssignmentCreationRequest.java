package com.study.StudyHelperApp.assignment;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssignmentCreationRequest {
    private String username;
    private String title;
}
