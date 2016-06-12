package cds;

import java.util.ArrayList;

import cdsdata.CDException;
import cdsdata.CDMapper;

public class CDCollectie {

  private ArrayList<CD> cds = null;
  private CDMapper mapper = null;

  /**
   * Creeert een cdcollectie aan de hand van de cds in de database. De
   * collectie wordt nog niet volledig geïnstantieerd: de tracks bij de cd's
   * worden nog niet gelezen 
   * @param url  de url
   * @throws CDException als er iets fout gaat.
   */
  public CDCollectie() throws CDException {
    mapper = new CDMapper();
    cds = mapper.leesAlleCDs();
  }
  
  /**
   * Geeft alle cd's uit de collectie
   * @return alle cd's
   */
  public ArrayList<CD> getCds(){
    return cds;
  }

  /**
   * Geeft de tracks bij een gegeven cd titel of null als de titel niet voorkomt. 
   * Bijzonderheid: als er meerdere cd's met dezelfde titel
   * zijn, worden de tracks van de eerste cd met de gegeven titel die gevonden
   * wordt gegeven.
   * @param titel  de gegeven titel
   * @return de tracks bij de gegeven titel, null als de titel niet voorkomt.
   * @throws CDException als er iets fout gaat
   */
  public ArrayList<Track> getTracks(String titel) throws CDException {
    CD cd = getCD(titel);
    if (cd != null) {
      // Als de tracks nog niet zijn ingelezen, doe dat dan nu
      if (cd.getTracks() == null) {
        cd.setTracks(mapper.leesTracks(cd));
      }
      return cd.getTracks();
    }
    else
      return null;
  }

  /**
   * Geeft de eerste CD in de collectie met de gegeven titel, null als er geen
   * CD met de titel voorkomt.
   * @param titel de titel
   * @return de eerste CD in de collectie met de gegeven titel
   */
  private CD getCD(String titel) {
    for (CD cd : cds) {
      if (cd.getTitel().equals(titel)) {
        return cd;
      }
    }
    return null;
  }

  /**
   * sluit de verbinding met de database
   */
  public void sluitAf() {
    if (mapper != null) {
      mapper.sluitVerbinding();
    }
  }
}
