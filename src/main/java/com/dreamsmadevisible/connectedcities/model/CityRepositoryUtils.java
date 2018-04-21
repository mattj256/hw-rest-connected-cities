package com.dreamsmadevisible.connectedcities.model;

public class CityRepositoryUtils {

  private CityRepositoryUtils() {}

  public static void addNeighbor(CityRepository cityRepository, String nameA, String nameB) {
    City cityA = getCity(cityRepository, nameA);
    City cityB = getCity(cityRepository, nameB);

    cityA.addNeighbor(cityB);
    // TODO: extra road created New York -> Boston
    cityRepository.save(cityA);
    cityRepository.save(cityB);
  }

  private static City getCity(CityRepository cityRepository, String name) {
    City city = cityRepository.findByName(name);
    return city != null ? city : new City(name);
  }
}
