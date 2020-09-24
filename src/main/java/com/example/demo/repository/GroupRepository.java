package com.example.demo.repository;

import com.example.demo.entity.GroupEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
    List<GroupEntity> findAll();
    Optional<GroupEntity> findByName(String name);
}
