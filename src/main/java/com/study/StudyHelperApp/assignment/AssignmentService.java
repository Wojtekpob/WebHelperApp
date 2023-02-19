package com.study.StudyHelperApp.assignment;

import com.study.StudyHelperApp.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;


@Data
@AllArgsConstructor

public class AssignmentService {
    @Autowired
    private final AssignmentRespository repository;

    public Assignment createAssignment(User user,AssignmentCreationRequest request){
        //TODO after adding assignedTo in Assignment add it here aswell
        Assignment assignment = Assignment.builder().user(user).build();
        return repository.save(assignment);
    }
}
