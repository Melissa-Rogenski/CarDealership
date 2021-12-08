/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Condition;
import com.sg.CarDealership.model.Role;
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
public class CarDealershipRoleDaoDB implements CarDealershipRoleDao {

    @Autowired
    JdbcTemplate jdbc;
    public static final class RoleMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int index) throws SQLException {
            Role r = new Role();
            r.setRoleId(rs.getInt("id"));
            r.setRole(rs.getString("role"));
            return r;
        }
    }
    
    @Override
    public List<Role> getAllRoles() {
        final String SELECT_ALL_ROLES = "SELECT * FROM role";
        return jdbc.query(SELECT_ALL_ROLES, new CarDealershipRoleDaoDB.RoleMapper());
    }

    @Override
    public Role getRoleById(int id) {
    try {
            final String SELECT_ROLE_BY_ID = "SELECT * FROM role WHERE id = ?";
            return jdbc.queryForObject(SELECT_ROLE_BY_ID, new CarDealershipRoleDaoDB.RoleMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Role addRole(Role role) {
        final String INSERT_ROLE = "INSERT INTO role(role) "
                + "VALUES(?)";
        jdbc.update(INSERT_ROLE, 
                role.getRole());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        role.setRoleId(newId);
        return role;
    }

    @Override
    public boolean updateRole(Role role) {
        final String UPDATE_ROLE = "UPDATE role SET role = ?"
                + "WHERE id = ?";
        jdbc.update(UPDATE_ROLE,
                role.getRole(),
                role.getRoleId());
        return true;
    }

    @Override
    @Transactional
    public void deleteRoleById(int id) {
        final String DELETE_USER_ROLE = "DELETE FROM user "
                + "WHERE roleId = ?";
        jdbc.update(DELETE_USER_ROLE, id);
        
        final String DELETE_ROLE = "DELETE FROM role WHERE id = ?";
        jdbc.update(DELETE_ROLE, id);
    }
    
}
