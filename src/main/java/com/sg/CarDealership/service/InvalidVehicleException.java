/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.CarDealership.service;

/**
 *
 * @author calebdiaz
 */
public class InvalidVehicleException extends Exception {

    public InvalidVehicleException(String message) {
        super(message);
    }

    public InvalidVehicleException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
