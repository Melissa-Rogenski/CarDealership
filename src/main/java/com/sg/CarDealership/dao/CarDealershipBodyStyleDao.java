/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.cardealership.data;

import com.sg.cardealership.models.BodyStyle;
import java.util.List;

/**
 *
 * @author calebdiaz
 */
public interface CarDealershipBodyStyleDao {
    List<BodyStyle> getAllBodyStyles();
    BodyStyle getBodyStyleById(int id);
    BodyStyle addBodyStyle(BodyStyle bodyStyle);
    void updateBodyStyle(BodyStyle bodyStyle);
    void deleteBodyStyleById(int id);
}
