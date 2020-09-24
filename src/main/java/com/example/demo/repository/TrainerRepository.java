package com.example.demo.repository;

import com.example.demo.entity.GroupEntity;
import com.example.demo.entity.TrainerEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainerRepository extends CrudRepository<TrainerEntity, Long> {
    List<TrainerEntity> findAllByGroupEquals(GroupEntity group);
    List<TrainerEntity> findAllByGroupIn(List<GroupEntity> groups);
    List<TrainerEntity> findAll();
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE trainer SET group_id = NULL", nativeQuery = true)
    void clearGroupForeignKey();
}
