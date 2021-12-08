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
import java.util.stream.Collectors;
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
    private CarDealershipBodyStyleDao bodyStyleDao;
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
        this.bodyStyleDao = bodyStyledao;
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
        List<Vehicle> featured = vehicleDao.getAllVehicles().stream()
                .filter((v) -> v.isFeatured() == true)
                .collect(Collectors.toList());
        return featured;
    }

    @Override
    public List<Vehicle> getVehicles(VehicleQueryContext query) {
        query = validateQuery(query);
        return vehicleDao.getAllVehicles(query);
    }

    @Override
    public Vehicle getVehicleById(int id) {
        return vehicleDao.getVehicleById(id);
    }

    @Override
    public List<Special> getSpecials() {
        return specialDao.getAllSpecials();
    }

    @Override
    public Contact addContact(Contact contact) {
        return contactDao.addContact(contact);
    }

    @Override
    public Sale addSale(SaleRequestContext request) {
        Sale sale = new Sale();
        sale.setBuyerName(request.getBuyerName());
        sale.setPurchasePrice(request.getPurchasePrice());
        sale.setPurchaseDate(request.getPurchaseDate());
        sale.setEmail(request.getEmail());
        sale.setPhone(request.getPhone());
        sale.setStreet1(request.getStreet1());
        sale.setStreet2(request.getStreet2());
        sale.setCity(request.getCity());
        sale.setState(getStateFromRequest(request));
        sale.setPurchaseType(getPurchaseTypeFromRequest(request));
        sale.setVehicle(getVehicleFromRequest(request));
        sale.setUser(getUserFromRequest(request));
        
        return saleDao.addSale(sale);
    }

    @Override
    public Vehicle addVehicle(VehicleRequestContext request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setYear(request.getYear());
        vehicle.setSalePrice(request.getSalePrice());
        vehicle.setMileage(request.getMileage());
        vehicle.setVin(request.getVin());
        vehicle.setDescription(request.getDescription());
        vehicle.setPicturePath(request.getPicturePath());
        vehicle.setMake(getMakeFromRequest(request));
        vehicle.setModel(getModelFromRequest(request));
        vehicle.setCondition(getConditionFromRequest(request));
        vehicle.setBodyStyle(getBodyStyleFromRequest(request));
        vehicle.setInterior(getInteriorFromRequest(request));
        vehicle.setTrans(getTransFromRequest(request));
        vehicle.setColor(getColorFromRequest(request));
        
        return vehicleDao.addVehicle(vehicle);
    }

    @Override
    public boolean editVehicle(VehicleRequestContext request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setYear(request.getYear());
        vehicle.setSalePrice(request.getSalePrice());
        vehicle.setMileage(request.getMileage());
        vehicle.setVin(request.getVin());
        vehicle.setDescription(request.getDescription());
        vehicle.setPicturePath(request.getPicturePath());
        vehicle.setMake(getMakeFromRequest(request));
        vehicle.setModel(getModelFromRequest(request));
        vehicle.setCondition(getConditionFromRequest(request));
        vehicle.setBodyStyle(getBodyStyleFromRequest(request));
        vehicle.setInterior(getInteriorFromRequest(request));
        vehicle.setTrans(getTransFromRequest(request));
        vehicle.setColor(getColorFromRequest(request));
        
        return true;
    }

    @Override
    public boolean deleteVehicleById(int id) {
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
        return makeDao.getMakeById(request.getMakeId());
    }
    
    private Model getModelFromRequest(VehicleRequestContext request){
        return modelDao.getModelById(request.getModelId());
    }
    
    private Condition getConditionFromRequest(VehicleRequestContext request){
        return conditionDao.getConditionById(request.getConditionId());
    }
    
    private BodyStyle getBodyStyleFromRequest(VehicleRequestContext request){
        return bodyStyleDao.getBodyStyleById(request.getBodyStyleId());
    }
    
    private Interior getInteriorFromRequest(VehicleRequestContext request){
        return interiorDao.getInteriorById(request.getInteriorId());
    }
    
    private Trans getTransFromRequest(VehicleRequestContext request){
        return transDao.getTransById(request.getTransId());
    }
    
    private Color getColorFromRequest(VehicleRequestContext request){
        return colorDao.getColorById(request.getColorId());
    }
    
    private Role getRoleFromRequest(UserRequestContext request){
        return roleDao.getRoleById(request.getRoleId());
    }
    
    private State getStateFromRequest(SaleRequestContext request){
        return stateDao.getStateById(request.getStateId());
    }
    
    private PurchaseType getPurchaseTypeFromRequest(SaleRequestContext request){
        return purchaseDao.getPurchaseTypeById(request.getPurchaseTypeId());
    }
    
    private Vehicle getVehicleFromRequest(SaleRequestContext request){
        return vehicleDao.getVehicleById(request.getVehicleId());
    }
    
    private User getUserFromRequest(SaleRequestContext request){
        return userDao.getUserById(request.getUserId());
    }
    
    private VehicleQueryContext validateQuery(VehicleQueryContext query){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private VehicleQueryContext validateQueryForSalesAdmin(VehicleQueryContext query){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}