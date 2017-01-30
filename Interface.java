package league.stat.checker;

/**
 * League of Legends Info Checker v1
 * @author Richard Cai
 * Date: 01/29/2017
 * GUI interface class
 */

//Imports, GUI elements
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Interface extends JFrame{
    
    private JTextField name;
    private JButton search;
    private JPanel fullPanel;
    private JPanel out;
    private JLabel status;

    
    
    //Output information
    private JLabel sumName;
    private JLabel wins;
    private JLabel losses;
    private JLabel winPct;
    
    
    public Interface() throws IOException{
        super("League of Legends Stat Checker");
        
        ItemHandler handler = new ItemHandler();
        
        out = new JPanel();
        out.setLayout(new GridLayout());
        
        //Adding labels to output panel
        sumName = new JLabel("Name: ");
        wins = new JLabel("Wins: ");
        losses = new JLabel("Losses: ");
        winPct = new JLabel("Win %: ");
        
        out.add(sumName);
        out.add(wins);
        out.add(losses);
        out.add(winPct);
        
        //Create a panel for all elements
        fullPanel = new JPanel();
        fullPanel.setLayout(new GridLayout(3,1));
        
        
        
        status = new JLabel("Status");
        
        //Adding all items to fullPanel
        name = new JTextField("ilovedota2");
        fullPanel.add(name);
        
        search = new JButton("Search");
        fullPanel.add(search);
        search.addActionListener(handler);
        
        add(fullPanel,BorderLayout.NORTH);
        add(status,BorderLayout.SOUTH);
        add(out,BorderLayout.CENTER);
       
    }
    
    //ItemHandler, only one event to handle, search button. Looking to implement dropdown boxes in the future for region selection and different game type statistics
    private class ItemHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            String[] info = new String[5];
            if ("Search".equals(event.getActionCommand())){             
                nameToID id = new nameToID();
                try {
                    if (!"Vic".equals(name.getText())){
                        info = id.findInfo(id.nameToID(name.getText()),name.getText());
                    }
                    else if ("Vic".equals(name.getText())){
                        info = id.findInfo(id.nameToID("Riƒt"),"Riƒt");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                    status.setText("Error!");
                }
                
                sumName.setText("Name: " + info[0]);
                wins.setText("Wins: " + info[2]);
                losses.setText("Losses: " + info[3]);
                winPct.setText("Win %: " + info[4]);
                
            }
        }
    }
}

