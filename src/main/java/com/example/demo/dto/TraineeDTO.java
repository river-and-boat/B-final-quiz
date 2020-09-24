package com.example.demo.dto;

import com.example.demo.entity.GroupEntity;
import com.example.demo.exception.ExceptionMessage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraineeDTO {
    private Long id;

    @NotEmpty(message = ExceptionMessage.TRAINEE_NAME_NOT_EMPTY)
    private String name;

    @NotEmpty(message = ExceptionMessage.TRAINEE_OFFICE_NOT_EMPTY)
    private String office;

    @NotEmpty(message = ExceptionMessage.TRAINEE_EMAIL_NOT_EMPTY)
    @Email(message = ExceptionMessage.TRAINEE_EMAIL_NOT_VALID)
    private String email;

    @NotEmpty(message = ExceptionMessage.TRAINEE_ZOOM_ID_NOT_VALID)
    private String zoomId;
}
