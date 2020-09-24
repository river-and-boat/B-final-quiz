package com.example.demo.service.aggregation;

import com.example.demo.entity.GroupEntity;
import com.example.demo.entity.TraineeEntity;
import com.example.demo.entity.TrainerEntity;
import com.example.demo.exception.ExceptionMessage;
import com.example.demo.exception.group.GroupingException;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutoGroupingService {

    private static final Integer GROUP_TRAINER_NUMBER = 2;

    private static final String GROUP_NAME_SUFFIX = "组";

    private final GroupRepository groupRepository;

    private final TraineeRepository traineeRepository;

    private final TrainerRepository trainerRepository;

    public AutoGroupingService(GroupRepository groupRepository,
                               TraineeRepository traineeRepository,
                               TrainerRepository trainerRepository) {
        this.groupRepository = groupRepository;
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

    @Transactional
    public List<GroupEntity> autoGrouping() {
        groupRepository.deleteAll();
        List<TrainerEntity> allTrainer = trainerRepository.findAll();
        int trainerNumber = allTrainer.size();
        if (trainerNumber < GROUP_TRAINER_NUMBER) {
            throw new GroupingException(ExceptionMessage.GROUPING_TRAINER_COUNT_LESS_THAN_2);
        }
        List<TraineeEntity> allTrainee = traineeRepository.findAll();
        Collections.shuffle(allTrainee);
        Collections.shuffle(allTrainer);
        int traineeNumber = allTrainee.size();
        int groupNumber = trainerNumber / GROUP_TRAINER_NUMBER;
        int overflowTrainee = traineeNumber % groupNumber;

        int previousTraineeNumber = 0;
        int previousTrainerNumber = 0;

        for (int groupId = 1; groupId <= groupNumber; groupId++) {
            GroupEntity group = GroupEntity.builder().name(groupId + GROUP_NAME_SUFFIX).build();
            int groupTraineeNumber = traineeNumber / groupNumber;
            if (overflowTrainee > 0) {
                groupTraineeNumber += 1;
                overflowTrainee -= 1;
            }
            List<TraineeEntity> trainees = allTrainee.stream()
                    .skip(previousTraineeNumber)
                    .limit(groupTraineeNumber)
                    .peek(t -> t.setGroup(group)).collect(Collectors.toList());
            List<TrainerEntity> trainers = allTrainer.stream()
                    .skip(previousTrainerNumber)
                    .limit(GROUP_TRAINER_NUMBER)
                    .peek(t -> t.setGroup(group)).collect(Collectors.toList());
            group.setTrainees(trainees);
            group.setTrainers(trainers);

            groupRepository.save(group);
            previousTraineeNumber += groupTraineeNumber;
            previousTrainerNumber += GROUP_TRAINER_NUMBER;
        }
        return groupRepository.findAll();
    }
}
