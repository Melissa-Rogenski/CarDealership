/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Color;
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
public class CarDealershipColorDaoDB implements CarDealershipColorDao {

    @Autowired
    JdbcTemplate jdbc;
    public static final class ColorMapper implements RowMapper<Color> {

        @Override
        public Color mapRow(ResultSet rs, int index) throws SQLException {
            Color c = new Color();
            c.setColorId(rs.getInt("id"));
            c.setColor(rs.getString("color"));
            return c;
        }
    }
    
    @Override
    public List<Color> getAllColors() {
        final String SELECT_ALL_COLORS = "SELECT * FROM color";
        return jdbc.query(SELECT_ALL_COLORS, new ColorMapper());
    }

    @Override
    public Color getColorById(int id) {
        try {
            final String SELECT_COLOR_BY_ID = "SELECT * FROM color WHERE id = ?";
            return jdbc.queryForObject(SELECT_COLOR_BY_ID, new ColorMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }    
    }

    @Override
    @Transactional
    public Color addColor(Color color) {
        final String INSERT_COLOR = "INSERT INTO color(color) "
                + "VALUES(?)";
        jdbc.update(INSERT_COLOR, 
                color.getColor());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        color.setColorId(newId);
        return color;
    }

    @Override
    public boolean updateColor(Color color) {
        final String UPDATE_COLOR = "UPDATE color SET color = ?"
                + "WHERE id = ?";
        jdbc.update(UPDATE_COLOR,
                color.getColor(),
                color.getColorId());
        return true;
    }

    @Override
    @Transactional
    public void deleteColorById(int id) {
        final String DELETE_VEHICLE_COLOR = "DELETE FROM vehicle "
                + "WHERE colorId = ?";
        jdbc.update(DELETE_VEHICLE_COLOR, id);
        
        final String DELETE_COLOR = "DELETE FROM color WHERE id = ?";
        jdbc.update(DELETE_COLOR, id);
    }
    
}
