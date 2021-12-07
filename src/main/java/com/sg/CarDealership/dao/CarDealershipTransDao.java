/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Trans;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface CarDealershipTransDao {
    List<Trans> getAllTrans();
    Trans getTransById(int id);
    Trans addTrans(Trans trans);
    void updateTrans(Trans trans);
    void deleteTransById(int id);
}
