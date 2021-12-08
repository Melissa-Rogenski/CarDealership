/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.model.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mroge
 */
@Repository
public class CarDealershipContactDaoDB implements CarDealershipContactDao {

    @Autowired
    JdbcTemplate jdbc;
    public static final class ContactMapper implements RowMapper<Contact> {

        @Override
        public Contact mapRow(ResultSet rs, int index) throws SQLException {
            Contact c = new Contact();
            c.setContactId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setMessage(rs.getString("message"));
            c.setPhone(rs.getString("phone"));
            c.setEmail(rs.getString("email"));
            return c;
        }
    }
    
    @Override
    public List<Contact> getAllContacts() {
        final String SELECT_ALL_CONTACTS = "SELECT * FROM contact";
        return jdbc.query(SELECT_ALL_CONTACTS, new CarDealershipContactDaoDB.ContactMapper());
    }

    @Override
    public Contact getContactById(int id) {
        try {
            final String SELECT_CONTACT_BY_ID = "SELECT * FROM contact WHERE id = ?";
            return jdbc.queryForObject(SELECT_CONTACT_BY_ID, new CarDealershipContactDaoDB.ContactMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Contact addContact(Contact contact) {
        final String INSERT_CONTACT = "INSERT INTO contact(name,message,phone,email) "
                + "VALUES(?)";
        jdbc.update(INSERT_CONTACT, 
                contact.getName(),
                contact.getMessage(),
                contact.getPhone(),
                contact.getEmail());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        contact.setContactId(newId);
        return contact;
    }

    @Override
    public boolean updateContact(Contact contact) {
        final String UPDATE_CONTACT = "UPDATE contact SET name = ?, message = ?, phone = ?, email = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_CONTACT,
                contact.getName(),
                contact.getMessage(),
                contact.getPhone(),
                contact.getEmail(),
                contact.getContactId());
        return true;
    }

    @Override
    @Transactional
    public void deleteContactById(int id) {
        final String DELETE_CONTACT = "DELETE FROM contact WHERE id = ?";
        jdbc.update(DELETE_CONTACT, id);
    }
    
}
