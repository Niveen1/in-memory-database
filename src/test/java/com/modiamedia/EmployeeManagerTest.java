package com.modiamedia;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.modiamedia.businessservice.EmployeeManager;
import com.modiamedia.businessservice.EmployeeManagerImpl;
import com.modiamedia.model.vo.EmployeeData;
import com.modiamedia.model.vo.EmployeeVO;

/**
 * Unit test for Employee Manager
 * 
 * @author Niveen Magdy
 * @version 15-11-2017
 */

public class EmployeeManagerTest extends TestCase {
	public void testAddNewEmployee() {
		EmployeeManager employeeManager = new EmployeeManagerImpl();

		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setId("1001");
		employeeVO.getEmployeeData().setName("Ali");
		employeeVO.getEmployeeData().setDesignation("Jave developer");
		employeeVO.getEmployeeData().setSalary("10000");
		int totalEmployees = employeeManager.addEmployee(employeeVO);

		assertEquals(1, totalEmployees);
	}

	public void testAddExistEmployee() {
		EmployeeManager employeeManager = new EmployeeManagerImpl();

		String key = "1001";

		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setId(key);
		employeeVO.getEmployeeData().setName("Ali");
		employeeVO.getEmployeeData().setDesignation("Jave developer");
		employeeVO.getEmployeeData().setSalary("10000");
		employeeManager.addEmployee(employeeVO);

		employeeVO = new EmployeeVO();
		employeeVO.setId(key);
		employeeVO.getEmployeeData().setName("Ali");
		employeeVO.getEmployeeData().setDesignation("Jave developer");
		employeeVO.getEmployeeData().setSalary("10000");
		try {
			employeeManager.addEmployee(employeeVO);
		} catch (IllegalStateException ex) {
			assertEquals("A value for '" + key + "' is already Added before.", ex.getMessage());
		}
	}

	public void testUpdateEmployee() {

		EmployeeManager employeeManager = new EmployeeManagerImpl();

		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setId("1001");
		employeeVO.getEmployeeData().setName("Ali");
		employeeVO.getEmployeeData().setDesignation("Jave developer");
		employeeVO.getEmployeeData().setSalary("10000");
		employeeManager.addEmployee(employeeVO);

		EmployeeData expectedData = new EmployeeData();
		expectedData.setName("Ahmed");

		String employeeId = "1001";
		String key = "name";
		String value = "Ahmed";
		EmployeeData updateEmployee = employeeManager.updateEmployee(employeeId, key, value);
		assertSame(expectedData.getName(), updateEmployee.getName());
	}

	public void testUpdateNotExistEmployee() {

		EmployeeManager employeeManager = new EmployeeManagerImpl();

		EmployeeData expectedData = new EmployeeData();
		expectedData.setName("Ahmed");

		String employeeId = "1001";
		String key = "name";
		String value = "Ahmed";
		try {
			employeeManager.updateEmployee(employeeId, key, value);
		} catch (IllegalStateException ex) {
			assertEquals("There is no value to update for employeeId '" + employeeId + "'.", ex.getMessage());
		}
	}

	public void testUpdateNullPropertyNameEmployee() {

		EmployeeManager employeeManager = new EmployeeManagerImpl();

		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setId("1001");
		employeeVO.getEmployeeData().setName("Ali");
		employeeVO.getEmployeeData().setDesignation("Jave developer");
		employeeVO.getEmployeeData().setSalary("10000");
		employeeManager.addEmployee(employeeVO);

		EmployeeData expectedData = new EmployeeData();
		expectedData.setName("Ahmed");

		String employeeId = "1001";
		String key = null;
		String value = "Ahmed";
		try {
			employeeManager.updateEmployee(employeeId, key, value);
		} catch (IllegalArgumentException ex) {
			assertEquals("Invalid key '" + key + "'.", ex.getMessage());
		}
	}

	public void testUpdateNullPropertyValueEmployee() {

		EmployeeManager employeeManager = new EmployeeManagerImpl();

		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setId("1001");
		employeeVO.getEmployeeData().setName("Ali");
		employeeVO.getEmployeeData().setDesignation("Jave developer");
		employeeVO.getEmployeeData().setSalary("10000");
		employeeManager.addEmployee(employeeVO);

		EmployeeData expectedData = new EmployeeData();
		expectedData.setName("Ahmed");

		String employeeId = "1001";
		String key = "name";
		String value = null;
		try {
			employeeManager.updateEmployee(employeeId, key, value);
		} catch (IllegalArgumentException ex) {
			assertEquals("New Value cannot be null.", ex.getMessage());
		}
	}

	public void testDeleteNotExistEmployee() {

		EmployeeManager employeeManager = new EmployeeManagerImpl();

		String employeeId = "1001";
		EmployeeVO deletedEmp = new EmployeeVO();
		deletedEmp.setId(employeeId);
		try {
			employeeManager.removeEmployee(deletedEmp);
		} catch (IllegalStateException ex) {
			assertEquals("Employee '1001' not found .", ex.getMessage());
		}
	}

	public void testGetEmployeeById() {
		EmployeeManager employeeManager = new EmployeeManagerImpl();

		String id = "1001";
		String name = "Ali";
		String designation = "Jave developer";
		String salary = "10000";

		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setId(id);
		employeeVO.getEmployeeData().setName(name);
		employeeVO.getEmployeeData().setDesignation(designation);
		employeeVO.getEmployeeData().setSalary(salary);
		employeeManager.addEmployee(employeeVO);

		EmployeeData expectedData = new EmployeeData();
		expectedData.setName(name);
		expectedData.setDesignation(designation);
		expectedData.setSalary(salary);

		EmployeeData employeeById = null;
		try {
			employeeById = employeeManager.getEmployeeById(id);
		} catch (IllegalStateException ex) {
			assertSame(expectedData, employeeById);
		}
	}

	public void testGetEmployeeByIdNotAdded() {
		EmployeeManager employeeManager = new EmployeeManagerImpl();

		String id = "1001";
		String name = "Ali";
		String designation = "Jave developer";
		String salary = "10000";

		EmployeeData expectedData = new EmployeeData();
		expectedData.setName(name);
		expectedData.setDesignation(designation);
		expectedData.setSalary(salary);

		EmployeeData employeeById = null;
		try {
			employeeById = employeeManager.getEmployeeById(id);
		} catch (IllegalStateException ex) {
			assertNull(employeeById);
		}
	}

	public void testGetAllEmployees() {
		EmployeeManager employeeManager = new EmployeeManagerImpl();

		EmployeeVO employeeVOAli = new EmployeeVO();
		employeeVOAli.setId("1001");
		employeeVOAli.getEmployeeData().setName("Ali");
		employeeVOAli.getEmployeeData().setDesignation("Jave developer");
		employeeVOAli.getEmployeeData().setSalary("10000");
		employeeManager.addEmployee(employeeVOAli);

		EmployeeVO employeeVOAhmed = new EmployeeVO();
		employeeVOAhmed.setId("1002");
		employeeVOAhmed.getEmployeeData().setName("Ahmed");
		employeeVOAhmed.getEmployeeData().setDesignation("IT Manager");
		employeeVOAhmed.getEmployeeData().setSalary("22000");
		employeeManager.addEmployee(employeeVOAhmed);

		EmployeeVO employeeVOFouad = new EmployeeVO();
		employeeVOFouad.setId("1003");
		employeeVOFouad.getEmployeeData().setName("Fouad");
		employeeVOFouad.getEmployeeData().setDesignation("HR Officer");
		employeeVOFouad.getEmployeeData().setSalary("20000");
		employeeManager.addEmployee(employeeVOFouad);

		Map<String, EmployeeData> expectedData = new HashMap<String, EmployeeData>();
		expectedData.put(employeeVOAli.getId(), employeeVOAli.getEmployeeData());
		expectedData.put(employeeVOAhmed.getId(), employeeVOAhmed.getEmployeeData());
		expectedData.put(employeeVOFouad.getId(), employeeVOFouad.getEmployeeData());

		Map<String, EmployeeData> employees = null;
		try {
			employees = employeeManager.getAllEmployees();
		} catch (IllegalStateException ex) {
			assertSame(expectedData, employees);
		}
	}

	public void testGetAllEmployeesEmptyData() {
		EmployeeManager employeeManager = new EmployeeManagerImpl();

		Map<String, EmployeeData> employees = null;
		try {
			employees = employeeManager.getAllEmployees();
		} catch (IllegalStateException ex) {
			assertNull(employees);
		}
	}
}
