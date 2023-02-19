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
    public ResponseEntity<AssignmentCreationResponse> createAssignment(
            @AuthenticationPrincipal User user,
            @RequestBody AssignmentCreationRequest request
    ){
        Assignment assignment = assignmentService.createAssignment(user, request);
        AssignmentCreationResponse response = AssignmentCreationResponse.builder()
                .assignedTo(assignment.getAssignedTo().getUsername())
                .assignedFrom(assignment.getAssignedFrom().getUsername())
                .assignmentTitle(assignment.getTitle())
                .build();
        return ResponseEntity.ok(response);
    }

    // TODO Implement mapping to update assignment (@PatchMapping)

//    @PostMapping("/update")
}
