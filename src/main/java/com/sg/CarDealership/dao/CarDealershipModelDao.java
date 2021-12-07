package com.sg.CarDealership.dao;

import java.util.List;

import com.sg.CarDealership.model.Make;

public interface CarDealershipModelDao {
	Make addMake(Make make);
	List<Make> getAllMakes();
	Make getMakeById(int id);

}