/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dao.CarDealershipUserDaoDB.UserMapper;
import com.sg.CarDealership.model.Make;
import com.sg.CarDealership.model.User;
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
public class CarDealershipMakeDaoDB implements CarDealershipMakeDao {

    @Autowired
    JdbcTemplate jdbc;
    public static final class MakeMapper implements RowMapper<Make> {

        @Override
        public Make mapRow(ResultSet rs, int index) throws SQLException {
            Make m = new Make();
            m.setMakeId(rs.getInt("id"));
            m.setMake(rs.getString("make"));
            m.setDateAdded(rs.getTimestamp("dateAdded").toLocalDateTime());
            return m;
        }
    }
    
    private User getUserForMake(Make make) {
        final String SELECT_USER_FOR_MAKE = "SELECT u.* FROM user u "
                + "JOIN make m ON u.id = m.makeId WHERE m.id = ?";
        return jdbc.queryForObject(SELECT_USER_FOR_MAKE, new UserMapper(), 
                make.getMakeId());
    }
    
    @Override
    public List<Make> getAllMakes() {
        final String SELECT_ALL_MAKES = "SELECT * FROM make";
        List<Make> makes = jdbc.query(SELECT_ALL_MAKES, new MakeMapper());
        
        addUserToMakes(makes);
        
        return makes;
    }
    
    private void addUserToMakes(List<Make> makes) {
        for(Make make : makes) {
            make.setUser(getUserForMake(make));
        }
    }

    @Override
    public Make getMakeById(int id) {
        try {
            final String SELECT_MAKE_BY_ID = "SELECT * FROM make WHERE id = ?";
            Make make = jdbc.queryForObject(SELECT_MAKE_BY_ID, 
                    new MakeMapper(), id);
            make.setUser(getUserForMake(make));
            return make;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Make addMake(Make make) {
        final String INSERT_MAKE = "INSERT INTO make(make, dateAdded, userId) VALUES(?,?,?)";
        jdbc.update(INSERT_MAKE,
                make.getMake(),
                Timestamp.valueOf(make.getDateAdded()),
                make.getUser().getUserId());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        make.setMakeId(newId);
     
        return make;
    }

    @Override
    public boolean updateMake(Make make) {
        final String UPDATE_MAKE = "UPDATE make "
                + "SET make = ?, dateAdded = ?, userId = ? WHERE id = ?";
        jdbc.update(UPDATE_MAKE,
                make.getMake(),
                Timestamp.valueOf(make.getDateAdded()),
                make.getUser().getUserId(),
                make.getMakeId());
        return true;
    }

    @Override
    @Transactional
    public void deleteMakeById(int id) {
        final String DELETE_VEHICLE_MAKE = "DELETE FROM vehicle "
                + "WHERE makeId = ?";
        jdbc.update(DELETE_VEHICLE_MAKE, id);
        
        final String DELETE_MODEL_MAKE = "DELETE FROM model "
                + "WHERE makeId = ?";
        jdbc.update(DELETE_MODEL_MAKE, id);

        final String DELETE_MAKE = "DELETE FROM make WHERE id = ?";
        jdbc.update(DELETE_MAKE, id);
    }
    
}
