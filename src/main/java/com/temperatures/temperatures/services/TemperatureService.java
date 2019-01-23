package com.temperatures.temperatures.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.temperatures.temperatures.entities.Temperature;

/**
 * Service for the Temperature API
 * @author Cody
 *
 */
@Service
public class TemperatureService {

	/**
	 * Creates duplicates of each Temperature object and converts their value to Fahrenheit. 
	 * @param celsiusTemps The temperatures (in C) from the database
	 * @return A new list of temperatures including the originals in Celsius and the newly created Fahrenheit temperatures 
	 */
	public Iterable<Temperature> addFahrenheitTemps(Iterable<Temperature> celsiusTemps) {
		List<Temperature> results = new ArrayList<>();
		for(Temperature cTemp : celsiusTemps) {
			Temperature fTemp = new Temperature();
			fTemp.setId(cTemp.getId());
			fTemp.setValue((cTemp.getValue() * 9 / 5) + 32);
			fTemp.setCreatedDate(cTemp.getCreatedDate());
			fTemp.setUpdatedDate(cTemp.getUpdatedDate());
			fTemp.setUnit("Fahrenheit");
			results.add(cTemp);
			results.add(fTemp);
		}
		return results;
	}
}
