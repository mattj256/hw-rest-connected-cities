package com.dreamsmadevisible.connectedcities;

import com.dreamsmadevisible.connectedcities.model.City;
import com.dreamsmadevisible.connectedcities.model.CityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner demo(CityRepository cityRepository) {
    return args -> {

      cityRepository.deleteAll();

      addNeighbor(cityRepository, "Boston", "New York");
      addNeighbor(cityRepository, "Philadelphia", "Newark");
      addNeighbor(cityRepository, "Newark", "Boston");
      addNeighbor(cityRepository, "Trenton", "Albany");
    };
  }

  private static void addNeighbor(CityRepository cityRepository, String nameA, String nameB) {
    City cityA = getCity(cityRepository, nameA, true);
    City cityB = getCity(cityRepository, nameB, true);

    cityA.addNeighbor(cityB);
    // TODO: extra road created New York -> Boston
    cityRepository.save(cityA);
    cityRepository.save(cityB);
  }

  private static City getCity(CityRepository cityRepository, String name, boolean saveIfNew) {
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
