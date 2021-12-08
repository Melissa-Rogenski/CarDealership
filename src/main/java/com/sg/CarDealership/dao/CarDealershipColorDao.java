/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Color;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface CarDealershipColorDao {
    List<Color> getAllColors();
    Color getColorById(int id);
    Color addColor(Color color);
    boolean updateColor(Color color);
    void deleteColorById(int id);
}
