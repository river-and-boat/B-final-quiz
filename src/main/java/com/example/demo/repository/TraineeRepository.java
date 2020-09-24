package com.example.demo.repository;

import com.example.demo.entity.GroupEntity;
import com.example.demo.entity.TraineeEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TraineeRepository extends CrudRepository<TraineeEntity, Long> {
    List<TraineeEntity> findAllByGroupIn(List<GroupEntity> groups);
    List<TraineeEntity> findAllByGroupEquals(GroupEntity group);
    List<TraineeEntity> findAll();
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE trainee SET group_id = NULL", nativeQuery = true)
    void clearGroupForeignKey();
}
