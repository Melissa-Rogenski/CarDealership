/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.CarDealership.service;

import com.sg.CarDealership.dao.CarDealershipBodyStyleDao;
import com.sg.CarDealership.dao.CarDealershipColorDao;
import com.sg.CarDealership.dao.CarDealershipConditionDao;
import com.sg.CarDealership.dao.CarDealershipContactDao;
import com.sg.CarDealership.dao.CarDealershipInteriorDao;
import com.sg.CarDealership.dao.CarDealershipMakeDao;
import com.sg.CarDealership.dao.CarDealershipModelDao;
import com.sg.CarDealership.dao.CarDealershipPurchaseTypeDao;
import com.sg.CarDealership.dao.CarDealershipRoleDao;
import com.sg.CarDealership.dao.CarDealershipSaleDao;
import com.sg.CarDealership.dao.CarDealershipSpecialDao;
import com.sg.CarDealership.dao.CarDealershipStateDao;
import com.sg.CarDealership.dao.CarDealershipTransDao;
import com.sg.CarDealership.dao.CarDealershipUserDao;
import com.sg.CarDealership.dao.CarDealershipVehicleDao;
import com.sg.CarDealership.model.BodyStyle;
import com.sg.CarDealership.model.Color;
import com.sg.CarDealership.model.Condition;
import com.sg.CarDealership.model.Contact;
import com.sg.CarDealership.model.Interior;
import com.sg.CarDealership.model.Make;
import com.sg.CarDealership.model.Model;
import com.sg.CarDealership.model.PurchaseType;
import com.sg.CarDealership.model.Role;
import com.sg.CarDealership.model.Sale;
import com.sg.CarDealership.model.Special;
import com.sg.CarDealership.model.State;
import com.sg.CarDealership.model.Trans;
import com.sg.CarDealership.model.User;
import com.sg.CarDealership.model.Vehicle;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author calebdiaz
 */
@Repository
public class CarDealershipServiceLayerImpl implements CarDealershipServiceLayer {
    
    private CarDealershipVehicleDao vehicleDao;
    private CarDealershipMakeDao makeDao;
    private CarDealershipModelDao modelDao;
    private CarDealershipConditionDao conditionDao;
    private CarDealershipBodyStyleDao bodyStyledao;
    private CarDealershipInteriorDao interiorDao;
    private CarDealershipColorDao colorDao;
    private CarDealershipTransDao transDao;
    private CarDealershipContactDao contactDao;
    private CarDealershipUserDao userDao;
    private CarDealershipRoleDao roleDao;
    private CarDealershipPurchaseTypeDao purchaseDao;
    private CarDealershipStateDao stateDao;
    private CarDealershipSpecialDao specialDao;
    private CarDealershipSaleDao saleDao;

    public CarDealershipServiceLayerImpl(CarDealershipVehicleDao vehicleDao, CarDealershipMakeDao makeDao, 
            CarDealershipModelDao modelDao, CarDealershipConditionDao conditionDao, CarDealershipBodyStyleDao bodyStyledao, 
            CarDealershipInteriorDao interiorDao, CarDealershipColorDao colorDao, CarDealershipTransDao transDao, 
            CarDealershipContactDao contactDao, CarDealershipUserDao userDao, CarDealershipRoleDao roleDao, 
            CarDealershipPurchaseTypeDao purchaseDao, CarDealershipStateDao stateDao, CarDealershipSpecialDao specialDao, 
            CarDealershipSaleDao saleDao) {
        this.vehicleDao = vehicleDao;
        this.makeDao = makeDao;
        this.modelDao = modelDao;
        this.conditionDao = conditionDao;
        this.bodyStyledao = bodyStyledao;
        this.interiorDao = interiorDao;
        this.colorDao = colorDao;
        this.transDao = transDao;
        this.contactDao = contactDao;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.purchaseDao = purchaseDao;
        this.stateDao = stateDao;
        this.specialDao = specialDao;
        this.saleDao = saleDao;
    }

    @Override
    public List<Vehicle> home() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vehicle> getVehicles(VehicleQueryContext query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vehicle getVehicleById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Special> getSpecials() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Contact addContact(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Sale addSale(Sale sale, int vehicleId, int userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vehicle addVehicle(VehicleRequestContext vehicleContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editVehicle(VehicleRequestContext vehicleContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User addUser(UserRequestContext userContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateUser(UserRequestContext userContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updatePassword(PasswordChangeContext pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Make> getMakes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Model> getModels() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Make addMake(Make make) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Model addModel(Model model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Special addSpecial(Special special) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteSpecialById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SalesReport> getSalesReport(ReportQueryContext query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InventoryReport> getInventoryReport() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Business logic and helper methods
    private VehicleRequestContext validateVehicleRequest(VehicleRequestContext request){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
    
    private Make getMakeFromRequest(VehicleRequestContext request){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Model getModelFromRequest(VehicleRequestContext request){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Condition getConditionFromRequest(VehicleRequestContext request){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private BodyStyle getBodyStyleFromRequest(VehicleRequestContext request){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Interior getInteriorFromRequest(VehicleRequestContext request){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Trans getTransFromRequest(VehicleRequestContext request){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Color getColorFromRequest(VehicleRequestContext request){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Role getRoleFromRequest(UserRequestContext request){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private State getStateFromRequest(SaleRequestContext request){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private PurchaseType getPurchaseTypeFromRequest(SaleRequestContext request){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Vehicle getVehicleFromRequest(SaleRequestContext request){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private User getUsersfeFromRequest(SaleRequestContext request){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private VehicleQueryContext validateQuery(VehicleQueryContext query){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private VehicleQueryContext validateQueryForSalesAdmin(VehicleQueryContext query){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}