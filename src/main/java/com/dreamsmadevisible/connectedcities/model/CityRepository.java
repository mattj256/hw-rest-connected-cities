package com.dreamsmadevisible.connectedcities.model;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {
    City findByName(String name);

    /**
     * Partitions the graph into connected components, then stores the
     * partition index in the "partition" property.
     */
    @Query("CALL algo.unionFind('City', 'ROAD', " +
        "{write:true, partitionProperty:'partition'}) YIELD nodes")
    int writePartitionIndices();
}
