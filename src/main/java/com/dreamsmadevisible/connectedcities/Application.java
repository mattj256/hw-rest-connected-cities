package com.dreamsmadevisible.connectedcities;

import com.dreamsmadevisible.connectedcities.model.City;
import com.dreamsmadevisible.connectedcities.model.CityRepository;
import com.dreamsmadevisible.connectedcities.model.CityRepositoryUtils;
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

      CityRepositoryUtils.addNeighbor(cityRepository, "Boston", "New York");
      CityRepositoryUtils.addNeighbor(cityRepository, "Philadelphia", "Newark");
      CityRepositoryUtils.addNeighbor(cityRepository, "Newark", "Boston");
      CityRepositoryUtils.addNeighbor(cityRepository, "Trenton", "Albany");

      cityRepository.writePartitionIndices();
    };
  }
}
