package com.example.demo.api;

import com.example.demo.dto.TraineeDTO;
import com.example.demo.entity.TraineeEntity;
import com.example.demo.service.TraineeService;
import com.example.demo.utils.ConvertTool;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TraineeController {

    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @PostMapping("/trainees")
    @ResponseStatus(value = HttpStatus.CREATED)
    public TraineeDTO addTrainee(@Valid @RequestBody TraineeDTO trainee) {
        TraineeEntity traineeEntity = traineeService.saveTrainee(ConvertTool
                .convertObject(trainee, TraineeEntity.class));
        return ConvertTool.convertObject(traineeEntity, TraineeDTO.class);
    }

    @DeleteMapping("/trainees/{trainee_id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    // GTB: - 变量不符合命名规范
    // GTB: - @PathVariable应该显式写明value属性
    public void deleteTrainee(@PathVariable Long trainee_id) {
        traineeService.deleteTrainee(trainee_id);
    }

    @GetMapping("/trainees")
    @ResponseStatus(value = HttpStatus.OK)
    // GTB: - @RequestParam应该显式写明value属性
    public List<TraineeDTO> getAllUnGroupedTrainees(@RequestParam boolean grouped) {
        List<TraineeEntity> ungroupedTrainees = traineeService.getTrainees(grouped);
        // GTB: - 建议写一个专门转换List的convert方法，避免类型安全问题
        return ConvertTool.convertObject(ungroupedTrainees, List.class);
    }
}
