/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Vehicle;
import com.sg.CarDealership.service.VehicleQueryContext;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface CarDealershipVehicleDao {
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAllVehicles(VehicleQueryContext query);
    Vehicle getVehicleById(int id);
    Vehicle addVehicle(Vehicle vehicle);
    boolean updateVehicle(Vehicle vehicle);
    void deleteVehicleById(int id);
}
