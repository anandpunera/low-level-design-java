package com.lld.splitwise.controller;

import com.lld.splitwise.model.Group;
import com.lld.splitwise.model.User;
import com.lld.splitwise.service.GroupService;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    public Optional<Group> createGroup(final String name, final String description) {
        Group group = groupService.createGroup(name, description);
        return Optional.of(group);
    }

    public boolean addUser(final String groupName, final User user) {
        return groupService.addUser(groupName, user);
    }

    public boolean removeUser(final String groupName, final User user) {
        return groupService.removeUser(groupName, user);
    }
}
