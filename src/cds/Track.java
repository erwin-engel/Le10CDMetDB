package cds;

/**
 * 
 * Deze klasse beheert de gegevens van een track (nr, titel, artiest) op een CD.
 * 
 * @author Open Univesiteit Nederland
 * 
 */
public class Track {

  private int nr = 0;
  private String titel = null;
  private String artiest = null;

  /**
   * Creeert een nieuwe track.
   * @param nr  het volgnummer van de track
   * @param titel  de titel van de track
   * @param artiest  de uitvoerende artiest
   */
  public Track(int nr, String titel, String artiest) {
    this.nr = nr;
    this.titel = titel;
    this.artiest = artiest;
  }

  public int getNr() {
    return nr;
  }

  public void setNr(int nr) {
    this.nr = nr;
  }

  public String getTitel() {
    return titel;
  }

  public void setTitel(String titel) {
    this.titel = titel;
  }

  public String getArtiest() {
    return artiest;
  }

  public void setArtiest(String artiest) {
    this.artiest = artiest;
  }
  
  /**
   * Geeft de String representatie
   */
  public String toString() {
    return nr + " " + titel + " " + artiest;
  }

}
