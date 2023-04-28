package com.lld.clear;

import com.lld.clear.dao.BalanceRepo;
import com.lld.clear.dao.ExpenseRepo;
import com.lld.clear.dao.UserRepo;
import com.lld.clear.dto.AddUserDto;
import com.lld.clear.dto.ExpenseDto;
import com.lld.clear.model.Expense;
import com.lld.clear.model.ExpenseType;
import com.lld.clear.service.ExpenseService;
import com.lld.clear.startegy.ExpenseStrategyFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClearApplicationTests {

    private UserRepo userRepo;
    private ExpenseRepo expenseController;
    private BalanceRepo balanceRepo;

    @BeforeAll
    void setup() {
        userRepo = new UserRepo();
        ExpenseStrategyFactory expenseStrategyFactory = new ExpenseStrategyFactory();
        ExpenseService expenseService = new ExpenseService(expenseStrategyFactory, userRepo);
        expenseController = new ExpenseRepo(expenseService);
        balanceRepo = new BalanceRepo(userRepo);
    }

    @Test
    void testAddUser() {

        userRepo.addUser(new AddUserDto("Anand1",""));
        userRepo.addUser(new AddUserDto("Anand2",""));
        userRepo.addUser(new AddUserDto("Anand3",""));
        userRepo.addUser(new AddUserDto("Anand4",""));
        assert userRepo.getUser("Anand1").isPresent();
        assert userRepo.getUser("X").isPresent() == false;
    }

    @Test
    void testAddExpense() {
        Map<String, BigDecimal> shares = new HashMap<>();
        shares.put("Anand1", BigDecimal.valueOf(200));
        shares.put("Anand2", BigDecimal.valueOf(300));
        shares.put("Anand3", BigDecimal.valueOf(300));
        shares.put("Anand4", BigDecimal.valueOf(200));
        Optional<Expense> expenseOptional = expenseController.addExpense(ExpenseDto.builder().expenseType(ExpenseType.EXACT).amount(
                BigDecimal.valueOf(1000)).id(UUID.randomUUID().toString()).paidBy("Anand1").shares(shares).build());
        assert expenseOptional.isPresent();
        assert balanceRepo.getBalances("Anand2").isPresent();
        assert balanceRepo.getBalances("Anand1").isPresent();
        System.out.println("Anand2 owes Anand1 "+ balanceRepo.getBalances("Anand2").get().get("Anand1"));
        assert balanceRepo.getBalances("Anand2").get().get("Anand1").equals(BigDecimal.valueOf(-300));
    }



}
