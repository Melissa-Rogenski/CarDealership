/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Trans;
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
public class CarDealershipTransDaoDB implements CarDealershipTransDao {

    @Autowired
    JdbcTemplate jdbc;
    public static final class TransMapper implements RowMapper<Trans> {

        @Override
        public Trans mapRow(ResultSet rs, int index) throws SQLException {
            Trans t = new Trans();
            t.setTransId(rs.getInt("id"));
            t.setTrans(rs.getString("trans"));
            return t;
        }
    }
    
    @Override
    public List<Trans> getAllTrans() {
        final String SELECT_ALL_TRANS = "SELECT * FROM trans";
        return jdbc.query(SELECT_ALL_TRANS, new TransMapper());
    }

    @Override
    public Trans getTransById(int id) {
        try {
            final String SELECT_TRANS_BY_ID = "SELECT * FROM trans WHERE id = ?";
            return jdbc.queryForObject(SELECT_TRANS_BY_ID, new TransMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Trans addTrans(Trans trans) {
        final String INSERT_TRANS = "INSERT INTO trans(trans) "
                + "VALUES(?)";
        jdbc.update(INSERT_TRANS, 
                trans.getTrans());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        trans.setTransId(newId);
        return trans;
    }

    @Override
    public boolean updateTrans(Trans trans) {
        final String UPDATE_TRANS = "UPDATE trans SET trans = ?"
                + "WHERE id = ?";
        jdbc.update(UPDATE_TRANS,
                trans.getTrans(),
                trans.getTransId());
        return true;
    }

    @Override
    @Transactional
    public void deleteTransById(int id) {
        final String DELETE_VEHICLE_TRANS = "DELETE FROM vehicle "
                + "WHERE transId = ?";
        jdbc.update(DELETE_VEHICLE_TRANS, id);
        
        final String DELETE_TRANS = "DELETE FROM trans WHERE id = ?";
        jdbc.update(DELETE_TRANS, id);
    }
    
}
