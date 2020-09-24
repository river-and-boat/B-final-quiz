package com.example.demo.service;

import com.example.demo.entity.GroupEntity;
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
}
