/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dao.CarDealershipMakeDaoDB.MakeMapper;
import com.sg.CarDealership.dao.CarDealershipUserDaoDB.UserMapper;
import com.sg.CarDealership.model.Make;
import com.sg.CarDealership.model.Model;
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
public class CarDealershipModelDaoDB implements CarDealershipModelDao {

    @Autowired
    JdbcTemplate jdbc;
    public static final class ModelMapper implements RowMapper<Model> {

        @Override
        public Model mapRow(ResultSet rs, int index) throws SQLException {
            Model m = new Model();
            m.setModelId(rs.getInt("id"));
            m.setModel(rs.getString("model"));
            m.setDateAdded(rs.getTimestamp("dateAdded").toLocalDateTime());
            return m;
        }
    }
    
    private User getUserForModel(Model model) {
        final String SELECT_USER_FOR_MODEL = "SELECT u.* FROM user u "
                + "JOIN model m ON u.id = m.userId WHERE m.id = ?";
        return jdbc.queryForObject(SELECT_USER_FOR_MODEL, new UserMapper(), 
                model.getModelId());
    }
    
    private Make getMakeForModel(Model model) {
        final String SELECT_MAKE_FOR_MODEL = "SELECT ma.* FROM make ma "
                + "JOIN model mo ON ma.id = mo.makeId WHERE mo.id = ?";
        return jdbc.queryForObject(SELECT_MAKE_FOR_MODEL, new MakeMapper(), 
                model.getModelId());
    }
    
    private void addUserToModels(List<Model> models) {
        for(Model model : models) {
            model.setUser(getUserForModel(model));
        }
    }
    
    private void addMakeToModels(List<Model> models) {
        for(Model model : models) {
            model.setMake(getMakeForModel(model));
        }
    }
    
    @Override
    public List<Model> getAllModels() {
        final String SELECT_ALL_MODELS = "SELECT * FROM model";
        List<Model> models = jdbc.query(SELECT_ALL_MODELS, new ModelMapper());
        
        addUserToModels(models);
        addMakeToModels(models);
        
        return models;
    }

    @Override
    public Model getModelById(int id) {
        try {
            final String SELECT_MODEL_BY_ID = "SELECT * FROM model WHERE id = ?";
            Model model = jdbc.queryForObject(SELECT_MODEL_BY_ID, 
                    new ModelMapper(), id);
            model.setUser(getUserForModel(model));
            model.setMake(getMakeForModel(model));
            return model;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Model addModel(Model model) {
        final String INSERT_MODEL = "INSERT INTO model(model, dateAdded, makeId, userId) VALUES(?,?,?,?)";
        jdbc.update(INSERT_MODEL,
                model.getModel(),
                Timestamp.valueOf(model.getDateAdded()),
                model.getMake().getMakeId(),
                model.getUser().getUserId());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setModelId(newId);
     
        return model;
    }

    @Override
    public boolean updateModel(Model model) {
        final String UPDATE_MODEL = "UPDATE model "
                + "SET model = ?, dateAdded = ?, makeId = ?, userId = ? WHERE id = ?";
        jdbc.update(UPDATE_MODEL,
                model.getModel(),
                Timestamp.valueOf(model.getDateAdded()),
                model.getMake().getMakeId(),
                model.getUser().getUserId(),
                model.getModelId());
        return true;
    }

    @Override
    @Transactional
    public void deleteModelById(int id) {
        final String DELETE_VEHICLE_MODEL = "DELETE FROM vehicle "
                + "WHERE modelId = ?";
        jdbc.update(DELETE_VEHICLE_MODEL, id);

        final String DELETE_MODEL = "DELETE FROM model WHERE id = ?";
        jdbc.update(DELETE_MODEL, id);
    }
    
}
