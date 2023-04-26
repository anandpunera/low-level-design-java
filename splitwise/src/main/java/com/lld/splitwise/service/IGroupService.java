package com.lld.splitwise.service;

import com.lld.splitwise.model.Group;
import com.lld.splitwise.model.User;

public interface IGroupService {
    Group createGroup(String groupName, String description);
    boolean addUser(String group, User user);
    boolean removeUser(String groupName, User user);
}
