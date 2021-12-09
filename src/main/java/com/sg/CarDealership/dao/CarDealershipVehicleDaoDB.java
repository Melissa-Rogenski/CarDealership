/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dao.CarDealershipBodyStyleDaoDB.BodyStyleMapper;
import com.sg.CarDealership.dao.CarDealershipColorDaoDB.ColorMapper;
import com.sg.CarDealership.dao.CarDealershipConditionDaoDB.ConditionMapper;
import com.sg.CarDealership.dao.CarDealershipInteriorDaoDB.InteriorMapper;
import com.sg.CarDealership.dao.CarDealershipMakeDaoDB.MakeMapper;
import com.sg.CarDealership.dao.CarDealershipModelDaoDB.ModelMapper;
import com.sg.CarDealership.dao.CarDealershipTransDaoDB.TransMapper;
import com.sg.CarDealership.model.BodyStyle;
import com.sg.CarDealership.model.Color;
import com.sg.CarDealership.model.Condition;
import com.sg.CarDealership.model.Interior;
import com.sg.CarDealership.model.Make;
import com.sg.CarDealership.model.Model;
import com.sg.CarDealership.model.Role;
import com.sg.CarDealership.model.Trans;
import com.sg.CarDealership.model.User;
import com.sg.CarDealership.model.Vehicle;
import com.sg.CarDealership.service.VehicleQueryContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mroge
 */
@Repository
@Profile("db")
public class CarDealershipVehicleDaoDB implements CarDealershipVehicleDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    
    public CarDealershipVehicleDaoDB(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Vehicle> getAllVehicles() {
        final String SELECT_ALL_VEHICLES = "SELECT * FROM vehicle";
        List<Vehicle> vehicles = jdbcTemplate.query(SELECT_ALL_VEHICLES, new VehicleMapper());
        
        vehicles.stream()
                .forEach((v) -> addFieldsToVehicle(v));
        
        return vehicles;
    }
    
    @Override
    public List<Vehicle> getAllVehicles(VehicleQueryContext query) {
        List<Vehicle> vehicles = new ArrayList<>();
        if(!query.getSearchBar().isEmpty()){
            final String SELECT_ALL_VEHICLES_BY_QUERY = "SELECT v.* FROM vehicle v "
                    + "JOIN make m ON (m.make LIKE ? AND m.Id = v.makeId) "
                    + "JOIN model mo ON (mo.model LIKE ? AND mo.Id = v.modelId) "
                    + "WHERE (year BETWEEN ? AND ? ) "
                    + "AND (msrp BETWEEN ? AND ? ) "
                    + "AND (conditionId = ? )";
            vehicles = jdbcTemplate.query(SELECT_ALL_VEHICLES_BY_QUERY, new VehicleMapper(),
                    "%"+query.getSearchBar()+"%", "%"+query.getSearchBar()+"%", query.getMinYear(), query.getMaxYear(), 
                    query.getMinPrice(), query.getMaxPrice(), query.getConditionId());
        } else if(query.getConditionId()==0){
            final String SELECT_ALL_VEHICLES_BY_QUERY = "SELECT v.* FROM vehicle v "
                    + "WHERE (year BETWEEN ? AND ? ) "
                    + "AND (msrp BETWEEN ? AND ? ) ";
            vehicles = jdbcTemplate.query(SELECT_ALL_VEHICLES_BY_QUERY, new VehicleMapper(),
                    query.getMinYear(), query.getMaxYear(), 
                    query.getMinPrice(), query.getMaxPrice());

        } else {
            final String SELECT_ALL_VEHICLES_BY_QUERY = "SELECT v.* FROM vehicle v "
                    + "WHERE (year BETWEEN ? AND ? ) "
                    + "AND (msrp BETWEEN ? AND ? ) "
                    + "AND (conditionId = ? )";
            vehicles = jdbcTemplate.query(SELECT_ALL_VEHICLES_BY_QUERY, new VehicleMapper(),
                    query.getMinYear(), query.getMaxYear(), 
                    query.getMinPrice(), query.getMaxPrice(), query.getConditionId());
        }
        
        vehicles.stream()
                .forEach((v) -> addFieldsToVehicle(v));
        
        return vehicles;
    }


    @Override
    public Vehicle getVehicleById(int id) {
        final String SELECT_VEHICLE_BY_ID = "SELECT * FROM vehicle WHERE id = ?;";
        Vehicle vehicle = jdbcTemplate.queryForObject(SELECT_VEHICLE_BY_ID, new VehicleMapper(), id);
        
        addFieldsToVehicle(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        final String sql = "INSERT INTO vehicle(year, salePrice, msrp, mileage, vin, description, "
                + "picture, purchased, featured, makeId, modelId, conditionId, bodyStyleId, "
                + "interiorId, transId, colorId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setObject(1, vehicle.getYear());
            statement.setBigDecimal(2, vehicle.getSalePrice());
            statement.setBigDecimal(3, vehicle.getMSRP());
            statement.setInt(4, vehicle.getMileage());
            statement.setString(5, vehicle.getVin());
            statement.setString(6, vehicle.getDescription());
            statement.setString(7, vehicle.getPicturePath());
            statement.setBoolean(8, vehicle.isPurchased());
            statement.setBoolean(9, vehicle.isFeatured());
            statement.setInt(10, vehicle.getMake().getMakeId());
            statement.setInt(11, vehicle.getModel().getModelId());
            statement.setInt(12, vehicle.getCondition().getConditionId());
            statement.setInt(13, vehicle.getBodyStyle().getBodyStyleId());
            statement.setInt(14, vehicle.getInterior().getInteriorId());
            statement.setInt(15, vehicle.getTrans().getTransId());
            statement.setInt(16, vehicle.getColor().getColorId());
            
            return statement;

        }, keyHolder);

        vehicle.setVehicleId(keyHolder.getKey().intValue());

        return vehicle;
    }

    @Override
    public void deleteVehicleById(int id) {
        final String DELETE_SALE_VEHICLE = "DELETE FROM sale "
                + "WHERE vehicleId = ?";
        jdbcTemplate.update(DELETE_SALE_VEHICLE, id);

        final String DELETE_VEHICLE = "DELETE FROM vehicle WHERE id = ?";
        jdbcTemplate.update(DELETE_VEHICLE, id);
    }

    @Override
    public boolean updateVehicle(Vehicle vehicle) {
        final String sql = "UPDATE vehicle SET "
                + "year = ?, salePrice = ?, msrp = ?, mileage = ?, vin = ?, description = ?, "
                + "picture = ?, purchased = ?, featured = ?, makeId = ?, modelId = ?, "
                + "conditionId = ?, bodyStyleId = ?, interiorId = ?, transId = ?, colorId = ? "
                + "WHERE id = ?;";
        
        return jdbcTemplate.update(sql,
                vehicle.getYear(), vehicle.getSalePrice(), vehicle.getMSRP(), vehicle.getMileage(), vehicle.getVin(),
                vehicle.getDescription(), vehicle.getPicturePath(), vehicle.isPurchased(), vehicle.isFeatured(),
                vehicle.getMake().getMakeId(), vehicle.getModel().getModelId(), vehicle.getCondition().getConditionId(),
                vehicle.getBodyStyle().getBodyStyleId(), vehicle.getInterior().getInteriorId(), vehicle.getTrans().getTransId(),
                vehicle.getColor().getColorId(), vehicle.getVehicleId()) > 0;
        
    }
    
    // Public mapper
    public static final class VehicleMapper implements RowMapper<Vehicle>{

        @Override
        public Vehicle mapRow(ResultSet rs, int index) throws SQLException {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleId(rs.getInt("id"));
            vehicle.setYear(rs.getTimestamp("year").toLocalDateTime());
            vehicle.setSalePrice(rs.getBigDecimal("salePrice"));
            vehicle.setMSRP(rs.getBigDecimal("msrp"));
            vehicle.setVin(rs.getString("vin"));
            vehicle.setDescription(rs.getString("description"));
            vehicle.setPicturePath(rs.getString("picture"));
            vehicle.setPurchased(rs.getBoolean("purchased"));
            vehicle.setFeatured(rs.getBoolean("featured"));
  
            return vehicle;
        }
    
    }
    
    // Private helper methods
    
    private Make getMakeForVehicle(Vehicle vehicle){
        final String SELECT_MAKE_FOR_VEHICLE = "SELECT m.* FROM make m "
                + "JOIN vehicle v ON m.id = v.makeId WHERE v.id = ?";
        Make m =  jdbcTemplate.queryForObject(SELECT_MAKE_FOR_VEHICLE, new MakeMapper(), 
                vehicle.getVehicleId());
        m.setUser(getUserForMake(m));
        return m;
    }
    
    private Model getModelForVehicle(Vehicle vehicle){
        final String SELECT_MODEL_FOR_VEHICLE = "SELECT m.* FROM model m "
                + "JOIN vehicle v ON m.id = v.modelId WHERE v.id = ?";
        Model m = jdbcTemplate.queryForObject(SELECT_MODEL_FOR_VEHICLE, new ModelMapper(), 
                vehicle.getVehicleId());
        m.setMake(getMakeForModel(m));
        m.setUser(getUserForModel(m));
        return m;
    }
    
    private User getUserForModel(Model model) {
        final String SELECT_USER_FOR_MODEL = "SELECT u.* FROM `user` u "
                + "JOIN model m ON u.id = m.userId WHERE m.id = ?";
        User u = jdbcTemplate.queryForObject(SELECT_USER_FOR_MODEL, new CarDealershipUserDaoDB.UserMapper(), 
                model.getModelId());
        u.setRole(getRoleForUser(u));
        return u;
    }
    
    private Make getMakeForModel(Model model) {
        final String SELECT_MAKE_FOR_MODEL = "SELECT ma.* FROM make ma "
                + "JOIN model mo ON ma.id = mo.makeId WHERE mo.id = ?";
        Make m =  jdbcTemplate.queryForObject(SELECT_MAKE_FOR_MODEL, new MakeMapper(), 
                model.getModelId());
        m.setUser(getUserForMake(m));
        return m;
    }
    
    private User getUserForMake(Make make) {
        final String SELECT_USER_FOR_MAKE = "SELECT u.* FROM `user` u "
                + "JOIN make m ON u.id = m.userId WHERE m.id = ?";
        User u = jdbcTemplate.queryForObject(SELECT_USER_FOR_MAKE, new CarDealershipUserDaoDB.UserMapper(), 
                make.getMakeId());
        u.setRole(getRoleForUser(u));
        return u;
    }
    
    private Role getRoleForUser(User user) {
        final String SELECT_ROLE_FOR_USER = "SELECT r.* FROM `role` r "
                + "JOIN `user` u ON r.id = u.roleId WHERE u.id = ?";
        return jdbcTemplate.queryForObject(SELECT_ROLE_FOR_USER, new CarDealershipRoleDaoDB.RoleMapper(), 
                user.getUserId());
    }
    
    private Condition getConditionForVehicle(Vehicle vehicle){
        final String SELECT_CONDITION_FOR_VEHICLE = "SELECT c.* FROM `condition` c "
                + "JOIN vehicle v ON c.id = v.conditionId WHERE v.id = ?";
        return jdbcTemplate.queryForObject(SELECT_CONDITION_FOR_VEHICLE, new ConditionMapper(), 
                vehicle.getVehicleId());
    }
    
    private BodyStyle getBodyStyleForVehicle(Vehicle vehicle){
        final String SELECT_BODYSTYLE_FOR_VEHICLE = "SELECT b.* FROM bodyStyle b "
                + "JOIN vehicle v ON b.id = v.bodyStyleId WHERE v.id = ?";
        return jdbcTemplate.queryForObject(SELECT_BODYSTYLE_FOR_VEHICLE, new BodyStyleMapper(), 
                vehicle.getVehicleId());
    }
    
    private Interior getInteriorForVehicle(Vehicle vehicle){
        final String SELECT_INTERIOR_FOR_VEHICLE = "SELECT i.* FROM interior i "
                + "JOIN vehicle v ON i.id = v.interiorId WHERE v.id = ?";
        return jdbcTemplate.queryForObject(SELECT_INTERIOR_FOR_VEHICLE, new InteriorMapper(), 
                vehicle.getVehicleId());
    }
    
    private Trans getTransForVehicle(Vehicle vehicle){
        final String SELECT_TRANS_FOR_VEHICLE = "SELECT t.* FROM trans t "
                + "JOIN vehicle v ON t.id = v.transId WHERE v.id = ?";
        return jdbcTemplate.queryForObject(SELECT_TRANS_FOR_VEHICLE, new TransMapper(), 
                vehicle.getVehicleId());
    }
    
    private Color getColorForVehicle(Vehicle vehicle){
        final String SELECT_COLOR_FOR_VEHICLE = "SELECT c.* FROM color c "
                + "JOIN vehicle v ON c.id = v.colorId WHERE v.id = ?";
        return jdbcTemplate.queryForObject(SELECT_COLOR_FOR_VEHICLE, new ColorMapper(), 
                vehicle.getVehicleId());
    }
    
    private void addFieldsToVehicle(Vehicle v){
        v.setMake(getMakeForVehicle(v));
        v.setModel(getModelForVehicle(v));
        v.setCondition(getConditionForVehicle(v));
        v.setBodyStyle(getBodyStyleForVehicle(v));
        v.setInterior(getInteriorForVehicle(v));
        v.setTrans(getTransForVehicle(v));
        v.setColor(getColorForVehicle(v));
    }

    
}
