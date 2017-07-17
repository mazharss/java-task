package com.newscorp.project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.newscorp.project.dto.ControllerResponse;
import com.newscorp.project.dto.ErrorDTO;
import com.newscorp.project.dto.OrderDTO;

/**
 * This is a utility service that will provide helper methods to REST API's
 * developed in controller package
 * 
 * @author mazharshaikh
 *
 */
@Service
public class UtilityService {

	/**
	 * This method
	 * 
	 * @param accepts
	 *            order details of type
	 *            {@link com.newscorp.project.dto.OrderDTO}
	 * @return
	 */
	public String truncate(OrderDTO order) {

		String strToBeReplaced = order.getStrToBeReplaced();
		int numberAllowed = order.getNumberAllowed();
		String orderDetails = order.getOrderDetails();

		// In this scenario we can't truncate order details
		if (strToBeReplaced.length() > numberAllowed || orderDetails.length() <= numberAllowed) {
			return orderDetails;
		} else if (strToBeReplaced.length() + 2 <= numberAllowed && orderDetails.length() > numberAllowed) {
			int modifiedLength = numberAllowed - strToBeReplaced.length();
			String str1 = orderDetails.substring(0, modifiedLength / 2);
			String str2 = orderDetails.substring(orderDetails.length() - (modifiedLength / 2), orderDetails.length());

			StringBuilder truncatedString = new StringBuilder();
			truncatedString.append(str1);
			truncatedString.append(strToBeReplaced);
			truncatedString.append(str2);

			return truncatedString.toString();
		}

		return orderDetails;
	}

	public ControllerResponse buildControllerResponse(Object response, int statusCode) {
		ControllerResponse controllerResponse = new ControllerResponse();

		if (statusCode == 200) {
			controllerResponse.setStatusCode(statusCode);
			controllerResponse.setSuccessResult(response);
		} else {

			// If status code is not 200 then there is some issue
			// Analyze exception object and build error objects
			if (statusCode == -1) {
				List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
				ErrorDTO error = new ErrorDTO();
				error.setCode(7001);
				error.setText("Not able to connect to database");
				error.setHints("[Please check that the database status, it may not be up and running");
				error.setDate(new Date());
				errors.add(error);

				controllerResponse.setStatusCode(500);
				controllerResponse.setErrors(errors);
			} else {
				List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
				ErrorDTO error = new ErrorDTO();
				error.setCode(8001);
				error.setText("NumberAllowed parameter is not integer");
				error.setHints(
						"[Please check that the user has provided a numeric value for \"numberAllowed\" parameter");
				error.setDate(new Date());
				errors.add(error);

				controllerResponse.setStatusCode(400);
				controllerResponse.setErrors(errors);
			}
		}

		return controllerResponse;
	}

}
