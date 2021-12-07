package com.sg.CarDealership.dao;

import java.util.List;

import com.sg.CarDealership.model.Vehicle;
import com.sg.CarDealership.service.VehicleQueryContext;

public interface CarDealershipVehicleDao {
	Vehicle addVehicle(Vehicle vehicle);
	List<Vehicle> getAllVehicles();
	List<Vehicle> getAllVehicles(VehicleQueryContext query);
	Vehicle getVehicleById(int id);
	boolean updateVehicle(Vehicle vehicle);

}
