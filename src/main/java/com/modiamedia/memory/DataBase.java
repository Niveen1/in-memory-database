package com.modiamedia.memory;

import com.modiamedia.businessservice.EmployeeServiceImpl;

public class DataBase {

	public static void main(String[] args) {
		EmployeeTransactionHandler employeeTransactionHandler = new EmployeeTransactionHandler(new EmployeeServiceImpl());
		employeeTransactionHandler.execute();
	}

}
