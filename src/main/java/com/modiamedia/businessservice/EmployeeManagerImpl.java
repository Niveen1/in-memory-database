package com.modiamedia.businessservice;

import java.util.Map;

import com.modiamedia.model.dao.EmployeeDAO;
import com.modiamedia.model.vo.EmployeeData;
import com.modiamedia.model.vo.EmployeeVO;

/**
 * will implment EmployeeManager methods , it will delegate to employeeDao to execute CRUD operations
 * 
 * @author Niveen Magdy
 * @version 15-11-2017
 */
public class EmployeeManagerImpl implements EmployeeManager {

	private EmployeeDAO employeeDAO;

	public int addEmployee(EmployeeVO employeeVO) {
		int totalEmployees = 0;
		totalEmployees = getEmployeeDAO().addEmployee(employeeVO.getId(), employeeVO.getEmployeeData());
		return totalEmployees;
	}

	public void removeEmployee(EmployeeVO employeeVO) {
		if (null != getEmployeeDAO().getEmployeeById(employeeVO.getId())) {
			getEmployeeDAO().removeEmployee(employeeVO.getId());
		} else {
			throw new IllegalStateException("Employee '" + employeeVO.getId() + "' not found .");
		}
	}

	public EmployeeData updateEmployee(String employeeId, String key, String newValue) {
		EmployeeData employeeData = getEmployeeDAO().updateEmployeeData(employeeId, key, newValue);
		return employeeData;
	}

	public EmployeeData getEmployeeById(String employeeId) {
		// get Employee with given ID
		EmployeeData employeeData = getEmployeeDAO().getEmployeeById(employeeId);
		return employeeData;
	}

	public Map<String, EmployeeData> getAllEmployees() {
		Map<String, EmployeeData> employees = getEmployeeDAO().getAllEmployees();
		return employees;
	}

	public int countEmployee() {
		return getEmployeeDAO().getEmployeeCount();
	}

	// ========================================setter and getter============================================

	public EmployeeDAO getEmployeeDAO() {
		if (null == employeeDAO) {
			employeeDAO = new EmployeeDAO();
		}
		return employeeDAO;
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

}
