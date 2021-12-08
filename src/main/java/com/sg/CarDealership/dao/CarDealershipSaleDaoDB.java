/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dao.CarDealershipPurchaseTypeDaoDB.PurchaseTypeMapper;
import com.sg.CarDealership.dao.CarDealershipStateDaoDB.StateMapper;
import com.sg.CarDealership.dao.CarDealershipUserDaoDB.UserMapper;
import com.sg.CarDealership.dao.CarDealershipVehicleDaoDB.VehicleMapper;
import com.sg.CarDealership.model.Model;
import com.sg.CarDealership.model.PurchaseType;
import com.sg.CarDealership.model.Sale;
import com.sg.CarDealership.model.State;
import com.sg.CarDealership.model.User;
import com.sg.CarDealership.model.Vehicle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mroge
 */
@Repository
public class CarDealershipSaleDaoDB implements CarDealershipSaleDao {

    @Autowired
    JdbcTemplate jdbc;
    public static final class SaleMapper implements RowMapper<Sale> {

        @Override
        public Sale mapRow(ResultSet rs, int index) throws SQLException {
            Sale s = new Sale();
            s.setSaleId(rs.getInt("id"));
            s.setBuyerName(rs.getString("buyerName"));
            s.setPurchasePrice(rs.getBigDecimal("purchasePrice"));
            s.setPurchaseDate(rs.getDate("purchaseDate").toLocalDate());
            s.setEmail(rs.getString("email"));
            s.setPhone(rs.getString("phone"));
            s.setStreet1(rs.getString("street1"));
            s.setStreet2(rs.getString("street2"));
            s.setCity(rs.getString("city"));
            s.setZipcode(rs.getString("zipcode"));
            return s;
        }
    }
    
    private State getStateForSale(Sale sale) {
        final String SELECT_STATE_FOR_SALE = "SELECT st.* FROM state st "
                + "JOIN sale sa ON st.id = sa.stateId WHERE sa.id = ?";
        return jdbc.queryForObject(SELECT_STATE_FOR_SALE, new StateMapper(), 
                sale.getSaleId());
    }
    
    private PurchaseType getPurchaseTypeForSale(Sale sale) {
        final String SELECT_PURCHASETYPE_FOR_SALE = "SELECT pt.* FROM purchaseType pt "
                + "JOIN sale sa ON pt.id = sa.purchaseTypeId WHERE sa.id = ?";
        return jdbc.queryForObject(SELECT_PURCHASETYPE_FOR_SALE, new PurchaseTypeMapper(), 
                sale.getSaleId());
    }
    
    private Vehicle getVehicleForSale(Sale sale) {
        final String SELECT_VEHICLE_FOR_SALE = "SELECT v.* FROM vehicle v "
                + "JOIN sale sa ON v.id = sa.vehicleId WHERE sa.id = ?";
        return jdbc.queryForObject(SELECT_VEHICLE_FOR_SALE, new VehicleMapper(), 
                sale.getSaleId());
    }
    
    private User getUserForSale(Sale sale) {
        final String SELECT_USER_FOR_SALE = "SELECT u.* FROM user u "
                + "JOIN sale sa ON u.id = sa.userId WHERE sa.id = ?";
        return jdbc.queryForObject(SELECT_USER_FOR_SALE, new UserMapper(), 
                sale.getSaleId());
    }
    
    private void addVehicleToSales(List<Sale> sales) {
        for(Sale sale : sales) {
            sale.setVehicle(getVehicleForSale(sale));
        }
    }
    
    private void addUserToSales(List<Sale> sales) {
        for(Sale sale : sales) {
            sale.setUser(getUserForSale(sale));
        }
    }
    
    private void addPurchaseTypeToSales(List<Sale> sales) {
        for(Sale sale : sales) {
            sale.setPurchaseType(getPurchaseTypeForSale(sale));
        }
    }
    
    @Override
    public List<Sale> getAllSales() {
        final String SELECT_ALL_SALES = "SELECT * FROM sale";
        List<Sale> sales = jdbc.query(SELECT_ALL_SALES, new SaleMapper());
        
        addPurchaseTypeToSales(sales);
        addVehicleToSales(sales);
        addUserToSales(sales);
        
        return sales;
    }

    @Override
    public Sale getSaleById(int id) {
        try {
            final String SELECT_SALE_BY_ID = "SELECT * FROM sale WHERE id = ?";
            Sale sale = jdbc.queryForObject(SELECT_SALE_BY_ID, 
                    new SaleMapper(), id);
            sale.setPurchaseType(getPurchaseTypeForSale(sale));
            sale.setVehicle(getVehicleForSale(sale));
            sale.setUser(getUserForSale(sale));
            return sale;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Sale addSale(Sale sale) {
        final String INSERT_SALE = "INSERT INTO sale(buyerName, purchasePrice, purchaseDate, email, phone, street1, street2, city, zipcode, state, purchaseTypeId, vehicleId, userId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_SALE,
                sale.getBuyerName(),
                sale.getPurchasePrice(),
                sale.getPurchaseDate(),
                sale.getEmail(),
                sale.getPhone(),
                sale.getStreet1(),
                sale.getStreet2(),
                sale.getCity(),
                sale.getZipcode(),
                sale.getPurchaseType().getPurchaseTypeId(),
                sale.getVehicle().getVehicleId(),
                sale.getUser().getUserId());
               
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sale.setSaleId(newId);
     
        return sale;
    }

    @Override
    public boolean updateSale(Sale sale) {
        final String UPDATE_SALE = "UPDATE sale "
                + "SET buyerName = ?, purchasePrice = ?, purchaseDate = ?, email = ?, phone = ?, street1 = ?, street2 = ?, city = ?, zipcode = ?, state = ?, purchaseTypeId = ?, vehicleId = ?, userId = ? WHERE id = ?";
        jdbc.update(UPDATE_SALE,
                sale.getBuyerName(),
                sale.getPurchasePrice(),
                sale.getPurchaseDate(),
                sale.getEmail(),
                sale.getPhone(),
                sale.getStreet1(),
                sale.getStreet2(),
                sale.getCity(),
                sale.getZipcode(),
                sale.getPurchaseType().getPurchaseTypeId(),
                sale.getVehicle().getVehicleId(),
                sale.getUser().getUserId());
        return true;
    }

    @Override
    @Transactional
    public void deleteSaleById(int id) {
        final String DELETE_SALE = "DELETE FROM sale WHERE id = ?";
        jdbc.update(DELETE_SALE, id);
    }
    
}
