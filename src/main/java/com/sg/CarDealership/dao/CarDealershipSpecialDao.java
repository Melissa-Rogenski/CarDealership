package com.sg.CarDealership.dao;

import java.util.List;

import com.sg.CarDealership.model.Special;

public interface CarDealershipSpecialDao {
	Special addSpecial(Special special);
	List<Special> getAllSpecials();
	boolean deleteSpecialById(int id);

}
