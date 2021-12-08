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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public Vehicle addVehicle(VehicleRequestContext request) throws InvalidVehicleException{
        validateVehicleRequest(request);
        
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
    public boolean editVehicle(VehicleRequestContext request) throws InvalidVehicleException{
        validateVehicleRequest(request);
        
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
        
        return vehicleDao.updateVehicle(vehicle);
    }

    @Override
    public boolean deleteVehicleById(int id) {
        if(vehicleDao.getVehicleById(id) != null){
            vehicleDao.deleteVehicleById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<User> getUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User addUser(UserRequestContext request) {
        User user = new User();
        user.setUserId(0);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(getRoleFromRequest(request));
        
        return userDao.addUser(user);
    }

    @Override
    public boolean updateUser(UserRequestContext request) {
        User user = new User();
        user.setUserId(request.getUserId());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(getRoleFromRequest(request));
        
        return true;
    }

    @Override
    public boolean updatePassword(PasswordChangeContext pass) throws PasswordsDoNotMatchException {
        validatePassword(pass);
        User user = userDao.getUserById(pass.getUserId());
        user.setPassword(pass.getPassword());
        return userDao.updateUser(user);
    }

    @Override
    public List<Make> getMakes() {
        return makeDao.getAllMakes();
    }

    @Override
    public List<Model> getModels() {
        return modelDao.getAllModels();
    }

    @Override
    public Make addMake(Make make, int userId) {
        make.setMakeId(0);
        make.setUser(userDao.getUserById(userId));
        return makeDao.addMake(make);
    }

    @Override
    public Model addModel(Model model, int userId, int makeId) {
        model.setModelId(0);
        model.setUser(userDao.getUserById(userId));
        model.setMake(makeDao.getMakeById(makeId));
        
        return modelDao.addModel(model);
    }

    @Override
    public Special addSpecial(Special special) {
        special.setSpecialId(0);
        return specialDao.addSpecial(special);
    }

    @Override
    public boolean deleteSpecialById(int id) {
        if(specialDao.getSpecialById(id) != null){
            specialDao.deleteSpecialById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<SalesReport> getSalesReport(ReportQueryContext query) {
        List<SalesReport> reports = new ArrayList<>(); // List of sales reports
        
        List<Sale> filtered = saleDao.getAllSales().stream() // filter down to sales that match date query
                .filter((s) -> (s.getPurchaseDate().compareTo(query.getMinDate()) >= 0))
                .filter((s) -> (s.getPurchaseDate().compareTo(query.getMaxDate()) <= 1))
                .collect(Collectors.toList());
        
        List<User> users = filtered.stream() // get list of users with sales
                .map((s) -> s.getUser())
                .collect(Collectors.toList());
        
        User user = userDao.getUserById(query.getUserId()); // get user IDed by query, if any
        
        if(query.getUserId() == 0) {
            users.stream()
                .forEach((u) -> {
                    reports.add(generateSalesReportForUser(filtered, u));
                });
        } else if(users.contains(user)){
            reports.add(generateSalesReportForUser(filtered, user));
        }
        
        return reports;
    }

    @Override
    public List<InventoryReport> getInventoryReport() {
        List<InventoryReport> reports = new ArrayList<>(); // List of inventory reports
        
        vehicleDao.getAllVehicles().stream() // List of all models of vehicles in inventory
                .map((v) -> v.getModel())
                .forEach((m) -> {
                    reports.add(generateInventoryReportForModel(m));
                });
        
        return reports;
    }
    
    // Business logic and helper methods
    private SalesReport generateSalesReportForUser(List<Sale> filtered, User user){
        SalesReport report = new SalesReport();
        
        report.setTotalSales(filtered.stream()
                .filter((s) -> s.getUser().getUserId() == user.getUserId())
                .map((s) -> s.getPurchasePrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        report.setTotalVehicles(filtered.stream()
                .filter((s) -> s.getUser().getUserId() == user.getUserId())
                .collect(Collectors.toList()).size());
        
        return report;
    }
    
    private InventoryReport generateInventoryReportForModel(Model model){
        InventoryReport report = new InventoryReport();
        
        List<Vehicle> modelStock = vehicleDao.getAllVehicles().stream()
                .filter((v) -> v.getModel().equals(model))
                .collect(Collectors.toList());
        
        report.setCount(modelStock.size());
        report.setStockValue(modelStock.stream()
            .map((v) -> v.getMSRP())
            .reduce(BigDecimal.ZERO, BigDecimal::add));
        
        return report;
    }
    
    private void validatePassword(PasswordChangeContext pass) throws PasswordsDoNotMatchException {
        if(!pass.getPassword().equals(pass.getConfirmation())){
            throw new PasswordsDoNotMatchException("Passwords do not much. Cannot save new password.");
        }
    }
    
    private void validateVehicleRequest(VehicleRequestContext request) throws InvalidVehicleException {
        // Validate Date
        LocalDateTime date = request.getYear();
        LocalDateTime minDate = LocalDateTime.parse("2000-01-01T00:00:00");
        LocalDateTime maxDate = LocalDateTime.now().plusYears(1);
        
        if(date.compareTo(minDate) < 0 || date.compareTo(maxDate) > 0){
            throw new InvalidVehicleException("Vehicle is not in valid date range. Cannot add or edit vehicle.");
        }
        
        // Validate Condition
        if(((request.getConditionId() == 1) && (request.getMileage() >= 1000)) 
                || ((request.getConditionId() == 2) && (request.getMileage() < 1000))){
            throw new InvalidVehicleException("Invalid condition for mileage. Cannot add or edit vehicle.");
        }
        
        // Validate Sale Price and MSRP
        if((request.getSalePrice().signum() == -1) 
                || (request.getMSRP().signum() == -1)
                || (request.getSalePrice().compareTo(request.getMSRP()) >= 0)){
            throw new InvalidVehicleException("Sale price must be less than MSRP. Cannot add or edit vehicle.");
        }

    }
    
    private Make getMakeFromRequest(VehicleRequestContext request){
        return makeDao.getMakeById(request.getMakeId());
    }
    
    private Model getModelFromRequest(VehicleRequestContext request){
        Model model = modelDao.getModelById(request.getModelId());
        if(model.getMake().getMakeId() == request.getMakeId()){
            return model;
        } else {
            return null;
        }
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

}