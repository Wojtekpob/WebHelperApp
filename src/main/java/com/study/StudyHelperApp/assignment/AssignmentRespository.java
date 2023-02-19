package com.study.StudyHelperApp.assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssignmentRespository extends JpaRepository<Assignment,Long> {}
