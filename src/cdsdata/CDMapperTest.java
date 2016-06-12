package cdsdata;

import java.util.ArrayList;


import cds.CD;

public class CDMapperTest {

  /**
   * Leest cds  (nog zonder tracks) uit de database en schrijft deze naar  standaarduitvoer
   * Leest tracks van de eerste cd en schrijft deze naar standaarduitvoer 
   * @param args nvt
   */
  public static void main(String[] args) throws CDException {
    CDMapper mapper = new CDMapper();
    // lees cds uit de database en toon ze
    ArrayList<CD> cds = mapper.leesAlleCDs();
    for (CD cd: cds){
      System.out.println("" + cd.toString());
    }   
    // lees tracks van eerste cd en toon dan de cd, nu ook met tracks. 
    CD cd = cds.get(0);  
    cd.setTracks(mapper.leesTracks(cd));    
    System.out.println("cd met gelezen tracks: \n"+cd); 
  }
}
