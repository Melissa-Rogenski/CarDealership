/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Interior;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface CarDealershipInteriorDao {
    List<Interior> getAllInteriors();
    Interior getInteriorById(int id);
    Interior addInterior(Interior interior);
    boolean updateInterior(Interior interior);
    void deleteInteriorById(int id);
}
