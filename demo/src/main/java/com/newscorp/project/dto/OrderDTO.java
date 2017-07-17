package com.newscorp.project.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * This class will hold order details
 * 
 * @author mazharshaikh
 *
 */
public class OrderDTO {

	@NotBlank(message = "orderDetails can't empty!")
	private String orderDetails;

	@NotBlank(message = "numberAllowed can't empty!")
	private int numberAllowed;

	@NotBlank(message = "strToBeReplaced can't empty!")
	private String strToBeReplaced;

	public String getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}

	public int getNumberAllowed() {
		return numberAllowed;
	}

	public void setNumberAllowed(int numberAllowed) {
		this.numberAllowed = numberAllowed;
	}

	public String getStrToBeReplaced() {
		return strToBeReplaced;
	}

	public void setStrToBeReplaced(String strToBeReplaced) {
		this.strToBeReplaced = strToBeReplaced;
	}

	@Override
	public String toString() {
		return "OrderDTO [orderDetails=" + orderDetails + ", numberAllowed=" + numberAllowed + ", strToBeReplaced="
				+ strToBeReplaced + "]";
	}
}
