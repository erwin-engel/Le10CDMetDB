package cdsdata;
/**
 * Exceptionklasse voor het signaleren van fouten bij communicatie met de CD-database
 * @author Medewerker OUNL
 *
 */
public class CDException extends Exception {
  
  public CDException(){
    super();
  }
  
  public CDException(String s){
    super(s);
  }

}
