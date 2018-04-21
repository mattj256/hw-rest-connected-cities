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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NodeEntity
public class City {

  private static final Logger log = LoggerFactory.getLogger(City.class);

  @Id @GeneratedValue Long id;
  private String name;
  private Integer partition;

  @Relationship(type = "ROAD", direction = Relationship.UNDIRECTED)
  Set<City> neighbors = new HashSet<>();

  private City() {
    // Empty constructor required as of Neo4j API 2.0.5
  };

  public City(String name) {
    this.name = name;
  }

  /*
  // TODO: remove
  public boolean hasNeighbor(City neighbor) {
    // TODO: remove
    // log.warn("hasNeighbor " + this + " " + neighbor + neighbors.contains(neighbor));
    return neighbors.contains(neighbor);
  }
  */

  public boolean isConnected(City neighbor) {
    return this.partition != null && this.partition.equals(neighbor.partition);
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

  public Integer getPartition() { return partition; }

  @Override
  public String toString() {
    return "[" + getClass().getName() + ": " + name + "]";
  }
}
