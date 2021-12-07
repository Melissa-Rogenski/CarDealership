package com.sg.CarDealership.dao;

import java.util.List;

import com.sg.CarDealership.model.Sale;
import com.sg.CarDealership.service.ReportQueryContext;

public interface CarDealershipSaleDao {
	Sale addSale(Sale sale);
	List<Sale> getAllSales();
	List<Sale> getAllSales(ReportQueryContext query);

}
