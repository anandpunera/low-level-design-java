package com.lld.rideshare;

import com.lld.rideshare.controllers.RideController;
import com.lld.rideshare.controllers.UserController;
import com.lld.rideshare.controllers.VehicleController;
import com.lld.rideshare.models.Brand;
import com.lld.rideshare.models.Gender;
import com.lld.rideshare.models.Place;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RdeShareApplicationTests {

    private UserController userController;
    private VehicleController vehicleController;
    private RideController rideController;

    @BeforeAll
    void setup() {
        userController = new UserController();
        vehicleController = new VehicleController();
        rideController = new RideController();
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

//    offer_ride(“Rohan, Origin=Hyderabad, Available Seats=1, KA-01-12345, Destination= Bangalore”)
//    offer_ride(“Shipra, Origin=Bangalore, Available Seats=1, KA-12-12332, Destination=Mysore”)
//    offer_ride(“Shipra, Origin=Bangalore, Available Seats=2, KA-05-41491, Destination=Mysore”)
//    offer_ride(“Shashank, Origin=Hyderabad, Available Seats=2, TS-05-62395, Destination=Bangalore”)
//    offer_ride(“Rahul, Origin=Hyderabad, Available Seats=5,  KA-05-1234, Destination=Bangalore”)
//    offer_ride(“Rohan, Origin=Bangalore, Available Seats=1, KA-01-12345, Destination=Pune”)

    @Test
    void testOfferRide() {
        rideController.offerRide("Rohan", Place.Hyderabad, Place.Bengaluru, (short) 1, "KA-01-12345");
        rideController.offerRide("Shipra", Place.Bengaluru, Place.Mysore, (short) 1, "KA-12-12332");
        rideController.offerRide("Shipra", Place.Bengaluru, Place.Mysore, (short) 2, "KA-05-41491");
        rideController.offerRide("Shashank", Place.Hyderabad, Place.Bengaluru, (short) 2, "TS-05-62395");
        rideController.offerRide("Rahul", Place.Hyderabad, Place.Bengaluru, (short) 5, "KA-05-1234");
        rideController.offerRide("Rohan", Place.Bengaluru, Place.Pune, (short) 1, "KA-01-12345");

        assert rideController.getRideMap().size() == 5;
    }

    @Test
    void testSelectRide() {
        rideController.offerRide("Rohan", Place.Hyderabad, Place.Bengaluru, (short) 1, "KA-01-12345");
        rideController.offerRide("Shipra", Place.Bengaluru, Place.Mysore, (short) 1, "KA-12-12332");
        rideController.offerRide("Shipra", Place.Bengaluru, Place.Mysore, (short) 2, "KA-05-41491");
        rideController.offerRide("Shashank", Place.Hyderabad, Place.Bengaluru, (short) 2, "TS-05-62395");
        rideController.offerRide("Rahul", Place.Hyderabad, Place.Bengaluru, (short) 5, "KA-05-1234");
        rideController.offerRide("Rohan", Place.Bengaluru, Place.Pune, (short) 1, "KA-01-12345");

        assert rideController.getRideMap().size() == 5;
    }
}
