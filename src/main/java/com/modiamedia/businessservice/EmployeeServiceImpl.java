package com.modiamedia.businessservice;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.modiamedia.memory.OrderEnum;
import com.modiamedia.model.vo.EmployeeData;
import com.modiamedia.model.vo.EmployeeVO;

/**
 * will implement EmployeeService methods , it will delegate to EmployeeManager <br>
 * responsible for creating result value
 * 
 * @author Niveen Magdy
 * @version 15-11-2017
 */
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeManager employeeManager;

	public String addEmployee(EmployeeVO employeeVO) {

		String result = null;
		int totalEmployees = 0;
		try {
			totalEmployees = getEmployeeManager().addEmployee(employeeVO);

			result = "Employee '" + employeeVO.getEmployeeData().getName() + "' added successfully. Total no of employees = " + totalEmployees;
		} catch (IllegalStateException exception) {
			totalEmployees = getEmployeeManager().countEmployee();
			result = exception.getMessage() + " Total no of employees = " + totalEmployees;
		}
		return result;
	}

	public String removeEmployee(EmployeeVO employeeVO) {

		String result = null;
		int totalEmployees = 0;
		try {
			getEmployeeManager().removeEmployee(employeeVO);
			result = "Employee '" + employeeVO.getId() + "' deleted successfully.";

		} catch (IllegalStateException exception) {
			result = exception.getMessage();

		} finally {
			totalEmployees = getEmployeeManager().countEmployee();
			result += " Total no of employees = " + totalEmployees;

		}
		return result;
	}

	public String updateEmployee(String employeeId, String key, String newValue) {

		String result = null;
		try {
			EmployeeData employeeData = getEmployeeManager().updateEmployee(employeeId, key, newValue);

			result = "Employee '" + employeeId + "' updated. Name:" + employeeData.toString();

		} catch (IllegalStateException exception) {
			result = "Failed to update Employee '" + employeeId + "' due to " + exception.getMessage();

		}
		return result;
	}

	public String getEmployeeById(String employeeId) {
		String result = null;
		try {
			// get Employee with given ID
			EmployeeData employeeData = getEmployeeManager().getEmployeeById(employeeId);

			if (null != employeeData) {
				result = employeeData.toString();
			} else {
				result = "no user found woth ID '" + employeeId + "'";
			}
		} catch (IllegalStateException exception) {
			result = "Failed to get Employee '" + employeeId + "' due to" + exception.getMessage();
		}
		return result;
	}

	public String getAllEmployees(OrderEnum order) {
		String result = null;

		Map<String, EmployeeData> employees = getEmployeeManager().getAllEmployees();
		Map<String, EmployeeData> sortEmployees = sortByComparator(employees, order);
		for (Entry<String, EmployeeData> entry : sortEmployees.entrySet()) {

			EmployeeData employeeData = entry.getValue();
			if (null == result) {
				result = employeeData.getName() + " :" + employeeData.getDesignation() + "\n";
			} else {
				result += employeeData.getName() + " :" + employeeData.getDesignation() + "\n";
			}
		}

		return result;
	}

	// =====================================helper methods=============================================

	private Map<String, EmployeeData> sortByComparator(Map<String, EmployeeData> unsortMap, final OrderEnum order) {

		List<Entry<String, EmployeeData>> list = new LinkedList<Entry<String, EmployeeData>>(unsortMap.entrySet());

		// Sorting the list based on values
		Collections.sort(list, new Comparator<Entry<String, EmployeeData>>() {
			public int compare(Entry<String, EmployeeData> o1, Entry<String, EmployeeData> o2) {
				if (order.equals(OrderEnum.ASC)) {
					return o1.getValue().getName().compareTo(o2.getValue().getName());
				} else {
					return o2.getValue().getName().compareTo(o1.getValue().getName());

				}
			}
		});

		// Maintaining insertion order with the help of LinkedList
		Map<String, EmployeeData> sortedMap = new LinkedHashMap<String, EmployeeData>();
		for (Entry<String, EmployeeData> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	// ========================================setter and getter============================================

	public EmployeeManager getEmployeeManager() {
		// in future will use spring to handle DI
		if (null == employeeManager) {
			employeeManager = new EmployeeManagerImpl();
		}
		return employeeManager;
	}

	public void setEmployeeManager(EmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}

}
