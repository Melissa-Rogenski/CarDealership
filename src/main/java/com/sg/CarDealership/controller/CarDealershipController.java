package com.sg.CarDealership.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.sg.CarDealership.model.Condition;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.CarDealership.model.Contact;
import com.sg.CarDealership.model.Make;
import com.sg.CarDealership.model.Model;
import com.sg.CarDealership.model.Sale;
import com.sg.CarDealership.model.Special;
import com.sg.CarDealership.model.User;
import com.sg.CarDealership.model.Vehicle;
import com.sg.CarDealership.service.CarDealershipServiceLayer;
import com.sg.CarDealership.service.InvalidContactException;
import com.sg.CarDealership.service.InvalidVehicleException;
import com.sg.CarDealership.service.InventoryReport;
import com.sg.CarDealership.service.MakeRequestContext;
import com.sg.CarDealership.service.ModelRequestContext;
import com.sg.CarDealership.service.PasswordChangeContext;
import com.sg.CarDealership.service.PasswordsDoNotMatchException;
import com.sg.CarDealership.service.ReportQueryContext;
import com.sg.CarDealership.service.SaleRequestContext;
import com.sg.CarDealership.service.SalesReport;
import com.sg.CarDealership.service.UserRequestContext;
import com.sg.CarDealership.service.VehicleQueryContext;
import com.sg.CarDealership.service.VehicleRequestContext;
import org.springframework.http.HttpStatus;
/**
 *
 * @author calebdiaz
 */
@RestController
@RequestMapping("/api")
public class CarDealershipController {
    
    private final CarDealershipServiceLayer service;
    
    public CarDealershipController(CarDealershipServiceLayer service){
        this.service = service;
    }
    
    @GetMapping("/home/index")
    public List<Vehicle> home(){
        return service.home();
    }
    
    @GetMapping("/inventory/new")
    public ResponseEntity<List<Vehicle>> getNewVehicles(@RequestBody VehicleQueryContext query){
        query.setConditionId(Condition.NEW);
        List<Vehicle> results = service.getVehicles(query);
        if (results == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(results);
    }
    
    @GetMapping("/inventory/used")
    public ResponseEntity<List<Vehicle>> getUsedVehicles(@RequestBody VehicleQueryContext query){
        query.setConditionId(Condition.USED);
        List<Vehicle> results = service.getVehicles(query);
        if (results == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(results);
    }
   
    @GetMapping("/inventory/details/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable int id){
        Vehicle result = service.getVehicleById(id);
        if (result == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("home/special")
    public List<Special> getAllSpecials(){
        return service.getSpecials();
    }
    
    @PostMapping("home/contact")
    public ResponseEntity<Contact> contact(@RequestBody Contact contact) {
        Contact result = null;
        try{
            result = service.addContact(contact);
        } catch (InvalidContactException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (result == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/sales/index")
    public ResponseEntity<List<Vehicle>> getSaleVehicles(@RequestBody VehicleQueryContext query){
        query.setConditionId(0);
        List<Vehicle> results = service.getVehicles(query);
        if (results == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(results);
    }
    
    @PostMapping("/sales/purchase/{id}")
    public ResponseEntity<Sale> logSale(@PathVariable int id, @RequestBody SaleRequestContext request){
        request.setVehicleId(id);
        Sale result = service.addSale(request);
        if (result == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/admin/vehicles")
    public ResponseEntity<List<Vehicle>> getAdminVehicles(@RequestBody VehicleQueryContext query){
        query.setConditionId(0);
        List<Vehicle> results = service.getVehicles(query);
        if (results == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(results);
    }
    
    @PostMapping("/admin/addvehicle")
    public ResponseEntity addVehicle(@RequestBody VehicleRequestContext request) throws InvalidVehicleException {
        Vehicle result = null;
        try{
            result = service.addVehicle(request);
        } catch (InvalidVehicleException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (result == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/admin/editvehicle/{id}")
    public ResponseEntity updateVehicle(@PathVariable int id, @RequestBody VehicleRequestContext request) throws InvalidVehicleException {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        
        request.setVehicleId(id);
        if (service.editVehicle(request)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }
    
    @GetMapping("/admin/users")
    public List<User> getAllUsers(){
        return service.getUsers();
    }
    
    @PostMapping("/admin/adduser")
    public ResponseEntity<User> addUser(@RequestBody UserRequestContext request){
        User result = service.addUser(request);

        if (result == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }
    
    @PutMapping("/admin/edituser/{id}")
    public ResponseEntity updateUser(@RequestBody UserRequestContext request, @PathVariable int id){
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        
        request.setUserId(id);
        if (service.updateUser(request)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }
    
    @PutMapping("/account/changepassword/{id}")
    public ResponseEntity updatePassword(@RequestBody PasswordChangeContext pass, @PathVariable int id) throws PasswordsDoNotMatchException {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        
        pass.setUserId(id);
        if (service.updatePassword(pass)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }
    
    @GetMapping("/admin/makes")
    public List<Make> getAllMakes(){
        return service.getMakes();
    }
    
    @PostMapping("/admin/makes")
    public ResponseEntity<Make> addMake(@RequestBody MakeRequestContext request){
        Make result = service.addMake(request);

        if (result == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/admin/models")
    public List<Model> getAllModels(){
        return service.getModels();
    }
    
    @PostMapping("/admin/models")
    public ResponseEntity<Model> addModel(@RequestBody ModelRequestContext request){
        Model result = service.addModel(request);

        if (result == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/admin/specials")
    public List<Special> getAdminAllSpecials(){
        return service.getSpecials();
    }
    
    @PostMapping("/admin/specials")
    public ResponseEntity<Special> addSpecial(@RequestBody Special special){
        Special result = service.addSpecial(special);

        if (result == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }
    
    @DeleteMapping("/admin/specials/{id}")
    public ResponseEntity deleteSpecial(@PathVariable int id){
        if (service.deleteSpecialById(id)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/reports/sales")
    public ResponseEntity<List<SalesReport>> getSalesReport(@RequestBody ReportQueryContext query){
        List<SalesReport> result = service.getSalesReport(query);
        
        if (result == null) {
            return new ResponseEntity("There was an error processing your request.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/reports/inventory")
    public List<InventoryReport> getInventoryReport(){
        return service.getInventoryReport();
    }

}