package com.study.StudyHelperApp.assignment;


import com.study.StudyHelperApp.user.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rest/assignments")
@AllArgsConstructor
public class AssignmentController {
    @Autowired
    private final AssignmentService assignmentService;

    @PostMapping("/create")
    public ResponseEntity<AssignmentModificationResponse> createAssignment(
            @AuthenticationPrincipal User user,
            @RequestBody AssignmentCreationRequest request
    ){
        Assignment assignment = assignmentService.createAssignment(user, request);
        AssignmentModificationResponse response = AssignmentModificationResponse.builder()
                .assignedTo(assignment.getAssignedTo().getUsername())
                .assignedFrom(assignment.getAssignedFrom().getUsername())
                .assignmentTitle(assignment.getTitle())
                .build();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update")
    public ResponseEntity<AssignmentModificationResponse> updateAssignment(@RequestBody AssignmentUpdateRequest request){
        Assignment assignment = assignmentService.updateAssignment(request);
        AssignmentModificationResponse response = AssignmentModificationResponse.builder()
                .assignedTo(assignment.getAssignedTo().getUsername())
                .assignedFrom(assignment.getAssignedFrom().getUsername())
                .assignmentTitle(assignment.getTitle())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<List<Assignment>> getAssignments(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(assignmentService.getAssignments(user));
    }

    @PatchMapping("/file")
    public ResponseEntity<?> updateAssignmentFile(
            @AuthenticationPrincipal User user,
            @RequestParam("image")MultipartFile file,
            @RequestParam("assignment_id") Long id
    ) throws IOException {
        String resp = assignmentService.uploadAssignmentFile(file,id,user);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/file")
    public ResponseEntity<?> downloadImageFromFileSystem(
            @RequestParam("assignment_id") Long id,
            @RequestParam("todo") boolean todo
    ) throws IOException {
        byte[] imageData=assignmentService.downloadAssignmentFile(id,todo);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/pdf"))
                .body(imageData);
    }
}
