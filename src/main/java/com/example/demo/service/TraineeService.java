package com.example.demo.service;

import com.example.demo.entity.TraineeEntity;
import com.example.demo.exception.ExceptionMessage;
import com.example.demo.exception.trainee.TraineeNotExistException;
import com.example.demo.repository.TraineeRepository;
import org.springframework.stereotype.Service;

@Service
public class TraineeService {

    private final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public TraineeEntity saveTrainee(TraineeEntity traineeEntity) {
        return traineeRepository.save(traineeEntity);
    }

    public TraineeEntity deleteTrainee(Long traineeId) {
        TraineeEntity traineeEntity = traineeRepository.findById(traineeId)
                .orElseThrow(() -> new TraineeNotExistException(ExceptionMessage.TRAINER_NOT_EXIST));
        traineeRepository.delete(traineeEntity);
        return traineeEntity;
    }
}
