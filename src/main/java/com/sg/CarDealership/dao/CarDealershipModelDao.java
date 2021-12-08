/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Model;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface CarDealershipModelDao {
    List<Model> getAllModels();
    Model getModelById(int id);
    Model addModel(Model model);
    boolean updateModel(Model model);
    void deleteModelById(int id);
}
