package com.newscorp.project.dto;

/**
 * This is the request object that will be passed with every API call.
 * 
 * @author mazharshaikh
 *
 */
public class ControllerRequest {

	/**
	 * Contains the request DTO/Bean details for all the services. This
	 * comprises of
	 * <ol>
	 * <li>orderDetails</li>
	 * <li>number of characters allowed in order details</li>
	 * <li>string that will be replaced in order details data</li>
	 * </ol>
	 */
	private Object requestData;

	/**
	 * This will be used so that the user is not required to pass credentials
	 * always. Any client requiring access to service should use this token
	 * while communicating. I have not implemented any authorization mechanisms
	 * though. We could implement following authorization techniques-
	 * <ol>
	 * <li>Basic</li>
	 * <li>JWT</li>
	 * <li>OAuths2</li>
	 * </ol>
	 */
	private String samlToken;

	public Object getRequestData() {
		return requestData;
	}

	public void setRequestData(Object requestData) {
		this.requestData = requestData;
	}

	public String getSamlToken() {
		return samlToken;
	}

	public void setSamlToken(String samlToken) {
		this.samlToken = samlToken;
	}
}
