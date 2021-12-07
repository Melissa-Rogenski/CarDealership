/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Contact;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface CarDealershipContactDao {
    List<Contact> getAllContacts();
    Contact getContactById(int id);
    Contact addContact(Contact contact);
    void updateContact(Contact contact);
    void deleteContactById(int id);
}
