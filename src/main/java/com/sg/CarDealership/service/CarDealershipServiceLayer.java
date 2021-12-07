/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.model.Contact;
import com.sg.CarDealership.model.Make;
import com.sg.CarDealership.model.Model;
import com.sg.CarDealership.model.Sale;
import com.sg.CarDealership.model.Special;
import com.sg.CarDealership.model.User;
import com.sg.CarDealership.model.Vehicle;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface CarDealershipServiceLayer {
    public List<Vehicle> home();
    public List<Vehicle> getVehicles(VehicleQueryContext query);
    public Vehicle getVehicleById(int id);
    public List<Special> getSpecials();
    public Contact addContact(Contact contact);
    public Sale addSale(Sale sale, int vehicleId, int userId);
    public Vehicle addVehicle(VehicleRequestContext vehicleContext);
    public boolean editVehicle(VehicleRequestContext vehicleContext);
    public List<User> getUsers();
    public User addUser(UserRequestContext userContext);
    public boolean updateUser(UserRequestContext userContext);
    public boolean updatePassword(PasswordChangeContext pass);
    public List<Make> getMakes();
    public List<Model> getModels();
    public Make addMake(Make make);
    public Model addModel(Model model);
    public Special addSpecial(Special special);
    public boolean deleteSpecialById(int id);
    public List<SalesReport> getSalesReport(ReportQueryContext query);
    public List<InventoryReport> getInventoryReport();
}
