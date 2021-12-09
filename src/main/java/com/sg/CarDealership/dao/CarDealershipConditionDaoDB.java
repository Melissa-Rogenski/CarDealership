/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Condition;
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
public class CarDealershipConditionDaoDB implements CarDealershipConditionDao {

    @Autowired
    JdbcTemplate jdbc;
    public static final class ConditionMapper implements RowMapper<Condition> {

        @Override
        public Condition mapRow(ResultSet rs, int index) throws SQLException {
            Condition c = new Condition();
            c.setConditionId(rs.getInt("id"));
            c.setCondition(rs.getString("condition"));
            return c;
        }
    }
    
    @Override
    public List<Condition> getAllConditions() {
        final String SELECT_ALL_CONDITIONS = "SELECT * FROM `condition`";
        return jdbc.query(SELECT_ALL_CONDITIONS, new ConditionMapper());
    }

    @Override
    public Condition getConditionById(int id) {
        try {
            final String SELECT_CONDITION_BY_ID = "SELECT * FROM `condition` WHERE id = ? ";
            return jdbc.queryForObject(SELECT_CONDITION_BY_ID, new ConditionMapper(), id);
        } catch(DataAccessException ex) {
            System.out.println("Error connecting to database");
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public Condition addCondition(Condition condition) {
        final String INSERT_CONDITION = "INSERT INTO `condition`(`condition`) "
                + "VALUES(?)";
        jdbc.update(INSERT_CONDITION, 
                condition.getCondition());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        condition.setConditionId(newId);
        return condition;
    }

    @Override
    public boolean updateCondition(Condition condition) {
        final String UPDATE_CONDITION = "UPDATE `condition` SET `condition` = ?"
                + "WHERE id = ?";
        jdbc.update(UPDATE_CONDITION,
                condition.getCondition(),
                condition.getConditionId());
        return true;
    }

    @Override
    @Transactional
    public void deleteConditionById(int id) {
        final String DELETE_VEHICLE_CONDITION = "DELETE FROM `vehicle` "
                + "WHERE conditionId = ?";
        jdbc.update(DELETE_VEHICLE_CONDITION, id);
        
        final String DELETE_CONDITION = "DELETE FROM `condition` WHERE id = ?";
        jdbc.update(DELETE_CONDITION, id);
    }
    
}
