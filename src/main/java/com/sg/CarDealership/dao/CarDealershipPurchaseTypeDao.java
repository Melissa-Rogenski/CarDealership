/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.PurchaseType;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface CarDealershipPurchaseTypeDao {
    List<PurchaseType> getAllPurchaseTypes();
    PurchaseType getPurchaseTypeById(int id);
    PurchaseType addPurchaseType(PurchaseType purchaseType);
    void updatePurchaseType(PurchaseType purchaseType);
    void deletePurchaseTypeById(int id);
}
