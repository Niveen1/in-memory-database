package com.modiamedia.memory;

/**
 * Order enum: <BR>
 * ASC<BR>
 * DESC
 * 
 * @author Niveen Magdy
 * @version 15-11-2017
 */
public enum OrderEnum {
	ASC("ASC"), DESC("DESC");

	private final String order;

	/**
	 * @param order
	 */
	private OrderEnum(final String order) {
		this.order = order;
	}

	public String getValue() {
		return order;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return order;
	}
}
