package com.sg.CarDealership.model;

import java.util.Objects;

public class PurchaseType {

    private int purchaseTypeId;
    private String purchaseType;

    public int getPurchaseTypeId() {
        return purchaseTypeId;
    }

    public void setPurchaseTypeId(int purchaseTypeId) {
        this.purchaseTypeId = purchaseTypeId;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.purchaseTypeId;
        hash = 43 * hash + Objects.hashCode(this.purchaseType);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PurchaseType other = (PurchaseType) obj;
        if (this.purchaseTypeId != other.purchaseTypeId) {
            return false;
        }
        if (!Objects.equals(this.purchaseType, other.purchaseType)) {
            return false;
        }
        return true;
    }
    
    

}
