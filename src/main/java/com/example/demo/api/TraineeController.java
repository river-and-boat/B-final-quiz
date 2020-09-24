package com.example.demo.api;

import com.example.demo.dto.TraineeDTO;
import com.example.demo.entity.TraineeEntity;
import com.example.demo.service.TraineeService;
import com.example.demo.utils.ConvertTool;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TraineeController {

    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @PostMapping("/trainees")
    @ResponseStatus(value = HttpStatus.CREATED)
    public TraineeDTO addTrainee(@Valid TraineeDTO trainee) {
        TraineeEntity traineeEntity = traineeService.saveTrainee(ConvertTool
                .convertObject(trainee, TraineeEntity.class));
        return ConvertTool.convertObject(traineeEntity, TraineeDTO.class);
    }

    @DeleteMapping("/trainees/{trainee_id}")
    public TraineeDTO deleteTrainee(@PathVariable Long trainee_id) {
        TraineeEntity deletedTrainee = traineeService.deleteTrainee(trainee_id);
        return ConvertTool.convertObject(deletedTrainee, TraineeDTO.class);
    }
}
