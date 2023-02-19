package com.study.StudyHelperApp.assignment;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssignmentUpdateRequest {
    private String title;
    private String comments;
    private Long assignmentId;
}
