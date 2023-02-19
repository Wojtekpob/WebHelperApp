package com.study.StudyHelperApp.assignment;

import com.study.StudyHelperApp.user.User;
import com.study.StudyHelperApp.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Data
@AllArgsConstructor
@Service
public class AssignmentService {
    @Autowired
    private final AssignmentRespository assignmentRepository;
    @Autowired
    private final UserRepository userRepository;

    public Assignment createAssignment(User assignedFrom,AssignmentCreationRequest request){
        Optional<User> foundUser = userRepository.findByUsername(request.getUsername());
        if (foundUser==null) {
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
}
