package com.study.StudyHelperApp.assignment;


import com.study.StudyHelperApp.exceptions.AccessException;
import com.study.StudyHelperApp.user.Role;
import com.study.StudyHelperApp.user.User;
import com.study.StudyHelperApp.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;


@Data
@AllArgsConstructor
@Service
public class AssignmentService {

    private final String FOLDER_PATH = "C:\\Users\\Wojtek\\Desktop\\xd\\";

    @Autowired
    private final AssignmentRespository assignmentRepository;
    @Autowired
    private final UserRepository userRepository;

    public Assignment createAssignment(User assignedFrom,AssignmentCreationRequest request) {
        if (!assignedFrom.hasRole(Role.TEACHER)){
            throw new AccessException("Only Teacher can give assignments");
        }
        Optional<User> foundUser = userRepository.findByUsername(request.getUsername());
        if (foundUser.isEmpty()) {
            throw new UsernameNotFoundException("There is no user with username: " + request.getUsername());
        }
        User assignedTo = foundUser.get();
        Assignment assignment = Assignment.builder()
                .assignedFrom(assignedFrom)
                .assignedTo(assignedTo)
                .title(request.getTitle())
                .build();
        return assignmentRepository.save(assignment);
    }

    public Assignment updateAssignment(AssignmentUpdateRequest request){
        Optional<Assignment> assignment = assignmentRepository.findById(request.getAssignmentId());
        if (assignment.isEmpty()){
            throw new ObjectNotFoundException(Assignment.class,"No assignment with such id: "+request.getAssignmentId());
        }
        Assignment assignment1=assignment.get();
        if (request.getComments() != null) assignment1.setComments(request.getComments());
        if (request.getTitle() != null) assignment1.setTitle(request.getTitle());
        return assignmentRepository.save(assignment1);
    }

    public List<Assignment> getAssignments(User user) {
        boolean showTeacher = user.hasRole(Role.TEACHER);
        if (showTeacher){
            return assignmentRepository.findByAssignedFrom(user);
        }else{
            return assignmentRepository.findByAssignedTo(user);
        }
    }

    public String uploadAssignmentFile(MultipartFile file,Long assignmentId,User user) throws IOException {
        String filePath = FOLDER_PATH;
        boolean isTeacher = user.hasRole(Role.TEACHER);
        if (isTeacher){
            filePath+="todo"+assignmentId;
        }else{
            filePath+="done"+assignmentId;
        }
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        if (assignment.isEmpty()){
            throw new ObjectNotFoundException(Assignment.class,"No assignment with such id: "+assignmentId);
        }
        Assignment assignment1=assignment.get();
        assignment1.setToDoFilePath(filePath);
        assignmentRepository.save(assignment1);

        file.transferTo(new File(filePath));

        return "Uploaded successfully";
    }

    public byte[] downloadAssignmentFile(Long fileId,boolean todo) throws IOException {
        String filePathPrefix = "done";
        if (todo==true){
            filePathPrefix="todo";
        }
        String filePath=FOLDER_PATH+filePathPrefix+fileId;
        byte[] file = Files.readAllBytes(new File(filePath).toPath());
        return file;
    }
}
