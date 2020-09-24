package com.example.demo.repository;

import com.example.demo.entity.GroupEntity;
import com.example.demo.entity.TrainerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainerRepository extends CrudRepository<TrainerEntity, Long> {
    List<TrainerEntity> findAllByGroupNotIn(List<GroupEntity> groups);
    List<TrainerEntity> findAllByGroupIn(List<GroupEntity> groups);
}
