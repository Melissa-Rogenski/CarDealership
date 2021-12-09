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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
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
public class BodyStyleDaoTest {
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
    
    public BodyStyleDaoTest(){  
    }
    
    @Test
    public void testAddGetBodyStyle() {
        BodyStyle style = new BodyStyle();
        style.setBodyStyle("Test BodyStyle");
        style = bodyStyleDao.addBodyStyle(style);
        
        BodyStyle fromDao = bodyStyleDao.getBodyStyleById(style.getBodyStyleId());
        
        assertEquals(style, fromDao);
    }
    
    @Test
    public void testGetAllBodyStyles() {
        BodyStyle bs = new BodyStyle();
        bs.setBodyStyle("Test Style");
        bs.setBodyStyleId(1);
        bodyStyleDao.addBodyStyle(bs);
        
        BodyStyle bs2 = new BodyStyle();
        bs2.setBodyStyle("Test Style 2");
        bs2.setBodyStyleId(2);
        bodyStyleDao.addBodyStyle(bs2);
        
        List<BodyStyle> styles = bodyStyleDao.getAllBodyStyles();
        
        assertEquals(2, styles.size());
        assertTrue(styles.contains(bs));
        assertTrue(styles.contains(bs2));
    }
    
    @Test
    public void testUpdateBodyStyle() {
        BodyStyle bs = new BodyStyle();
        bs.setBodyStyle("Test Style");
        bs = bodyStyleDao.addBodyStyle(bs);
        
        BodyStyle fromDao = bodyStyleDao.getBodyStyleById(bs.getBodyStyleId());
        
        assertEquals(bs, fromDao);
        
        bs.setBodyStyle("Another Test Style");
        
        bodyStyleDao.updateBodyStyle(bs);
        
        assertNotEquals(bs, fromDao);
        
        fromDao = bodyStyleDao.getBodyStyleById(bs.getBodyStyleId());
        
        assertEquals(bs, fromDao);
    }
}
