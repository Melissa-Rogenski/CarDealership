/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.dao.CarDealershipRoleDaoDB.RoleMapper;
import com.sg.CarDealership.model.Role;
import com.sg.CarDealership.model.User;
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
public class CarDealershipUserDaoDB implements CarDealershipUserDao {

    @Autowired
    JdbcTemplate jdbc;
    public static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int index) throws SQLException {
            User u = new User();
            u.setUserId(rs.getInt("id"));
            u.setFirstName(rs.getString("firstName"));
            u.setLastName(rs.getString("lastName"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            return u;
        }
    }
    
    private Role getRoleForUser(User user) {
        final String SELECT_ROLE_FOR_USER = "SELECT r.* FROM role r "
                + "JOIN make u ON r.id = u.userId WHERE u.id = ?";
        return jdbc.queryForObject(SELECT_ROLE_FOR_USER, new RoleMapper(), 
                user.getUserId());
    }
    
    @Override
    public List<User> getAllUsers() {
        final String SELECT_ALL_USERS = "SELECT * FROM user";
        List<User> users = jdbc.query(SELECT_ALL_USERS, new CarDealershipUserDaoDB.UserMapper());
        
        addRoleToUsers(users);
        
        return users;
    }
    
    private void addRoleToUsers(List<User> users) {
        for(User user : users) {
            user.setRole(getRoleForUser(user));
        }
    }

    @Override
    public User getUserById(int id) {
        try {
            final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
            User user = jdbc.queryForObject(SELECT_USER_BY_ID, 
                    new CarDealershipUserDaoDB.UserMapper(), id);
            user.setRole(getRoleForUser(user));
            return user;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public User addUser(User user) {
        final String INSERT_USER = "INSERT INTO user(firstName, lastName, email, password, roleId) VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_USER,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().getRoleId());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        user.setUserId(newId);
     
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        final String UPDATE_USER = "UPDATE user "
                + "SET firstName = ?, lastName = ?, email = ?, password = ?, roleId = ? WHERE id = ?";
        jdbc.update(UPDATE_USER,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().getRoleId(),
                user.getUserId());
        return true;
    }

    @Override
    public void deleteUserById(int id) {
        final String DELETE_SALE_USER = "DELETE FROM sale "
                + "WHERE userId = ?";
        jdbc.update(DELETE_SALE_USER, id);
        
        final String DELETE_MODEL_USER = "DELETE FROM model "
                + "WHERE userId = ?";
        jdbc.update(DELETE_MODEL_USER, id);
        
        final String DELETE_MAKE_USER = "DELETE FROM make WHERE userId = ?";
        jdbc.update(DELETE_MAKE_USER, id);
        
        final String DELETE_USER = "DELETE FROM user WHERE id = ?";
        jdbc.update(DELETE_USER, id);
    }
    
}
