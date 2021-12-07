package com.sg.CarDealership.model;

import java.util.Objects;

public class Interior {

    private int interiorId;
    private String interior;

    public int getInteriorId() {
        return interiorId;
    }

    public void setInteriorId(int interiorId) {
        this.interiorId = interiorId;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.interiorId;
        hash = 37 * hash + Objects.hashCode(this.interior);
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
        final Interior other = (Interior) obj;
        if (this.interiorId != other.interiorId) {
            return false;
        }
        if (!Objects.equals(this.interior, other.interior)) {
            return false;
        }
        return true;
    }

    
}
