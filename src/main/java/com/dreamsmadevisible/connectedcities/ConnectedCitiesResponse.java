package com.dreamsmadevisible.connectedcities;

public class ConnectedCitiesResponse {

  private final long id;
  private final String content;

  public ConnectedCitiesResponse(long id, String content) {
    this.id = id;
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }
}
