/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Make;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface CarDealershipMakeDao {
    List<Make> getAllMakes();
    Make getMakeById(int id);
    Make addMake(Make make);
    void updateMake(Make make);
    void deleteMakeById(int id);
}
