package cdsgui;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;

import cds.CD;
import cds.CDCollectie;
import cds.Track;
import cdsdata.CDException;

import javax.swing.JButton;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**
 * Gebruikersinterface voor de CD-collectie
 * @author Mederwerker OUNL
 *
 */
public class CDCollectieFrame extends JFrame {
  // De cdcollectie
  private CDCollectie cdcollectie = null;
  
  private static final long serialVersionUID = 1L;
  private JPanel jContentPane = null;

  private JScrollPane titelScrollPane = null;

  private JList titelLijst = null;

  private JScrollPane trackScrollPane = null;

  private JList trackLijst = null;

  private JButton trackKnop = null;

  /**
   * This is the default constructor
   */
  public CDCollectieFrame() {
    super();
    initialize();
    mijnInit();
  }

  /**
   * Instantieert een CDCollectie-object en 
   * haalt de cd's uit de collectie op en toont de cdtitels in een Jlist. 
   * Gebruik van een JList komt aan de orde in leereenheid 11/12) 
   */
  private void mijnInit() {
    try {
      cdcollectie = new CDCollectie();
    }
    catch (CDException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(), "CDException",
          JOptionPane.ERROR_MESSAGE);      
      System.exit(0);
    }
    DefaultListModel model = (DefaultListModel) titelLijst.getModel();
    model.removeAllElements();    
    ArrayList<CD> cds = cdcollectie.getCds();
    for (CD cd: cds){
      model.addElement(cd.getTitel());
    }
  }
  
  /**
   * Sluit de cd collectie
   */
  private void closeWindow() {
    cdcollectie.sluitAf();
  } 
  
  /**
   * Event handler: Haalt de tracks horend bij de cd met de geselecteerde titel uit de 
   * uit de collectie en toont de tracks in een JList.
   * Gebruik van een JList komt aan de orde in leereenheid 11/12)
   *  
   */
  private void trackKnopAction() {
    String titel = (String) titelLijst.getSelectedValue();    
    try {
      ArrayList<Track> tracks = cdcollectie.getTracks(titel);
      if (tracks != null) {
        DefaultListModel model = (DefaultListModel) trackLijst.getModel();
        model.removeAllElements();
        for (Track track : tracks) {
          model.addElement(track.getTitel());
        }
      }
    }
    catch (CDException e) {
      JOptionPane.showMessageDialog(this, e.getMessage(),
          "CDException", JOptionPane.ERROR_MESSAGE);
      if (cdcollectie != null) {
        cdcollectie.sluitAf();        
      }
      System.exit(0);
    }
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize() {
    this.setSize(417, 341);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(getJContentPane());
    this.setTitle("Mijn CD collectie");
    this.addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent e) {
        closeWindow();
      }
    });
  }

  /**
   * This method initializes jContentPane
   * 
   * @return javax.swing.JPanel
   */
  private JPanel getJContentPane() {
    if (jContentPane == null) {
      jContentPane = new JPanel();
      jContentPane.setLayout(null);
      jContentPane.add(getTitelScrollPane(), null);
      jContentPane.add(getTrackScrollPane(), null);
      jContentPane.add(getJButton(), null);
    }
    return jContentPane;
  }

  /**
   * This method initializes titelScrollPane
   * 
   * @return javax.swing.JScrollPane
   */
  private JScrollPane getTitelScrollPane() {
    if (titelScrollPane == null) {
      titelScrollPane = new JScrollPane();
      titelScrollPane.setBounds(new Rectangle(20, 62, 174, 235));
      titelScrollPane.setViewportView(getTitelLijst());
    }
    return titelScrollPane;
  }

  /**
   * This method initializes titelLijst
   * 
   * @return javax.swing.JList
   */
  private JList getTitelLijst() {
    if (titelLijst == null) {
      titelLijst = new JList();
      titelLijst.setModel(new DefaultListModel());
      titelLijst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }
    return titelLijst;
  }

  /**
   * This method initializes trackScrollPane
   * 
   * @return javax.swing.JScrollPane
   */
  private JScrollPane getTrackScrollPane() {
    if (trackScrollPane == null) {
      trackScrollPane = new JScrollPane();
      trackScrollPane.setBounds(new Rectangle(222, 62, 174, 235));
      trackScrollPane.setViewportView(getTrackLijst());
    }
    return trackScrollPane;
  }

  /**
   * This method initializes trackLijst
   * 
   * @return javax.swing.JList
   */
  private JList getTrackLijst() {
    if (trackLijst == null) {
      trackLijst = new JList();
      trackLijst.setModel(new DefaultListModel());
    }
    return trackLijst;
  }

  /**
   * This method initializes jButton
   * 
   * @return javax.swing.JButton
   */
  private JButton getJButton() {
    if (trackKnop == null) {
      trackKnop = new JButton();
      trackKnop.setBounds(new Rectangle(150, 15, 109, 30));
      trackKnop.setText("Toon tracks");
      trackKnop.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
          trackKnopAction();
        }
      });
    }
    return trackKnop;
  }

  public static void main(String[] args) {
    CDCollectieFrame fr = new CDCollectieFrame();
    fr.setVisible(true);
  }
} // @jve:decl-index=0:visual-constraint="10,10"
