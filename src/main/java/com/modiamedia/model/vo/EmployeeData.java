package com.modiamedia.model.vo;

/**
 * will be used in saving and retrieving employee Data
 * 
 * @author Niveen Magdy
 * @version 15-11-2017
 */
public class EmployeeData {
	private String name;
	private String designation;
	private String salary;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the salary
	 */
	public String getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return getName() + " ,Designation:" + getDesignation() + " ,Salary:" + getSalary();
	}

}
