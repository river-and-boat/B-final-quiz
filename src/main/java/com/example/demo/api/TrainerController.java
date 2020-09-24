package com.example.demo.api;

import com.example.demo.dto.TrainerDTO;
import com.example.demo.entity.TrainerEntity;
import com.example.demo.service.TrainerService;
import com.example.demo.utils.ConvertTool;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping("/trainers")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainerDTO addTrainer(@Valid @RequestBody TrainerDTO trainerDTO) {
        TrainerEntity trainerEntity = ConvertTool.convertObject(trainerDTO, TrainerEntity.class);
        TrainerEntity savedTrainer = trainerService.saveTrainer(trainerEntity);
        return ConvertTool.convertObject(savedTrainer, TrainerDTO.class);
    }

    @DeleteMapping("/trainers/{trainer_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // url中使用_命名,接收参数是否需要用驼峰命名，如何转换？
    public void deleteTrainer(@PathVariable Long trainer_id) {
        trainerService.deleteTrainer(trainer_id);
    }

    @GetMapping("/trainers")
    @ResponseStatus(HttpStatus.OK)
    public List<TrainerDTO> getAllUnGroupedTrainers(@RequestParam boolean grouped) {
        List<TrainerEntity> trainers = trainerService.getTrainers(grouped);
        return ConvertTool.convertObject(trainers, List.class);
    }
}
