package com.example.demo.dto;

import com.example.demo.entity.GroupEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDTO {
    private Long id;
    private String name;
    @JsonIgnore
    private GroupEntity group;
}
