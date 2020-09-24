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
        // 默认group为null，即未分组
        return trainerRepository.findAllByGroupEquals(null);
    }

    public TrainerEntity saveTrainer(TrainerEntity trainerEntity) {
        return trainerRepository.save(trainerEntity);
    }

    public void deleteTrainer(Long trainerId) {
        TrainerEntity deletingTrainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new TrainerNotExistException(ExceptionMessage.TRAINER_NOT_EXIST));
        trainerRepository.delete(deletingTrainer);
    }
}
