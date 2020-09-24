package com.example.demo.dto;

import com.example.demo.entity.TraineeEntity;
import com.example.demo.entity.TrainerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private Long id;
    private String name;
    private List<TraineeEntity> trainees;
    private List<TrainerEntity> trainers;
}
