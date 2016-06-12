package cdsdata;


import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.io.PrintWriter;

import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import cds.CD;
import cds.Track;

/**
 * Deze klasse is verantwoordelijk voor de communicatie met de database 
 * @author Open Universiteit Nederland
 * 
 */
public class CDMapper {
	private Connection con = null;
	private PreparedStatement prepStatSelCd = null;
	private PreparedStatement prepStatSelCdTrack = null;
	
	
  /**
   * Maakt verbinding met de database en initialiseert
   * PreparedStatement-objecten.
   * @throws CDException
   * @throws  
   */
  public CDMapper() throws CDException {
  	maakDBVerbinding();
  	initPrepStatements();
  }
  /**
   * Maak verbinding met database
   * @throws CDException
   */
  private void maakDBVerbinding()throws CDException{
   	// logger maken, driver laden, connectie maken
  	try {
  		DriverManager.setLogWriter(new PrintWriter(System.out));
			Class.forName(DBConst.DRIVERNAAM);
			con = (Connection) DriverManager.getConnection(DBConst.URL, DBConst.GEBRUIKERSNAAM,
					DBConst.WACHTWOORD);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CDException("fout bij lade driver");
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new CDException("fout bij maken connectie");
		}
  }
  
  private void initPrepStatements()throws CDException{
  	// tbv selecteren alle cds
  	try{
  	prepStatSelCd = con.prepareStatement("Select * from cd");
  	prepStatSelCdTrack = con.prepareStatement("SELECT * FROm Track WHERE cd = ?");
  	
  	}  catch (SQLException e) {
  		sluitVerbinding();
  		e.printStackTrace();
			throw new CDException("fout bij maken prepared statement");
  	}
  	
  	try {
			DatabaseMetaData  dbmd = con.getMetaData();
			System.out.println(dbmd.getDriverName() + "\n");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	
  }
  
  /**
   * Sluit de verbinding met de database.
   * @throws CDException 
   */
  public void sluitVerbinding()  {
  	if (con != null){
	  	try {
				con.close();
			}
	  	catch (SQLException e) {
	  		e.printStackTrace();
			}
  	}
  }

  /**
   * Leest alle cd 's uit de database. De tracks bij een cd worden nog niet
   * gelezen. 
   * @return alle CD-objecten zonder de bijbehorende tracks
   * @throws CDException als er iets fout gaat
   */
  public ArrayList<CD> leesAlleCDs() throws CDException {
  	ArrayList <CD> CdLijst = new ArrayList<CD>();
  	try {
			ResultSet resultSet = prepStatSelCd.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++){
	//		System.out.println(rsmd.getColumnName(i) + "\n");
				System.out.print(rsmd.getColumnName(i) + " / " );
			}
			while (resultSet.next()){
				String code = resultSet.getString(1);
				String titel = resultSet.getString(2);
				String label = resultSet.getString(3);
				int jaar = Integer.parseInt(resultSet.getString(4));
				String genre = resultSet.getString(5);
				CD cd = new CD(code, titel, label, jaar, genre);
				System.out.println(code);
				CdLijst.add(cd);
			}
  	}
		catch (SQLException e) {
  		sluitVerbinding();
  		e.printStackTrace();
			throw new CDException("fout bij selecteren CDs");
		}
    return CdLijst;
  }

  /**
   * Leest tracks bij een gegeven cd
   * @param cd  de cd
   * @return de tracks bij de cd
   * @throws CDException
   */
  public ArrayList<Track> leesTracks(CD cd) throws CDException {
  	ArrayList<Track> trackLijst = new ArrayList<Track>();
  	try {
  		System.out.println(cd.getCode());
  		prepStatSelCdTrack.setString(1, cd.getCode());
			ResultSet resultSet = prepStatSelCdTrack.executeQuery();
			while (resultSet.next()){
				int nr = Integer.parseInt(resultSet.getString("nr"));
				String titel = resultSet.getString("titel");
				String artiest = resultSet.getString("artiest");
				Track track = new Track(nr, titel, artiest);
				trackLijst.add(track);
			}
  	}
		catch (SQLException e) {
  		sluitVerbinding();
  		e.printStackTrace();
			throw new CDException("fout bij selecteren tracks");
		}
    return trackLijst;
  }
}
