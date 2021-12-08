/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Condition;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface CarDealershipConditionDao {
    List<Condition> getAllConditions();
    Condition getConditionById(int id);
    Condition addCondition(Condition condition);
    boolean updateCondition(Condition condition);
    void deleteConditionById(int id);
}
