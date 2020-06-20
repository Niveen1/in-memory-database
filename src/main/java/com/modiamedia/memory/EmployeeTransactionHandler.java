package com.modiamedia.memory;

import java.util.Scanner;

import com.modiamedia.businessservice.EmployeeService;
import com.modiamedia.model.vo.EmployeeVO;

/**
 * EmployeeTransactionHandler is responsible for read and Write to console , will delegate also to valid service
 * 
 * 
 * 
 * @author Niveen Magdy
 * @version 15-11-2017
 */
public class EmployeeTransactionHandler {
	private EmployeeService employeeService;

	private static final String DASH = "-";
	private static final String DASH_NEW_FORMAT = "‐";
	private static final String WHITE_SPACE = "\\s";
	private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";

	public EmployeeTransactionHandler(EmployeeService employeeService) {
		// constructor injection
		this.employeeService = employeeService;
	}

	public void execute() {
		boolean exist = false;
		while (!exist) {
			// create a scanner so we can read the command-line input
			Scanner scanner = new Scanner(System.in);

			// get their input as a String
			String userInput = scanner.nextLine();
			String[] userInputs = userInput.split(WHITE_SPACE);// splits the string based on string

			// if user didn't enter any input or it is empty print invalid operation without closing the app
			if (userInputs.length == 0 || null == userInputs[0] || userInputs[0].isEmpty()) {
				System.out.println("Invalid operation.");
			} else {
				// get operation
				OperationEnum operation = parseOperation(userInputs);

				String remainCommand = getRemainCommand(userInputs).toString();

				// before checking on operation type check if user entered quit
				if (null != operation) {
					if (operation.equals(OperationEnum.QUIT)) {
						exist = true;
						scanner.close();
						continue;
					} else {
						if (null != remainCommand) {
							// if operation is not quit execute the operation
							String result = executeOperation(operation, remainCommand);
							System.out.println(result + "\n");
						} else {
							System.out.println("Invalid Command.");
						}
					}
				}

			}
		}
	}

	private String executeOperation(OperationEnum operation, String command) {
		String result = null;

		switch (operation) {
		case ADD:
			result = addEmployee(command);
			break;

		case DEL:
			result = removeEmployee(command);
			break;

		case UPDATE:
			result = updateEmployee(command);
			break;

		case PRINT:
			result = printEmployee(command);
			break;

		case PRINTALL:
			result = printAllEmployees(command);
			break;

		default:
			System.out.println("operation " + operation + "' is not supported.");
		}
		return result;
	}

	// ========================================helper methods===================================

	private StringBuilder getRemainCommand(String[] userInputs) {
		StringBuilder remainCommand = new StringBuilder();
		for (int itr = 1; itr < userInputs.length; itr++) {
			if (null == remainCommand) {
				remainCommand.append(userInputs[itr]);
			} else {
				remainCommand.append(" " + userInputs[itr]);
			}
		}

		return remainCommand;
	}

	private OperationEnum parseOperation(String[] userInputs) {
		// read user input and map first element to operation
		OperationEnum operation = null;
		try {
			operation = OperationEnum.valueOf(userInputs[0].toUpperCase());
		} catch (IllegalArgumentException ex) {
			System.out.println("Invalid operation.");
		}

		return operation;
	}

	private String addEmployee(String command) {
		String result = null;

		EmployeeVO employeeVO = new EmployeeVO();
		if (null == command || command.isEmpty()) {
			result = "Invalid Employee Data";
		} else {
			// split user input with -
			String[] employeeData = splitUserInputWithDash(command);
			if (employeeData.length < 4) {
				result = "Invalid Employee Data";
			} else {
				employeeVO.setId(employeeData[0]);

				String name = employeeData[1];
				String designation = employeeData[2];
				String salary = employeeData[3];

				employeeVO.getEmployeeData().setName(name);
				employeeVO.getEmployeeData().setDesignation(designation);
				employeeVO.getEmployeeData().setSalary(salary);

				result = getEmployeeService().addEmployee(employeeVO);
			}
		}
		return result;
	}

	private String removeEmployee(String command) {
		String result = null;

		EmployeeVO employeeVO = new EmployeeVO();
		if (null == command || command.isEmpty()) {
			result = "Invalid Employee ID";
		} else {
			// split user input with -
			String[] employeeData = splitUserInputWithDash(command);

			if (null == employeeData[0] || employeeData[0].isEmpty()) {
				result = "Invalid EmployeeID";
			} else {
				employeeVO.setId(employeeData[0]);

				result = getEmployeeService().removeEmployee(employeeVO);
			}
		}
		return result;
	}

	private String updateEmployee(String command) {
		String result = null;
		if (null == command || command.isEmpty()) {
			result = "Invalid Command";
		} else {
			// split user input with -
			String[] employeeData = splitUserInputWithDash(command);

			if (employeeData.length < 3) {
				result = "Invalid Employee Data";
			} else {
				result = getEmployeeService().updateEmployee(employeeData[0], employeeData[1], employeeData[2]);
			}
		}
		return result;
	}

	private String printEmployee(String command) {
		String result = null;
		if (null == command || command.isEmpty()) {
			result = "Invalid EmployeeID";
		} else {
			// split user input with -
			String[] employeeData = splitUserInputWithDash(command);

			if (employeeData.length == 0) {
				result = "Invalid EmployeeID";
			} else {
				result = getEmployeeService().getEmployeeById(employeeData[0]);
			}
		}
		return result;
	}

	private String printAllEmployees(String command) {
		String result = null;
		// split user input with -
		OrderEnum order = OrderEnum.ASC;
		if (null != command) {
			// split user input with -
			String[] employeeData = splitUserInputWithDash(command);

			if (employeeData.length > 1) {
				order = OrderEnum.valueOf(employeeData[0].toUpperCase());
			}
		}
		result = getEmployeeService().getAllEmployees(order);
		if (null == result) {
			result = "No Employees.";
		}
		return result;
	}

	private String[] splitUserInputWithDash(String command) {
		// split user input with -
		String[] employeeData = command.split(DASH);
		if (employeeData.length == 1) {
			employeeData = command.split(DASH_NEW_FORMAT);
		}
		return employeeData;
	}

	// =======================================Employee validation=============================
	private boolean isValidStringInput(String input) {
		boolean valid = false;
		if (null != input) {
			valid = true;
		}
		return valid;
	}

	private boolean isValidSalaryInput(String salary) {
		boolean valid = false;
		if (null != salary) {
			valid = true;
		}
		return valid;
	}

	// =======================================setter and getter=================================

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

}
