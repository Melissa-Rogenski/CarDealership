package com.sg.CarDealership.model;

import java.util.Objects;

public class BodyStyle {

    private int bodyStyleId;
    private String bodyStyle;

    public int getBodyStyleId() {
        return bodyStyleId;
    }

    public void setBodyStyleId(int bodyStyleId) {
        this.bodyStyleId = bodyStyleId;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.bodyStyleId;
        hash = 37 * hash + Objects.hashCode(this.bodyStyle);
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
        final BodyStyle other = (BodyStyle) obj;
        if (this.bodyStyleId != other.bodyStyleId) {
            return false;
        }
        if (!Objects.equals(this.bodyStyle, other.bodyStyle)) {
            return false;
        }
        return true;
    }
    
    

}
