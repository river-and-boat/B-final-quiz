package com.example.demo.service;

import com.example.demo.entity.GroupEntity;
import com.example.demo.exception.ExceptionMessage;
import com.example.demo.exception.group.GroupNameHasExistException;
import com.example.demo.exception.group.GroupNotExistException;
import com.example.demo.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<GroupEntity> getGroups() {
        return groupRepository.findAll();
    }

    public void updateGroup(GroupEntity group) {
        // Todo：如果手写update语句则可少一条sql
        GroupEntity updatingGroup = groupRepository.findById(group.getId())
                .orElseThrow(() -> new GroupNotExistException(ExceptionMessage.GROUP_NOT_EXIST));
        groupRepository.findByName(group.getName()).ifPresent(s -> {
            throw new GroupNameHasExistException(ExceptionMessage.GROUP_NAME_HAS_EXIST);
        });
        updatingGroup.setName(group.getName());
        groupRepository.save(updatingGroup);
    }
}
