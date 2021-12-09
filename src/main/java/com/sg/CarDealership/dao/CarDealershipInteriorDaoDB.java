/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Interior;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
@Profile("db")
public class CarDealershipInteriorDaoDB implements CarDealershipInteriorDao {

    @Autowired
    JdbcTemplate jdbc;
    public static final class InteriorMapper implements RowMapper<Interior> {

        @Override
        public Interior mapRow(ResultSet rs, int index) throws SQLException {
            Interior i = new Interior();
            i.setInteriorId(rs.getInt("id"));
            i.setInterior(rs.getString("interior"));
            return i;
        }
    }
    
    @Override
    public List<Interior> getAllInteriors() {
        final String SELECT_ALL_INTERIORS = "SELECT * FROM `interior`";
        return jdbc.query(SELECT_ALL_INTERIORS, new InteriorMapper());
    }

    @Override
    public Interior getInteriorById(int id) {
        try {
            final String SELECT_INTERIOR_BY_ID = "SELECT * FROM `interior` WHERE id = ?";
            return jdbc.queryForObject(SELECT_INTERIOR_BY_ID, new InteriorMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Interior addInterior(Interior interior) {
        final String INSERT_INTERIOR = "INSERT INTO `interior`(interior) "
                + "VALUES(?)";
        jdbc.update(INSERT_INTERIOR, 
                interior.getInterior());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        interior.setInteriorId(newId);
        return interior;
    }

    @Override
    public boolean updateInterior(Interior interior) {
        final String UPDATE_INTERIOR = "UPDATE `interior` SET interior = ?"
                + "WHERE id = ?";
        jdbc.update(UPDATE_INTERIOR,
                interior.getInterior(),
                interior.getInteriorId());
        return true;
    }

    @Override
    @Transactional
    public void deleteInteriorById(int id) {
        final String DELETE_VEHICLE_INTERIOR = "DELETE FROM `vehicle` "
                + "WHERE interiorId = ?";
        jdbc.update(DELETE_VEHICLE_INTERIOR, id);
        
        final String DELETE_INTERIOR = "DELETE FROM `interior` WHERE id = ?";
        jdbc.update(DELETE_INTERIOR, id);
    }
    
}
