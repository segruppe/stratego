/**
 * Created by Dennis on 30.10.2014.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menue extends JFrame {

    private JButton spielStarten;
    private JButton hilfe;
    private JButton beenden;
    private JPanel panelButton;

    public Menue() {
        super("Stratego");
        // Groesse des Fensters
        setSize(600, 600);
        // Position des Fensters
        setLocation(700, 300);
        // Programm beim Schliessen des Fensters beenden
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout(5, 5));

        // Buttons erzeugen
        spielStarten = new JButton("Spiel starten");
        hilfe = new JButton("Hilfe");
        beenden = new JButton("Beenden");

        // Panels auf GridLayout erzeugen
        panelButton = new JPanel(new GridLayout(4, 1));

        // Buttons auf panel packen
        panelButton.add(spielStarten);
        panelButton.add(hilfe);
        panelButton.add(beenden);

//        // Listener fuer Buttons
//        addButtonListener(spielStarten);
//        addButtonListener(spielLaden);
//        addButtonListener(hilfe);
//        addButtonListener(beenden);

        //Panels auf Frame packen (das panelButton hat ein GridLayout, dass jetzt in den WestBereich des BorderLayouts kommt)
        getContentPane().add(BorderLayout.CENTER, panelButton);
        // sichtbar machen
        setVisible(true);
    }

    public static void main(String[] args) {
        Menue gui = new Menue();
    }
//
//    private void addButtonListener(JButton b){
//        b.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent ae){
//                eingabe(ae.getActionCommand());
//            }
//        });
//    }
//private void eingabe(String a)
//{
//    anzeige.setText(a);
//}

}
