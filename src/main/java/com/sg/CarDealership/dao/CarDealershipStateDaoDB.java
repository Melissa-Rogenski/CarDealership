/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.State;
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
public class CarDealershipStateDaoDB implements CarDealershipStateDao {

    @Autowired
    JdbcTemplate jdbc;
    public static final class StateMapper implements RowMapper<State> {

        @Override
        public State mapRow(ResultSet rs, int index) throws SQLException {
            State s = new State();
            s.setStateId(rs.getInt("id"));
            s.setState(rs.getString("state"));
            return s;
        }
    }
    
    @Override
    public List<State> getAllStates() {
        final String SELECT_ALL_STATES = "SELECT * FROM state";
        return jdbc.query(SELECT_ALL_STATES, new CarDealershipStateDaoDB.StateMapper());
    }

    @Override
    public State getStateById(int id) {
        try {
            final String SELECT_STATE_BY_ID = "SELECT * FROM state WHERE id = ?";
            return jdbc.queryForObject(SELECT_STATE_BY_ID, new CarDealershipStateDaoDB.StateMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public State addState(State state) {
        final String INSERT_STATE = "INSERT INTO state(state) "
                + "VALUES(?)";
        jdbc.update(INSERT_STATE, 
                state.getState());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        state.setStateId(newId);
        return state;
    }

    @Override
    public boolean updateState(State state) {
        final String UPDATE_STATE = "UPDATE state SET state = ?"
                + "WHERE id = ?";
        jdbc.update(UPDATE_STATE,
                state.getState(),
                state.getStateId());
        return true;
    }

    @Override
    @Transactional
    public void deleteStateById(int id) {
        final String DELETE_SALE_STATE = "DELETE FROM sale "
                + "WHERE stateId = ?";
        jdbc.update(DELETE_SALE_STATE, id);
        
        final String DELETE_STATE = "DELETE FROM state WHERE id = ?";
        jdbc.update(DELETE_STATE, id);
    }
    
}
