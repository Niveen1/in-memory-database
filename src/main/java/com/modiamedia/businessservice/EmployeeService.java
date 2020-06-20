package com.modiamedia.businessservice;

import com.modiamedia.memory.OrderEnum;
import com.modiamedia.model.vo.EmployeeVO;

/**
 * 
 * @author Niveen Magdy
 * @version 15-11-2017
 */
public interface EmployeeService {

	public String addEmployee(EmployeeVO employeeVO);

	public String removeEmployee(EmployeeVO employeeVO);

	public String updateEmployee(String employeeId, String key, String value);

	public String getEmployeeById(String employeeId);

	public String getAllEmployees(OrderEnum order);

}
