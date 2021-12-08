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
    
    List<Vehicle> home();
    List<Vehicle> getVehicles(VehicleQueryContext query);
    Vehicle getVehicleById(int id);
    List<Special> getSpecials();
    Contact addContact(Contact contact);
    Sale addSale(SaleRequestContext request);
    Vehicle addVehicle(VehicleRequestContext request);
    boolean editVehicle(VehicleRequestContext request, boolean toDelete);
    List<User> getUsers();
    User addUser(UserRequestContext requestt);
    boolean updateUser(UserRequestContext request);
    boolean updatePassword(PasswordChangeContext pass);
    List<Make> getMakes();
    List<Model> getModels();
    Make addMake(Make make);
    Model addModel(Model model);
    Special addSpecial(Special special);
    boolean deleteSpecialById(int id);
    List<SalesReport> getSalesReport(ReportQueryContext query);
    List<InventoryReport> getInventoryReport();
}
   