package com.sg.CarDealership.model;

import java.util.Objects;

public class Condition {

    private int conditionId;
    private String condition;

    public int getConditionId() {
        return conditionId;
    }

    public void setConditionId(int conditionId) {
        this.conditionId = conditionId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.conditionId;
        hash = 11 * hash + Objects.hashCode(this.condition);
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
        final Condition other = (Condition) obj;
        if (this.conditionId != other.conditionId) {
            return false;
        }
        if (!Objects.equals(this.condition, other.condition)) {
            return false;
        }
        return true;
    }
    
    

}
