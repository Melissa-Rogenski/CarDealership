package com.sg.CarDealership.model;

import java.util.Objects;

public class Trans {
    
    private int transId;
    private String trans;

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.transId;
        hash = 53 * hash + Objects.hashCode(this.trans);
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
        final Trans other = (Trans) obj;
        if (this.transId != other.transId) {
            return false;
        }
        if (!Objects.equals(this.trans, other.trans)) {
            return false;
        }
        return true;
    }

    
}
