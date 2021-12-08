/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Condition;
import com.sg.CarDealership.model.Special;
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
public class CarDealershipSpecialDaoDB implements CarDealershipSpecialDao {

    @Autowired
    JdbcTemplate jdbc;
    public static final class SpecialMapper implements RowMapper<Special> {

        @Override
        public Special mapRow(ResultSet rs, int index) throws SQLException {
            Special s = new Special();
            s.setSpecialId(rs.getInt("id"));
            s.setTitle(rs.getString("title"));
            s.setDescription(rs.getString("description"));
            return s;
        }
    }
    
    @Override
    public List<Special> getAllSpecials() {
        final String SELECT_ALL_SPECIALS = "SELECT * FROM special";
        return jdbc.query(SELECT_ALL_SPECIALS, new CarDealershipSpecialDaoDB.SpecialMapper());
    }

    @Override
    public Special getSpecialById(int id) {
        try {
            final String SELECT_SPECIAL_BY_ID = "SELECT * FROM special WHERE id = ?";
            return jdbc.queryForObject(SELECT_SPECIAL_BY_ID, new CarDealershipSpecialDaoDB.SpecialMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Special addSpecial(Special special) {
        final String INSERT_SPECIAL = "INSERT INTO special(title, description) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_SPECIAL, 
                special.getTitle(),
                special.getDescription());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        special.setSpecialId(newId);
        return special;
    }

    @Override
    public boolean updateSpecial(Special special) {
        final String UPDATE_SPECIAL = "UPDATE special SET title = ?, description = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_SPECIAL,
                special.getTitle(),
                special.getDescription(),
                special.getSpecialId());
        return true;
    }

    @Override
    @Transactional
    public void deleteSpecialById(int id) {
        final String DELETE_SPECIAL = "DELETE FROM special WHERE id = ?";
        jdbc.update(DELETE_SPECIAL, id);
    }
    
}
