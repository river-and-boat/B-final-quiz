package com.example.demo.dto;

import com.example.demo.exception.ExceptionMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDTO {
    private Long id;
    @NotEmpty(message = ExceptionMessage.TRAINER_NAME_NOT_EMPTY)
    private String name;
}
