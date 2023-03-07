package com.study.StudyHelperApp.assignment;

import com.study.StudyHelperApp.user.UserRepository;
import jakarta.annotation.Nullable;
import org.hibernate.ObjectNotFoundException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

class AssignmentServiceTest {
    @Mock
    private AssignmentRespository assignmentRespository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private AssignmentService underTest;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);

//        underTest = new AssignmentService(assignmentRespository,userRepository);
    }

    @Test
    void testGetAssignmentThatExists() {
        Assignment assgn = new Assignment();
        Long id = 2L;
        assgn.setId(id);
        given(assignmentRespository.findById(id)).willReturn(Optional.of(assgn));
        Assignment test = underTest.getAssignment(id);
        verify(assignmentRespository).findById(id);
        assertEquals(assgn,test);
    }

    @Test
    void testThrowsGetAssignmentDoesNotExist() {
        Assignment assgn = new Assignment();
        Long id = 2L;
        given(assignmentRespository.findById(id)).willReturn(Optional.ofNullable(null));

        assertThrows(ObjectNotFoundException.class, ()->{underTest.getAssignment(id);});
        verify(assignmentRespository).findById(id);
    }

    @Test
    void updateAssignment() {
    }

    @Test
    void getAssignments() {
    }
}