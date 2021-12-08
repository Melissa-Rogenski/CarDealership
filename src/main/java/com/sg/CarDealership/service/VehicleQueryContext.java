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
    
    private String searchBar = "";
    private LocalDate maxYear = LocalDate.MAX;
    private LocalDate minYear = LocalDate.MIN;
    private BigDecimal minPrice = BigDecimal.ZERO;
    private BigDecimal maxPrice = new BigDecimal("1000000000");
    private int conditionId;
    private boolean featured;
    private boolean purchased;

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

    public LocalDate getMinYear() {
        return minYear;
    }

    public void setMinYear(LocalDate minYear) {
        this.minYear = minYear;
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

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    
}
