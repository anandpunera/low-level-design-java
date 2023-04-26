package com.lld.vr;

import com.lld.vr.controller.*;
import com.lld.vr.model.*;
import com.lld.vr.strategy.BookingStrategy;
import com.lld.vr.strategy.DefaultBookingStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VehicleRentalTests {

    private BookingController bookingController;
    private VehicleController vehicleController;
    private BranchController branchController;
    private UserController userController;
    private RatesManager ratesManager;
    private BookingStrategy bookingStrategy;

    @BeforeAll
    void setup() {
        userController = new UserController();
        ratesManager = new RatesManager();
        branchController = new BranchController();
        vehicleController = new VehicleController(branchController);
        bookingStrategy = new DefaultBookingStrategy(ratesManager, branchController);
        bookingController = new BookingController(branchController, vehicleController, userController,
                ratesManager, bookingStrategy);
    }

    @Test
    void testAddUser() {
        User user = userController.addUser("anand", BigDecimal.valueOf(1000));
        assert userController.getById(user.getId()).isPresent();
        User user1 = userController.addUser("ishaan", BigDecimal.valueOf(560));
        assert userController.getById(user1.getId()).isPresent();
        User user2 = userController.addUser("diya", BigDecimal.valueOf(300));
        assert userController.getById(user2.getId()).isPresent();
        User user3 = userController.addUser("aam", BigDecimal.valueOf(2100));
        assert userController.getById(user3.getId()).isPresent();
    }

    @Test
    void testVehicleController() {
        branchController.addBranch("Vasant Vihar");
        branchController.addBranch("CP");
        User user = userController.addUser("anand punera", BigDecimal.valueOf(1300));
        Vehicle vehicle1 = vehicleController.addVehicle(VehicleType.Sedan, "Vasant Vihar").get();
        Vehicle vehicle2 =vehicleController.addVehicle(VehicleType.SUV, "Vasant Vihar").get();
        Vehicle vehicle3 =vehicleController.addVehicle(VehicleType.Hatchback, "Vasant Vihar").get();
        Vehicle vehicle4 =vehicleController.addVehicle(VehicleType.Sedan, "CP").get();
        Vehicle vehicle5 =vehicleController.addVehicle(VehicleType.SUV, "CP").get();
        Vehicle vehicle6 =vehicleController.addVehicle(VehicleType.Hatchback, "CP").get();
        ratesManager.allocateRates("Vasant Vihar", VehicleType.SUV, BigDecimal.valueOf(150));
        ratesManager.allocateRates("Vasant Vihar", VehicleType.Sedan, BigDecimal.valueOf(100));
        ratesManager.allocateRates("Vasant Vihar", VehicleType.Hatchback, BigDecimal.valueOf(50));
        ratesManager.allocateRates("CP", VehicleType.SUV, BigDecimal.valueOf(150));
        ratesManager.allocateRates("CP", VehicleType.Sedan, BigDecimal.valueOf(100));
        ratesManager.allocateRates("CP", VehicleType.Hatchback, BigDecimal.valueOf(50));
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, 7);
        Date start = cal.getTime();

        cal.add(Calendar.HOUR_OF_DAY, 10);
        Date end = cal.getTime();
        Slot slot = new Slot(start, end);
        assert bookingController.bookVehicle(user.getId(), VehicleType.Sedan, slot);
        assert user.getBookingList().isEmpty() == false;
        Optional<List<Pair<BookingStatus,List<Vehicle>>>> optionalPairs = bookingController.viewVehicleInventory(slot);
        System.out.println(optionalPairs);
    }
}
