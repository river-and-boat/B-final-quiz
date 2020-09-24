package com.example.demo.api;

import com.example.demo.dto.GroupDTO;
import com.example.demo.entity.GroupEntity;
import com.example.demo.service.GroupService;
import com.example.demo.utils.ConvertTool;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    public List<GroupDTO> getGroups() {
        List<GroupEntity> groups = groupService.getGroups();
        return ConvertTool.convertObject(groups, List.class);
    }
}
