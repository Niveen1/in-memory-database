package com.modiamedia.model.dao;

import java.util.HashMap;
import java.util.Map;

import com.modiamedia.model.vo.EmployeeData;

/**
 * employeeDAO is responsible for CRUD operations, a {@link HashMap} is used to store the data.
 * 
 * The class is thread safe, but every call is synchronized to block other threads.
 * 
 * 
 * @author Niveen Magdy
 * @version 15-11-2017
 */
public class EmployeeDAO {
	public static final String NAME = "NAME";
	public static final String DESIGNATION = "DESIGNATION";
	public static final String SALARY = "SALARY";

	private final Map<String, EmployeeData> map = new HashMap<String, EmployeeData>();

	/**
	 * Add new element then return map size(count), if value is null throw exception <br>
	 * if key used before throw exception
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public synchronized int addEmployee(String key, EmployeeData value) {
		if (map.containsKey(key)) {
			throw new IllegalStateException("A value for '" + key + "' is already Added before.");
		}

		if (value == null) {
			throw new IllegalArgumentException("Value cannot be null.");
		}

		map.put(key, value);

		return getEmployeeCount();
	}

	/**
	 * update the EmployeeData if exist (will check it by key) , if not exist throw exception, if value is null throw exception
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public synchronized EmployeeData updateEmployeeData(String employeeId, String key, String newValue) {

		if (!map.containsKey(employeeId)) {
			throw new IllegalStateException("There is no value to update for employeeId '" + employeeId + "'.");
		}

		if (newValue == null) {
			throw new IllegalArgumentException("New Value cannot be null.");
		}
		if (key == null) {
			throw new IllegalArgumentException("Invalid key '" + key + "'.");
		}
		// update employee data according to key value
		if (key.equalsIgnoreCase(NAME)) {
			map.get(employeeId).setName(newValue);
		} else if (key.equalsIgnoreCase(DESIGNATION)) {
			map.get(employeeId).setDesignation(newValue);
		} else if (key.equalsIgnoreCase(SALARY)) {
			map.get(employeeId).setSalary(newValue);
		} else {
			throw new IllegalStateException("Invalid key '" + key + "'.");
		}

		return map.get(employeeId);
	}

	/**
	 * remove the element which add with passed key from Map
	 * 
	 * @param key
	 * @return
	 */
	public synchronized boolean removeEmployee(String key) {
		return map.remove(key) != null;
	}

	/**
	 * get specific element using passed key
	 * 
	 * @param key
	 * @return
	 */
	public synchronized EmployeeData getEmployeeById(String key) {
		return map.get(key);
	}

	/**
	 * get all map elements
	 * 
	 * @return
	 */
	public synchronized Map<String, EmployeeData> getAllEmployees() {
		return map;
	}

	/**
	 * get Map size (count elements)
	 * 
	 * @return
	 */
	public synchronized int getEmployeeCount() {
		return map.size();
	}
}
