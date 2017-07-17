package com.newscorp.project.util;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.newscorp.project.dto.ControllerRequest;
import com.newscorp.project.dto.OrderDTO;

/**
 * This is a utility validator class to validate data coming in incoming
 * request.
 * 
 * @author mazharshaikh
 *
 */
public class ControllerRequestValidator implements Validator {

	/**
	 * This Validator validates *just* ControllerRequest instances
	 */
	public boolean supports(Class clazz) {
		return ControllerRequest.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {

		ControllerRequest controllerRequest = (ControllerRequest) obj;

		if (controllerRequest.getRequestData() instanceof OrderDTO) {

			OrderDTO order = (OrderDTO) controllerRequest.getRequestData();

			if (StringUtils.isEmpty(order.getOrderDetails())) {
				e.rejectValue("orderDetails", "orderDetails can't be empty");
			} else if (StringUtils.isEmpty(order.getNumberAllowed())) {
				e.rejectValue("numberAllowed", "numberAllowed can't be empty");
			} else if (StringUtils.isEmpty(order.getStrToBeReplaced())) {
				e.rejectValue("strToBeReplaced", "strToBeReplaced can't be empty");
			}
		}
	}
}
