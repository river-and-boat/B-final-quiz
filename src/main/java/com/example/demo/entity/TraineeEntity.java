package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trainee")
public class TraineeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String office;

    private String email;

    private String zoomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupId")
    @JsonIgnore
    private GroupEntity group;
}
