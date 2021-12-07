/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Vehicle;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface CarDealershipVehicleDao {
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(int id);
    Vehicle addVehicle(Vehicle vehicle);
    void updateVehicle(Vehicle vehicle);
    void deleteVehicleById(int id);
}
