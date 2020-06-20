package com.modiamedia.model.vo;

/**
 * employee value object class will use it in application
 * 
 * @author Niveen Magdy
 * @version 15-11-2017
 */
public class EmployeeVO {

	private String id;
	private EmployeeData employeeData;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public EmployeeData getEmployeeData() {
		if (null == employeeData) {
			employeeData = new EmployeeData();
		}
		return employeeData;
	}

	public void setEmployeeData(EmployeeData employeeData) {
		this.employeeData = employeeData;
	}

	@Override
	public String toString() {
		return "ID : " + getId() + ", Name :" + getEmployeeData().getName() + " , Designation :" + getEmployeeData().getDesignation() + " , Salary :"
				+ getEmployeeData().getSalary();
	}
}
