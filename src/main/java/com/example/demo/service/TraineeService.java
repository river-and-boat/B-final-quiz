package com.example.demo.service;

import com.example.demo.entity.GroupEntity;
import com.example.demo.entity.TraineeEntity;
import com.example.demo.exception.ExceptionMessage;
import com.example.demo.exception.trainee.TraineeNotExistException;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.TraineeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeService {

    private final TraineeRepository traineeRepository;
    private final GroupRepository groupRepository;

    public TraineeService(TraineeRepository traineeRepository, GroupRepository groupRepository) {
        this.traineeRepository = traineeRepository;
        this.groupRepository = groupRepository;
    }

    public TraineeEntity saveTrainee(TraineeEntity traineeEntity) {
        return traineeRepository.save(traineeEntity);
    }

    public void deleteTrainee(Long traineeId) {
        TraineeEntity traineeEntity = traineeRepository.findById(traineeId)
                .orElseThrow(() -> new TraineeNotExistException(ExceptionMessage.TRAINEE_NOT_EXIST));
        traineeRepository.delete(traineeEntity);
    }

    public List<TraineeEntity> getTrainees(boolean grouped) {
        List<GroupEntity> groups = groupRepository.findAll();
        if (grouped) {
            return traineeRepository.findAllByGroupIn(groups);
        }
        // GTB：- 尽量不要加注释
        // 默认group为null，即未分组
        return traineeRepository.findAllByGroupEquals(null);
    }
}
