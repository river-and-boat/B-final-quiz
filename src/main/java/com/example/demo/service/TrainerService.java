package com.example.demo.service;
import com.example.demo.entity.GroupEntity;
import com.example.demo.entity.TrainerEntity;
import com.example.demo.exception.ExceptionMessage;
import com.example.demo.exception.trainer.TrainerNotExistException;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final GroupRepository groupRepository;

    public TrainerService(TrainerRepository trainerRepository,
                          GroupRepository groupRepository) {
        this.trainerRepository = trainerRepository;
        this.groupRepository = groupRepository;
    }

    public List<TrainerEntity> getTrainers(boolean grouped) {
        List<GroupEntity> groups = groupRepository.findAll();
        if (grouped) {
            return trainerRepository.findAllByGroupIn(groups);
        }
        return trainerRepository.findAllByGroupNotIn(groups);
    }

    public TrainerEntity saveTrainer(TrainerEntity trainerEntity) {
        return trainerRepository.save(trainerEntity);
    }

    public TrainerEntity deleteTrainer(Long trainerId) {
        TrainerEntity deletingTrainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new TrainerNotExistException(ExceptionMessage.TRAINER_NOT_EXIST));
        trainerRepository.delete(deletingTrainer);
        return deletingTrainer;
    }
}