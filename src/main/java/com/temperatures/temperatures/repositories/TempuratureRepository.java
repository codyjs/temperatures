package com.temperatures.temperatures.repositories;

import org.springframework.data.repository.CrudRepository;

import com.temperatures.temperatures.entities.Temperature;

/**
 * JPA repository for CRUD operations on Temperature objects.
 * @author Cody
 *
 */
public interface TempuratureRepository extends CrudRepository<Temperature, Long> {

}
