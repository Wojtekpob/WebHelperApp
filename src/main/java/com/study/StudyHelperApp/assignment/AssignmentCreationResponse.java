package com.study.StudyHelperApp.assignment;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AssignmentCreationResponse {
    private String assignmentTitle;
    private String assignedTo;
    private String assignedFrom;
}
