package com.example.demo.api;

import com.example.demo.dto.GroupDTO;
import com.example.demo.entity.GroupEntity;
import com.example.demo.service.GroupService;
import com.example.demo.utils.ConvertTool;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDTO> getGroups() {
        List<GroupEntity> groups = groupService.getGroups();
        return ConvertTool.convertObject(groups, List.class);
    }

    @PatchMapping("/groups/{group_id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateGroup(@Valid @RequestBody GroupDTO groupDTO,
                                @PathVariable Long group_id) {
        groupDTO.setId(group_id);
        GroupEntity patchingGroup = ConvertTool.convertObject(groupDTO, GroupEntity.class);
        groupService.updateGroup(patchingGroup);
    }
}
