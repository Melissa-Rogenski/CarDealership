/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Role;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface CarDealershipRoleDao {
    List<Role> getAllRoles();
    Role getRoleById(int id);
    Role addRole(Role role);
    boolean updateRole(Role role);
    void deleteRoleById(int id);
}
