/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.CarDealership.service;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author calebdiaz
 */
public class VehicleQueryContext {
    
    private String searchBar;
    private LocalDate maxYear;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private int conditionId;
    private boolean featured;

    public String getSearchBar() {
        return searchBar;
    }

    public void setSearchBar(String searchBar) {
        this.searchBar = searchBar;
    }

    public LocalDate getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(LocalDate maxYear) {
        this.maxYear = maxYear;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getConditionId() {
        return conditionId;
    }

    public void setConditionId(int conditionId) {
        this.conditionId = conditionId;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    
}
