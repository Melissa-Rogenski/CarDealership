package com.sg.CarDealership.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Vehicle {
    private int vehicleId;
    private LocalDate year;
    private BigDecimal salePrice;
    private BigDecimal MSRP;
    private int mileage;
    private String vin;
    private String description;
    private String picturePath;
    private boolean purchased = false;
    private boolean featured = false;
    private Make make;
    private Model model;
    private Condition condition;
    private BodyStyle bodyStyle;
    private Interior interior;
    private Trans trans;
    private Color color;

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getMSRP() {
        return MSRP;
    }

    public void setMSRP(BigDecimal MSRP) {
        this.MSRP = MSRP;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
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

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public BodyStyle getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(BodyStyle bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public Interior getInterior() {
        return interior;
    }

    public void setInterior(Interior interior) {
        this.interior = interior;
    }

    public Trans getTrans() {
        return trans;
    }

    public void setTrans(Trans trans) {
        this.trans = trans;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.vehicleId;
        hash = 11 * hash + Objects.hashCode(this.year);
        hash = 11 * hash + Objects.hashCode(this.salePrice);
        hash = 11 * hash + this.mileage;
        hash = 11 * hash + Objects.hashCode(this.vin);
        hash = 11 * hash + Objects.hashCode(this.description);
        hash = 11 * hash + Objects.hashCode(this.picturePath);
        hash = 11 * hash + (this.purchased ? 1 : 0);
        hash = 11 * hash + (this.featured ? 1 : 0);
        hash = 11 * hash + Objects.hashCode(this.make);
        hash = 11 * hash + Objects.hashCode(this.model);
        hash = 11 * hash + Objects.hashCode(this.condition);
        hash = 11 * hash + Objects.hashCode(this.bodyStyle);
        hash = 11 * hash + Objects.hashCode(this.interior);
        hash = 11 * hash + Objects.hashCode(this.trans);
        hash = 11 * hash + Objects.hashCode(this.color);
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
        final Vehicle other = (Vehicle) obj;
        if (this.vehicleId != other.vehicleId) {
            return false;
        }
        if (this.mileage != other.mileage) {
            return false;
        }
        if (this.purchased != other.purchased) {
            return false;
        }
        if (this.featured != other.featured) {
            return false;
        }
        if (!Objects.equals(this.vin, other.vin)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.picturePath, other.picturePath)) {
            return false;
        }
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        if (!Objects.equals(this.salePrice, other.salePrice)) {
            return false;
        }
        if (!Objects.equals(this.make, other.make)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.condition, other.condition)) {
            return false;
        }
        if (!Objects.equals(this.bodyStyle, other.bodyStyle)) {
            return false;
        }
        if (!Objects.equals(this.interior, other.interior)) {
            return false;
        }
        if (!Objects.equals(this.trans, other.trans)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return true;
    }

    
}
