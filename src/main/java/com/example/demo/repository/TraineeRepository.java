package com.example.demo.repository;

import com.example.demo.entity.GroupEntity;
import com.example.demo.entity.TraineeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TraineeRepository extends CrudRepository<TraineeEntity, Long> {
    List<TraineeEntity> findAllByGroupIn(List<GroupEntity> groups);
    List<TraineeEntity> findAllByGroupNotIn(List<GroupEntity> group);
}
