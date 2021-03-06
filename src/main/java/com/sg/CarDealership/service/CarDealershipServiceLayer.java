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
    Contact addContact(Contact contact) throws InvalidContactException;
    Sale addSale(SaleRequestContext request);
    Vehicle addVehicle(VehicleRequestContext request) throws InvalidVehicleException;
    boolean editVehicle(VehicleRequestContext request) throws InvalidVehicleException;
    boolean deleteVehicleById(int id);
    List<User> getUsers();
    User addUser(UserRequestContext requestt);
    boolean updateUser(UserRequestContext request);
    boolean updatePassword(PasswordChangeContext pass) throws PasswordsDoNotMatchException;
    List<Make> getMakes();
    List<Model> getModels();
    Make addMake(MakeRequestContext request);
    Model addModel(ModelRequestContext request);
    Special addSpecial(Special special);
    boolean deleteSpecialById(int id);
    List<SalesReport> getSalesReport(ReportQueryContext query);
    List<InventoryReport> getInventoryReport();
}
   