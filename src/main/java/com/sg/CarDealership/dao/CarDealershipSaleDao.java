/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Sale;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface CarDealershipSaleDao {
    List<Sale> getAllSales();
    Sale getSaleById(int id);
    Sale addSale(Sale sale);
    void updateSale(Sale sale);
    void deleteSaleById(int id);
}
