package com.study.StudyHelperApp.assignment;

import com.study.StudyHelperApp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AssignmentRespository extends JpaRepository<Assignment,Long> {
    public List<Assignment> findByAssignedFrom(User user);
    public List<Assignment> findByAssignedTo(User user);
}
