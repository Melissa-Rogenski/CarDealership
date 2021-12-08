/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.PurchaseType;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class CarDealershipPurchaseTypeDaoDB implements CarDealershipPurchaseTypeDao {

    @Autowired
    JdbcTemplate jdbc;
    public static final class PurchaseTypeMapper implements RowMapper<PurchaseType> {

        @Override
        public PurchaseType mapRow(ResultSet rs, int index) throws SQLException {
            PurchaseType p = new PurchaseType();
            p.setPurchaseTypeId(rs.getInt("id"));
            p.setPurchaseType(rs.getString("purchaseType"));
            return p;
        }
    }
    
    @Override
    public List<PurchaseType> getAllPurchaseTypes() {
        final String SELECT_ALL_PURCHASETYPES = "SELECT * FROM purchaseType";
        return jdbc.query(SELECT_ALL_PURCHASETYPES, new PurchaseTypeMapper());
    }

    @Override
    public PurchaseType getPurchaseTypeById(int id) {
        try {
            final String SELECT_PURCHASETYPE_BY_ID = "SELECT * FROM purchaseType WHERE id = ?";
            return jdbc.queryForObject(SELECT_PURCHASETYPE_BY_ID, new PurchaseTypeMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public PurchaseType addPurchaseType(PurchaseType purchaseType) {
        final String INSERT_PURCHASETYPE = "INSERT INTO purchaseType(purchaseType) "
                + "VALUES(?)";
        jdbc.update(INSERT_PURCHASETYPE, 
                purchaseType.getPurchaseType());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        purchaseType.setPurchaseTypeId(newId);
        return purchaseType;
    }

    @Override
    public boolean updatePurchaseType(PurchaseType purchaseType) {
        final String UPDATE_PURCHASETYPE = "UPDATE purchaseType SET purchaseType = ?"
                + "WHERE id = ?";
        jdbc.update(UPDATE_PURCHASETYPE,
                purchaseType.getPurchaseType(),
                purchaseType.getPurchaseTypeId());
        return true;
    }

    @Override
    @Transactional
    public void deletePurchaseTypeById(int id) {
        final String DELETE_SALE_PURCHASETYPE = "DELETE FROM sale "
                + "WHERE purchaseTypeId = ?";
        jdbc.update(DELETE_SALE_PURCHASETYPE, id);
        
        final String DELETE_PURCHASETYPE = "DELETE FROM purchaseType WHERE id = ?";
        jdbc.update(DELETE_PURCHASETYPE, id);
    }
    
}
