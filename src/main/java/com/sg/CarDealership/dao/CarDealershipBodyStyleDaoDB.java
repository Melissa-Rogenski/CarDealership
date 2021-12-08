/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.BodyStyle;
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
public class CarDealershipBodyStyleDaoDB implements CarDealershipBodyStyleDao {

    @Autowired
    JdbcTemplate jdbc;
    public static final class BodyStyleMapper implements RowMapper<BodyStyle> {

        @Override
        public BodyStyle mapRow(ResultSet rs, int index) throws SQLException {
            BodyStyle bs = new BodyStyle();
            bs.setBodyStyleId(rs.getInt("id"));
            bs.setBodyStyle(rs.getString("bodyStyle"));
            return bs;
        }
    }
    
    @Override
    public List<BodyStyle> getAllBodyStyles() {
        final String SELECT_ALL_BODYSTYLES = "SELECT * FROM bodystyle";
        return jdbc.query(SELECT_ALL_BODYSTYLES, new BodyStyleMapper());
    }

    @Override
    public BodyStyle getBodyStyleById(int id) {
        try {
            final String SELECT_BODYSTYLE_BY_ID = "SELECT * FROM bodystyle WHERE id = ?";
            return jdbc.queryForObject(SELECT_BODYSTYLE_BY_ID, new BodyStyleMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public BodyStyle addBodyStyle(BodyStyle bodyStyle) {
        final String INSERT_BODYSTYLE = "INSERT INTO bodystyle(bodyStyle) "
                + "VALUES(?)";
        jdbc.update(INSERT_BODYSTYLE, 
                bodyStyle.getBodyStyle());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        bodyStyle.setBodyStyleId(newId);
        return bodyStyle;
    }

    @Override
    public boolean updateBodyStyle(BodyStyle bodyStyle) {
        final String UPDATE_BODYSTYLE = "UPDATE bodystyle SET bodyStyle = ?"
                + "WHERE id = ?";
        jdbc.update(UPDATE_BODYSTYLE,
                bodyStyle.getBodyStyle(),
                bodyStyle.getBodyStyleId());
        return true;
    }

    @Override
    @Transactional
    public void deleteBodyStyleById(int id) {
        final String DELETE_VEHICLE_BODYSTYLE = "DELETE FROM vehicle "
                + "WHERE bodystyleId = ?";
        jdbc.update(DELETE_VEHICLE_BODYSTYLE, id);
        
        final String DELETE_BODYSTYLE = "DELETE FROM bodystyle WHERE id = ?";
        jdbc.update(DELETE_BODYSTYLE, id);
    }
    
}
