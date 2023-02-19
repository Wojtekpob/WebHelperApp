package com.study.StudyHelperApp.assignment;


import com.study.StudyHelperApp.user.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/assignments")
@AllArgsConstructor
public class AssignmentController {
    @Autowired
    private final AssignmentService assignmentService;

    @PostMapping("/create")
    //TODO Create AssignmentCreationResponse and return it here.
    public ResponseEntity<Assignment> createAssignment(
            @AuthenticationPrincipal User user,
            @RequestBody AssignmentCreationRequest request
    ){
        return ResponseEntity.ok(assignmentService.createAssignment(user, request));
    }
}
