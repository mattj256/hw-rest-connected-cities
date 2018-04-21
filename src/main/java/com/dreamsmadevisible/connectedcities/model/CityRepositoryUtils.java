package com.dreamsmadevisible.connectedcities.model;

public class CityRepositoryUtils {

  private CityRepositoryUtils() {}

  public static void addNeighbor(CityRepository cityRepository, String nameA, String nameB) {
    City cityA = getCity(cityRepository, nameA, true);
    City cityB = getCity(cityRepository, nameB, true);

    cityA.addNeighbor(cityB);
    // TODO: extra road created New York -> Boston
    cityRepository.save(cityA);
    cityRepository.save(cityB);
  }

  public static City getCity(CityRepository cityRepository, String name, boolean saveIfNew) {
    City city = cityRepository.findByName(name);
    /*
    if (city == null) {
      city = new City(name);
      if (saveIfNew) cityRepository.save(city);
    }
    return city;
    */
    return city != null ? city : new City(name);
  }
}
