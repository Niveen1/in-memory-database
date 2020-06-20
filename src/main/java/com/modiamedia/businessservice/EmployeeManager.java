package com.modiamedia.businessservice;

import java.util.Map;

import com.modiamedia.model.vo.EmployeeData;
import com.modiamedia.model.vo.EmployeeVO;

/**
 * 
 * @author Niveen Magdy
 * @version 15-11-2017
 */
public interface EmployeeManager {

	public int addEmployee(EmployeeVO employeeVO);

	public void removeEmployee(EmployeeVO employeeVO);

	public EmployeeData updateEmployee(String employeeId, String key, String value);

	public EmployeeData getEmployeeById(String employeeId);

	public Map<String, EmployeeData> getAllEmployees();

	public int countEmployee();

}
