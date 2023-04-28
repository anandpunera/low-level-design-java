package com.lld.clear.dao;

import com.lld.clear.dto.AddUserDto;
import com.lld.clear.exceptions.UserException;
import com.lld.clear.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class UserRepo {
    Map<String, User> userMap = new HashMap<>();

    public Optional<User> addUser(AddUserDto addUserDto) {
        if(userMap.containsKey(addUserDto.getName())) {
            throw new UserException(String.format("User with name %s Already Exists", addUserDto.getName()));
        }
        userMap.put(addUserDto.getName(), new User(addUserDto.getName(), new HashMap<>()));
        return Optional.ofNullable(userMap.get(addUserDto.getName()));
    }

    public Optional<User> getUser(String name) {
        return Optional.ofNullable(userMap.get(name));
    }

    public void updateBalance(String user, String transactionUser, BigDecimal amount) {
        User primary = userMap.get(user);
        User transactedWithUser = userMap.get(transactionUser);

        if(null == primary || null == transactedWithUser) {
            throw new UserException(String.format("User with name %s or %s does not Exist", user, transactionUser));
        }
        BigDecimal currentBalance = primary.getBalances().get(transactionUser);
        if(null == currentBalance) {
            currentBalance = BigDecimal.ZERO;
        }
        primary.getBalances().put(transactionUser, currentBalance.add(amount));
        currentBalance = transactedWithUser.getBalances().get(primary.getName());
        if(null == currentBalance) {
            currentBalance = BigDecimal.ZERO;
        }
        transactedWithUser.getBalances().put(primary.getName(), currentBalance.subtract(amount));
    }

    public void settleBalances(String user, String userToSettleWith) {
        User userPrimary = userMap.get(user);
        User userToSettleWithOptional = userMap.get(userToSettleWith);
        if(null == userPrimary || null == userToSettleWithOptional) {
            throw new UserException("User Not Found");
        }
        userPrimary.getBalances().put(userToSettleWithOptional.getName(), BigDecimal.ZERO);
        userToSettleWithOptional.getBalances().put(userPrimary.getName(), BigDecimal.ZERO);
    }
}
