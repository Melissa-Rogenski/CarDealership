package com.sg.CarDealership.dao;

import java.util.List;

import com.sg.CarDealership.model.User;

public interface CarDealershipUserDao {
	User addUser(User user);
	User getUserById(User user);
	List<User> getAllUsers();
	boolean updateUser(User user);

}
