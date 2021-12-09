/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.CarDealership.dao;

import com.sg.CarDealership.TestApplicationConfiguration;
import com.sg.CarDealership.model.BodyStyle;
import com.sg.CarDealership.model.Color;
import com.sg.CarDealership.model.Condition;
import com.sg.CarDealership.model.Contact;
import com.sg.CarDealership.model.Interior;
import com.sg.CarDealership.model.Make;
import com.sg.CarDealership.model.Model;
import com.sg.CarDealership.model.PurchaseType;
import com.sg.CarDealership.model.Role;
import com.sg.CarDealership.model.Sale;
import com.sg.CarDealership.model.Special;
import com.sg.CarDealership.model.State;
import com.sg.CarDealership.model.Trans;
import com.sg.CarDealership.model.User;
import com.sg.CarDealership.model.Vehicle;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mroge
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class ColorDaoTest {
    @Autowired
    CarDealershipBodyStyleDao bodyStyleDao;
    @Autowired
    CarDealershipColorDao colorDao;
    @Autowired
    CarDealershipConditionDao conditionDao;
    @Autowired
    CarDealershipContactDao contactDao;
    @Autowired
    CarDealershipInteriorDao interiorDao;
    @Autowired
    CarDealershipMakeDao makeDao;
    @Autowired
    CarDealershipModelDao modelDao;
    @Autowired
    CarDealershipPurchaseTypeDao purchaseTypeDao;
    @Autowired
    CarDealershipRoleDao roleDao;
    @Autowired
    CarDealershipSaleDao saleDao;
    @Autowired
    CarDealershipSpecialDao specialDao;
    @Autowired
    CarDealershipStateDao stateDao;
    @Autowired
    CarDealershipTransDao transDao;
    @Autowired
    CarDealershipUserDao userDao;
    @Autowired
    CarDealershipVehicleDao vehicleDao;
    
    
    @Before
    public void setUp(){
        List<BodyStyle> styles = bodyStyleDao.getAllBodyStyles();
        for(BodyStyle style:styles){
            bodyStyleDao.deleteBodyStyleById(style.getBodyStyleId());
        }
        
        List<Color> colors = colorDao.getAllColors();
        for(Color color:colors){
            colorDao.deleteColorById(color.getColorId());
        }
        
        List<Condition> conditions = conditionDao.getAllConditions();
        for(Condition condition:conditions){
            conditionDao.deleteConditionById(condition.getConditionId());
        }
        
        List<Contact> contacts = contactDao.getAllContacts();
        for(Contact contact:contacts){
            contactDao.deleteContactById(contact.getContactId());
        }
        
        List<Interior> interiors = interiorDao.getAllInteriors();
        for(Interior interior:interiors){
            interiorDao.deleteInteriorById(interior.getInteriorId());
        }
        
        List<Make> makes = makeDao.getAllMakes();
        for(Make make:makes){
            makeDao.deleteMakeById(make.getMakeId());
        }
        
        List<Model> models = modelDao.getAllModels();
        for(Model model:models){
            modelDao.deleteModelById(model.getModelId());
        }
        
        List<PurchaseType> purchases = purchaseTypeDao.getAllPurchaseTypes();
        for(PurchaseType purchase:purchases){
            purchaseTypeDao.deletePurchaseTypeById(purchase.getPurchaseTypeId());
        }
        
        List<Role> roles = roleDao.getAllRoles();
        for(Role role:roles){
            roleDao.deleteRoleById(role.getRoleId());
        }
        
        List<Sale> sales = saleDao.getAllSales();
        for(Sale sale:sales){
            saleDao.deleteSaleById(sale.getSaleId());
        }
        
        List<Special> specials = specialDao.getAllSpecials();
        for(Special special:specials){
            specialDao.deleteSpecialById(special.getSpecialId());
        }
        
        List<State> states = stateDao.getAllStates();
        for(State state:states){
            stateDao.deleteStateById(state.getStateId());
        }
        
        List<Trans> trans = transDao.getAllTrans();
        for(Trans tran:trans){
            transDao.deleteTransById(tran.getTransId());
        }
        
        List<User> users = userDao.getAllUsers();
        for(User user:users){
            userDao.deleteUserById(user.getUserId());
        }
        
        List<Vehicle> vehicles = vehicleDao.getAllVehicles();
        for(Vehicle vehicle:vehicles){
            vehicleDao.deleteVehicleById(vehicle.getVehicleId());
        }
    }
    
    public ColorDaoTest(){  
    }
    
    @Test
    public void testAddGetColor() {
        Color c = new Color();
        c.setColor("Test Color");
        c = colorDao.addColor(c);
        
        Color fromDao = colorDao.getColorById(c.getColorId());
        
        assertEquals(c, fromDao);
    }
    
    @Test
    public void testGetAllColors() {
        Color bs = new Color();
        bs.setColor("Test Color");
        colorDao.addColor(bs);
        
        Color bs2 = new Color();
        bs2.setColor("Test Color 2");
        colorDao.addColor(bs2);
        
        List<Color> colors = colorDao.getAllColors();
        
        assertEquals(2, colors.size());
        assertTrue(colors.contains(bs));
        assertTrue(colors.contains(bs2));
    }
    
    @Test
    public void testUpdateColor() {
        Color bs = new Color();
        bs.setColor("Updated Color");
        bs = colorDao.addColor(bs);
        
        Color fromDao = colorDao.getColorById(bs.getColorId());
        
        assertEquals(bs, fromDao);
        
        bs.setColor("AnotherUpdated Color Test");
        
        colorDao.updateColor(bs);
        
        assertNotEquals(bs, fromDao);
        
        fromDao = colorDao.getColorById(bs.getColorId());
        
        assertEquals(bs, fromDao);
    }
}
