package com.study.StudyHelperApp.assignment;


import com.study.StudyHelperApp.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Assignment {
    @Id @GeneratedValue
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String comments;
    //TODO list of images
    @ManyToOne(optional = false)
    private User user;
    //TODO Add assignedTo
}
