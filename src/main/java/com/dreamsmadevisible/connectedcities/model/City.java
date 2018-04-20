package com.dreamsmadevisible.connectedcities.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class City {

  @Id @GeneratedValue Long id;
  private String name;

  @Relationship(type="ROAD", direction = Relationship.UNDIRECTED)
  Set<City> neighbors = new HashSet<>();

  // private City() {
  //   // Empty constructor required as of Neo4j API 2.0.5
  // };

  public City(String name) {
    this.name = name;
  }

  public void addNeighbor(City neighbor) {
    neighbors.add(neighbor);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "[" + getClass().getName() + ": " + name + "]";
  }
}
