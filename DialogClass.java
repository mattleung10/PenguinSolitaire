// Author: Matthew Leung
// ICS4U1 Final Project
// Penguin Solitaire

import java.awt.*;
import java.awt.event.*;

public class DialogClass //used to create dialog boxes
{
    private static Dialog d;
    
    DialogClass ()
    {
    }
    
    DialogClass (int type)
    {
	if ((type == 1) || (type == 2))
	{
	    Frame f = new Frame ();
	    Button by, bn;
	    Label confirm;

	    if (type == 1) //dialog box that confirms if the player wants to restart
	    {
		d = new Dialog (f, "Warning", true);
		by = new Button ("Yes");
		bn = new Button ("No");
		confirm = new Label ("Are you sure you want to restart?");
	    }
	    else //dialog box that asks player if they want to play again
	    {
		d = new Dialog (f, "Game Won", true);
		by = new Button ("Play Again");
		bn = new Button ("Exit");
		confirm = new Label ("Congratulations! You won the game!");
	    }
	    d.setLocationRelativeTo (null);
	    d.setSize (300, 125);

	    ////////////////////////////////////////////////////////////////////

	    by.addActionListener (new ActionListener ()  //takes care of what happens when each button is pressed
	    {
		public void actionPerformed (ActionEvent e)
		{
		    DialogClass.d.setVisible (false); //close the dialog
		    Main.dialogCommunicator = true; //used to pass information over to Main
		}
	    }
	    );
	    bn.addActionListener (new ActionListener ()
	    {
		public void actionPerformed (ActionEvent e)
		{
		    DialogClass.d.setVisible (false); //close the dialog
		    Main.dialogCommunicator = false; //used to pass information over to Main
		}
	    }
	    );

	    ////////////////////////////////////////////////////////////////////

	    BorderLayout lm = new BorderLayout (); //layout manager for d
	    d.setLayout (lm);

	    ////////////////////////////////////////////////////////////////////

	    Panel pdeNorth = new Panel (); //add a FlowLayout to the "North" panel
	    pdeNorth.setLayout (new FlowLayout ());
	    pdeNorth.add (confirm);
	    d.add ("North", pdeNorth);

	    ////////////////////////////////////////////////////////////////////

	    Panel pdeSouth = new Panel (); //add a GridBagLayout to the "South" panel
	    GridBagLayout lmgb = new GridBagLayout ();
	    pdeSouth.setLayout (lmgb);
	    GridBagConstraints gbc = new GridBagConstraints ();
	    gbc.weightx = 1;
	    gbc.weighty = 1;

	    gbc.gridx = 2;
	    gbc.gridy = 0;
	    lmgb.setConstraints (by, gbc);
	    pdeSouth.add (by, gbc);

	    gbc.gridx = 3;
	    gbc.gridy = 0;
	    lmgb.setConstraints (bn, gbc);
	    pdeSouth.add (bn, gbc);

	    gbc.gridx = 0; //included to improve spacing
	    gbc.gridy = 1;
	    pdeSouth.add (new Label (""), gbc);
	    gbc.gridx = 4; //included to improve spacing
	    gbc.gridy = 1;
	    pdeSouth.add (new Label (""), gbc);
	    d.add ("South", pdeSouth);

	    ////////////////////////////////////////////////////////////////////

	    d.setVisible (true);
	}
    }
}
