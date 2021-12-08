/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.State;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface CarDealershipStateDao {
    List<State> getAllStates();
    State getStateById(int id);
    State addState(State state);
    boolean updateState(State state);
    void deleteStateById(int id);
}
