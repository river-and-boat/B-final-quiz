package com.example.demo.api.aggregation;

import com.example.demo.dto.GroupDTO;
import com.example.demo.entity.GroupEntity;
import com.example.demo.service.aggregation.AutoGroupingService;
import com.example.demo.utils.ConvertTool;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupingController {

    private final AutoGroupingService autoGroupingService;

    public GroupingController(AutoGroupingService autoGroupingService) {
        this.autoGroupingService = autoGroupingService;
    }

    @PostMapping("/groups/auto-grouping")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDTO> autoGrouping() {
        List<GroupEntity> groupingResult = autoGroupingService.autoGrouping();
        return ConvertTool.convertObject(groupingResult, List.class);
    }
}
