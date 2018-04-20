package com.dreamsmadevisible.connectedcities.model;

import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {

    City findByName(String name);
}
