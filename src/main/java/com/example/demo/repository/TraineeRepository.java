package com.example.demo.repository;

import com.example.demo.entity.TraineeEntity;
import org.springframework.data.repository.CrudRepository;

public interface TraineeRepository extends CrudRepository<TraineeEntity, Long> {
}
