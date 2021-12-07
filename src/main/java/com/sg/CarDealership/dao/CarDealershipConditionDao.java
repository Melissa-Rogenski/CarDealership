package com.sg.CarDealership.dao;

import ch.qos.logback.core.joran.conditional.Condition;

public interface CarDealershipConditionDao {
	Condition getConditionById(int id);

}
