package com.temperatures.temperatures.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.temperatures.temperatures.entities.ApiError;
import com.temperatures.temperatures.entities.Temperature;
import com.temperatures.temperatures.repositories.TempuratureRepository;
import com.temperatures.temperatures.services.TemperatureService;

/**
 * REST endpoints for the Temperature API
 * @author Cody
 * 
 */
@RestController
@RequestMapping(value = "/api/temperature", produces = "application/json")
public class TemperaturesController {

	@Autowired
	private TempuratureRepository tempRepo;
	
	@Autowired
	private TemperatureService tempService;

	/**
	 * Fetches all temperatures from the database.
	 * @return All temperatures in Celsius and Fahrenheit
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Iterable<Temperature> getTemps() {
		Iterable<Temperature> celsiusTemps = this.tempRepo.findAll();
		return this.tempService.addFahrenheitTemps(celsiusTemps);
	}

	/**
	 * Inserts a new temperature into the database.
	 * @param response The HTTP response
	 * @param temp The temperature to create
	 * @return The newly created temperature
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Temperature createTemp(HttpServletResponse response, @RequestBody Temperature temp) {
		temp.setCreatedDate(new Date());
		temp = this.tempRepo.save(temp);
		response.setStatus(HttpServletResponse.SC_CREATED);
		return temp;
	}

	/**
	 * Updates an existing temperature in the database. If the temperature doesn't exist, returns a 404 error.
	 * @param updatedTemp The temperature to update
	 * @param tempId The id of the temperature to update
	 * @return The newly updated temperature
	 */
	@RequestMapping(value = "/{tempId}", method = RequestMethod.PUT)
	public Temperature updateTemp(@RequestBody Temperature updatedTemp, @PathVariable long tempId) {
		Temperature temp = this.tempRepo.findOne(tempId);
		if (temp == null)
			throw new EmptyResultDataAccessException(String.format("No class com.temperatures.temperatures.entities.Temperature entity with id %s exists!", tempId), 0);
		temp.setValue(updatedTemp.getValue());
		temp.setUpdatedDate(new Date());
		temp = this.tempRepo.save(temp);
		return temp;
	}

	/**
	 * Deletes a temperature from the database. If the temperature doesn't exist, returns a 404 error.
	 * @param tempId The id of the temperature to delete
	 */
	@RequestMapping(value = "/{tempId}", method = RequestMethod.DELETE)
	public void deleteTemp(@PathVariable long tempId) {
		this.tempRepo.delete(tempId);
	}

	/**
	 * Handles an EmptyResultDataAccessException by returning a 404 error.
	 * @param response The http response
	 * @param ex The exception handled
	 * @return An ApiError with a descriptive message and a 404 error code
	 */
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ApiError handleEmptyResultDataAccessException(HttpServletResponse response,
			EmptyResultDataAccessException ex) {
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return new ApiError(ex.getMessage(), HttpServletResponse.SC_NOT_FOUND);
	}
}
