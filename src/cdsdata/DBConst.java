package cdsdata;
/**
 * Klasse met constanten voor het gebruikte DBMS 
 * @author Medewerker OUNL
 *
 */
public class DBConst {
   // vul in PADNAAM nog de juiste padnaam in naar de map met projecten
	public static final String PADNAAM = "cds";
	public static final String DRIVERNAAM = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost/"+ PADNAAM +"?useSSL=false"; 
	public static final String GEBRUIKERSNAAM = "erwin";
	public static final String WACHTWOORD = "mysqltest";

}
