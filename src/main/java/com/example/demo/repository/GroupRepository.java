package com.example.demo.repository;

import com.example.demo.entity.GroupEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
    List<GroupEntity> findAll();
}
