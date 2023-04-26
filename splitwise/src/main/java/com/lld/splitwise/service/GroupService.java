package com.lld.splitwise.service;

import com.lld.splitwise.model.Group;
import com.lld.splitwise.model.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class GroupService implements IGroupService {
    Map<String, Group> groupMap = new TreeMap<>();

    @Override
    public Group createGroup(final String groupName, final String description) {
        return Optional.ofNullable(groupMap.get(groupName)).orElseGet(() -> {
            groupMap.put(groupName, Group.builder().name(groupName).description(description).build());
            return groupMap.get(groupName);
        });
    }

    @Override
    public boolean addUser(final String groupName, final User user) {
        Group group = groupMap.get(groupName);
        if(null == group) {
            return false;
        }
        return group.getMembers().add(user);
    }

    @Override
    public boolean removeUser(String groupName, User user) {
        Group group = groupMap.get(groupName);
        if(null == group) {
            return false;
        }
        return group.getMembers().remove(user);
    }
}
