/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.User;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface CarDealershipUserDao {
    List<User> getAllUsers();
    User getUserById(int id);
    User addUser(User user);
    void updateUser(User user);
    void deleteUserById(int id);
}
