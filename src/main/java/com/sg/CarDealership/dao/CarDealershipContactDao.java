package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Contact;

public interface CarDealershipContactDao {
	Contact addContact(Contact contact);
	Contact getContactById(int id);

}
