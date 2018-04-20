package com.dreamsmadevisible.connectedcities;

public class ConnectedCitiesResponse {

  // private final long id;
  private final String response;

  public ConnectedCitiesResponse(/* long id, */ String response) {
    // this.id = id;
    this.response = response;
  }

  /*
  public long getId() {
    return id;
  }
  */

  public String getResponse() {
    return response;
  }
}
