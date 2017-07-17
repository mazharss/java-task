package com.newscorp.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newscorp.project.dto.ControllerRequest;
import com.newscorp.project.dto.ControllerResponse;
import com.newscorp.project.dto.OrderDTO;
import com.newscorp.project.service.UtilityService;
import com.newscorp.project.util.IPathConstants;

/**
 * This is the controller class that will expose the API. As of now, I have not
 * secured the API. It will be preferred to authenticate and authorize the user
 * accessing the resource
 * 
 * @author mazharshaikh
 *
 */
@RestController
// @PreAuthorize("hasRole('ADMIN')")
public class SubscriptionController {

	@Autowired
	UtilityService utilityService;

	/**
	 * This method exposes the subscription REST API to outside world
	 * 
	 * @param controllerRequest
	 *            of type {@link com.newscorp.project.dto.ControllerRequest}.
	 * @param errors
	 *            of type {@link org.springframework.validation.Errors} will
	 *            hold validation errors if issues with incoming request
	 * @return controllerResponse of type
	 *         {@link com.newscorp.project.dto.ControllerResponse}. This
	 *         comprises of
	 *         <ol>
	 *         <li>status code - signifying the outcome of API call</li>
	 *         <li>successfully truncated order details</li>
	 *         <li>errors if any, please note this will be populated only in
	 *         case of errors</li>
	 *         </ol>
	 */
	@RequestMapping(path = IPathConstants.PATH_SUBSCRIPTION, method = RequestMethod.PATCH, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public ControllerResponse subscribe(@Valid @RequestBody ControllerRequest controllerRequest, Errors errors) {

		ControllerResponse controllerResponse = null;
		ObjectMapper objectMapper = new ObjectMapper();
		OrderDTO order = null;

		try {

			if (errors.hasErrors()) {
				throw new Exception("Bad request");
			}

			// This is to ensure that the service does not fail if client sends
			// invalid data in request
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			order = objectMapper.convertValue(controllerRequest.getRequestData(), OrderDTO.class);
			Object truncatedOrderStr = utilityService.truncate(order);
			controllerResponse = utilityService.buildControllerResponse(truncatedOrderStr, 200);

		} catch (Exception exception) {
			// If this condition is true then it is bad request
			if (errors.hasErrors()) {
				controllerResponse = utilityService.buildControllerResponse(errors.getAllErrors(), 400);
			} else {
				// In this case there are more chances of server side error, so
				// analyze exception object and send back appropriate response.
				controllerResponse = utilityService.buildControllerResponse(exception, -1);
			}
		}
		return controllerResponse;
	}
}
