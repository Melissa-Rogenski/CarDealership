/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.BodyStyle;
import com.sg.CarDealership.model.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mroge
 */
@Repository
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Color getColorById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Color addColor(Color color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateColor(Color color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteColorById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
