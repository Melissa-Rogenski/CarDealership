/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Special;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface CarDealershipSpecialDao {
    List<Special> getAllSpecials();
    Special getSpecialById(int id);
    Special addSpecial(Special special);
    boolean updateSpecial(Special special);
    void deleteSpecialById(int id);
}
