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
    @ManyToOne(optional = false)
    private User assignedFrom;
    @ManyToOne(optional = false)
    private User assignedTo;
    private String toDoFilePath;
    private String doneFilePath;
}
