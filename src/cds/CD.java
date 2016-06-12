package cds;

import java.util.ArrayList;

/**
 * Deze klasse beheert de gegevens (code, titel, label, jaar, genre, tracks) van
 * een CD.
 * @author Open Universiteit Nederland
 */
public class CD {

  private String code = null;
  private String titel = null;
  private String label = null;
  private int jaar = 0;
  private String genre = null;
  private ArrayList<Track> tracks = null;

  /**
   * Creert een nieuwe CD 
   * @param code  de code van de cd
   * @param titel  de titel van de cd
   * @param label  het label waarop de cd is verschenen
   * @param jaar  het jaar van verschijning
   * @param genre  het genre waartoe de muziek op de cd behoort
   */
  public CD(String code, String titel, String label, int jaar, String genre) {
    this.code = code;
    this.titel = titel;
    this.label = label;
    this.jaar = jaar;
    this.genre = genre;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getTitel() {
    return titel;
  }

  public void setTitel(String titel) {
    this.titel = titel;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public int getJaar() {
    return jaar;
  }

  public void setJaar(int jaar) {
    this.jaar = jaar;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public ArrayList<Track> getTracks() {
    return tracks;
  }

  public void setTracks(ArrayList<Track> tracks) {
    this.tracks = tracks;
  }

  /**
   * Geeft een string representatie van een cd
   */
  public String toString() {
    String res = code + " " + titel + " " + label + " " + genre + " \n" + " *** tracks **** \n";
//    res = res + " *** tracks **** \n";
    if (tracks != null) {
      for (Track track : tracks) {
        res = res + track + "\n";
      }
    }
    return res;
  }
}
