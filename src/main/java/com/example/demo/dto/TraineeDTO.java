package com.example.demo.dto;

import com.example.demo.entity.GroupEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraineeDTO {
    private Long id;
    private String name;
    private String office;
    private String email;
    private String zoomId;
    @JsonIgnore
    private GroupEntity group;
}
