package com.modiamedia.memory;

/**
 * Operations enum: <BR>
 * ADD<BR>
 * DEL<BR>
 * UPDATE<BR>
 * PRINT<BR>
 * PRINT_ALL<BR>
 * QUIT
 * 
 * @author Niveen Magdy
 * @version 15-11-2017
 */
public enum OperationEnum {
	ADD("ADD"), DEL("DEL"), UPDATE("UPDATE"), PRINT("PRINT"), PRINTALL("PRINT_ALL"), QUIT("QUIT");

	private final String operation;

	/**
	 * @param operation
	 */
	private OperationEnum(final String operation) {
		this.operation = operation;
	}

	public String getValue() {
		return operation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return operation;
	}
}
