package com.lld.rideshare;

import com.lld.rideshare.controllers.UserController;
import com.lld.rideshare.controllers.VehicleController;
import com.lld.rideshare.models.Brand;
import com.lld.rideshare.models.Gender;
import com.lld.rideshare.models.Vehicle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RdeShareApplicationTests {

    private UserController userController;
    private VehicleController vehicleController;

    @BeforeAll
    void setup() {
        userController = new UserController();
        vehicleController = new VehicleController();
    }

    @Test
    void testAddUser() {
        userController.addUser("Rohan", (short) 36, Gender.MALE);
        userController.addUser("Shashank", (short) 29, Gender.MALE);
        userController.addUser("Nandini", (short) 29, Gender.FEMALE);
        userController.addUser("Shipra", (short) 27, Gender.FEMALE);
        userController.addUser("Gaurav", (short) 29, Gender.MALE);
        userController.addUser("Rahul", (short) 35, Gender.MALE);

        assert userController.getUserByName("Nandini").isPresent();
        assert userController.getUserByName("shakal").isPresent() == false;
    }

    @Test
    void testAddVehicle() {
        vehicleController.addVehicle("KA-01-12345","Rohan", Brand.Swift, (short) 4);
        vehicleController.addVehicle("TS-05-62395","Shashank", Brand.Baleno, (short) 4);
        vehicleController.addVehicle("KA-05-41491","Shipra", Brand.Polo, (short) 3);
        vehicleController.addVehicle("KA-12-12332","Shipra", Brand.Activa, (short) 1);
        vehicleController.addVehicle("KA-05-1234","Rahul", Brand.XUV, (short) 6);

        assert vehicleController.getVehicleById("TS-05-62395").isPresent();
        assert vehicleController.getVehicleByOwnerName("Shipra").isPresent();
        assert vehicleController.getVehicleByOwnerName("Shipra").get().size() == 2;
    }
}
