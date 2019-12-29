// Author: Matthew Leung
// ICS4U1 Final Project
// Penguin Solitaire

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    Graphics g;
    Button buttonRestart = new Button ("Restart"); //restart button
    BorderLayout lm = new BorderLayout (); //layout manager

    FoundationClass[] fd = new FoundationClass [4]; //the 4 foundation decks of my game
    FlipperClass[] fp = new FlipperClass [7]; //the 7 flipper (temporary) card spaces of my game
    PileClass[] col = new PileClass [7]; //the 7 columns of my game
    Point[] colLoc = new Point [7]; //the left corner coordinates of the 7 columns of my game

    PileClass temp = new PileClass (); //temporary pile so that piles can be moved
    boolean OKtoMove; //whether or not an object can be dragged
    int taken; //the location that a pile was taken from (0 to 6 is a column, 7 to 13 is a flipper space)
    int dmx, dmy; //difference between mouse coordinates and object (centre or left corner) coordinates
    static boolean dialogCommunicator; //variable that is used to communicate responses between DialogClass and Main
    Font f = new Font ("SanSerif", Font.PLAIN, 14); //font used to draw my name in top left corner of applet

    public void init ()
    {
	Panel pDraw = new Panel ();
	add ("Centre", pDraw);
	g = pDraw.getGraphics ();
	setLayout (lm);
	setSize (1200, 900); //set the size of the applet
	setBackground (Globals.colorbg); //set background colour of applet

	/////////////////////////////////////////////////////////////////////////////////////////
	//buttons

	Panel pde = new Panel (); //adding a FlowLayout to the East portion of the lm BorderLayout
	pde.setLayout (new FlowLayout ());
	pde.add (buttonRestart);
	add ("East", pde);

	/////////////////////////////////////////////////////////////////////////////////////////

	for (int i = 0 ; i < 7 ; i++) //set up the left corner coordinates of the 7 columns
	{
	    colLoc [i] = new Point ();
	    colLoc [i].x = 210 + i * (Globals.cwh.x + 30);
	    colLoc [i].y = 50 + Globals.cwh.y + 40;
	}

	start ();
	addMouseListener (this);
	addMouseMotionListener (this);
    }


    public void start ()  //setup the game
    {
	DeckClass stdDeck = new DeckClass ('s');
	DeckClass initFdCards = new DeckClass (); //temporary deck to store the initial cards in the foundation
	Globals.beak = (int) (Math.random () * 13) + 2; //random rank of card is selected for the beak
	for (int k = 0 ; k <= 3 ; k++)
	{
	    initFdCards.addCard (stdDeck.dealCard (Globals.beak * 4 - 8), 0); //add 4 cards each of the same randomly generated rank to this deck
	}
	initFdCards.shuffle ();


	for (int i = 0 ; i < 4 ; i++) //add a card to 3 out of the 4 foundation decks
	{
	    fd [i] = new FoundationClass ();
	    if (i != 3)
	    {
		fd [i].addCard (initFdCards.dealCard (0), 0);
	    }
	    fd [i].setCentreAndHeight (50 + Globals.cwh.x / 2, 50 + Globals.cwh.y / 2 + i * (Globals.cwh.y + 40), Globals.cwh.y);
	}

	for (int i = 0 ; i < 7 ; i++) //set up the location of the 7 columns and 7 flipper spaces
	{
	    fp [i] = new FlipperClass ();
	    fp [i].setCentreAndHeight (Globals.cwh.x / 2 + colLoc [i].x, 50 + Globals.cwh.y / 2, Globals.cwh.y);
	    col [i] = new PileClass ();
	    col [i].setLpoint (colLoc [i].x, colLoc [i].y);
	}

	//add the rest of the cards to the 7 columns
	col [0].addCard (initFdCards.dealCard (0), 0); //add the remaining 4th "beak" card to the first card in the first column
	stdDeck.shuffle (); //shuffle the remaining cards
	for (int i = 2 ; i <= 49 ; i++)
	{
	    if (i % 7 != 0) //7 cards in every pile
	    {
		col [(int) Math.floor (i / 7)].addCard (stdDeck.dealCard (0), 7);
	    }
	    else
	    {
		col [i / 7 - 1].addCard (stdDeck.dealCard (0), 7);
	    }
	}        
    }


    public void update (Graphics g)  //double buffering
    {
	Graphics offgc;
	Image offscreen = null;
	Dimension d = size ();

	// create the offscreen buffer and associated Graphics
	offscreen = createImage (d.width, d.height);
	offgc = offscreen.getGraphics ();
	// clear the exposed area
	offgc.setColor (getBackground ());
	offgc.fillRect (0, 0, d.width, d.height);
	offgc.setColor (getForeground ());
	// do normal redraw
	paint (offgc);
	// transfer offscreen to window
	g.drawImage (offscreen, 0, 0, this);
    }


    public void actionPerformed (ActionEvent e)
    {

    }


    public boolean action (Event e, Object o)
    {
	if (e.target instanceof Button)
	{
	    if (e.target == buttonRestart) //restart button pressed
	    {
		new DialogClass (1); //opens up a dialog box that confirms if the player wants to restart
		if (dialogCommunicator == true)
		{
		    start (); //restart the game
		}
	    }
	}
	repaint ();
	return true;
    }


    public void paint (Graphics g)
    {
	g.setFont (f);
	g.drawString ("PENGUIN SOLITAIRE", 5, 15);
	g.drawString ("By Matthew Leung", 5, 30); //my name        
	for (int i = 0 ; i < 4 ; i++) //draw the foundation decks
	{
	    fd [i].draw (g);
	}

	for (int i = 0 ; i < 7 ; i++) //draw the flipper spaces and columns
	{
	    g.setColor (Color.black);
	    g.drawRect (colLoc [i].x, colLoc [i].y, Globals.cwh.x, Globals.cwh.y); //draw the rectangles that mark the position of each pile
	    fp [i].draw (g);
	    col [i].draw (g);
	}
	temp.draw (g);
    }


    ///////////////////////////////////////////////////////////////////


    public void mouseClicked (MouseEvent e)
    {

    }


    public void mouseEntered (MouseEvent e)
    {

    }


    public void mouseExited (MouseEvent e)
    {

    }


    public void mousePressed (MouseEvent e)
    {
	if (e.getButton () == 1) //only valid when mouse is left click
	{
	    for (int i = 0 ; i < 7 ; i++)
	    {
		if ((col [i].isInside (e.getX (), e.getY ()) == true) && (col [i].canDrag (col [i].getPosInPile (e.getX (), e.getY ())) == true)) //player selected a draggable pile from a column
		{
		    OKtoMove = true;
		    taken = i;
		    dmx = col [i].getLx () - e.getX ();
		    dmy = col [i].getLy () + col [i].getPosInPile (e.getX (), e.getY ()) * (Globals.cwh.y / 3) - e.getY ();
		    temp = col [i].getPile (col [i].getPosInPile (e.getX (), e.getY ()));
		    temp.setLpoint (e.getX () + dmx, e.getY () + dmy); //make mouse press (selection of object) look more natural
		    temp.draw (g);
		    repaint ();
		}
	    }
	    for (int i = 0 ; i < 7 ; i++)
	    {
		if ((fp [i].isInside (e.getX (), e.getY ()) == true) && (fp [i].getDeckSize () > 0)) //player selected a draggable pile (single card in this case) from a flipper space
		{
		    OKtoMove = true;
		    taken = i + 7;
		    dmx = fp [i].getCenterX () - Globals.cwh.x / 2 - e.getX ();
		    dmy = fp [i].getCenterY () - Globals.cwh.y / 2 - e.getY ();
		    temp = fp [i].returnPile ();
		    temp.setLpoint (e.getX () + dmx, e.getY () + dmy); //make mouse press (selection of object) look more natural
		    temp.draw (g);
		    repaint ();
		}
	    }
	}
    }


    public void mouseReleased (MouseEvent e)
    {
	if (OKtoMove == true)
	{
	    int choice = -1; //choice is which object the player chooses to add the pile to (1 = another column, 2 = a flipper space, 3 = a foundation deck)
	    int n = -1; //the location in the array containing the object the player chooses to add the pile to
	    boolean success = false; //whether or not the pile can be added to the object that the player chooses

	    for (int i = 0 ; i < 7 ; i++)
	    {
		if (col [i].isInside (e.getX (), e.getY ()) == true) //player wants to add pile to another column
		{
		    n = i;
		    choice = 1;
		    break;
		}
		else if (fp [i].isInside (e.getX (), e.getY ()) == true) //player wants to add pile to a flipper space
		{
		    n = i;
		    choice = 2;
		    break;
		}

		if (i < 4)
		{
		    if (fd [i].isInside (e.getX (), e.getY ()) == true) //player wants to add pile to a foundation deck
		    {
			n = i;
			choice = 3;
			break;
		    }
		}
	    }

	    if (choice == 1)
	    {
		if (col [n].canAdd (temp) == true) //verify if the pile can be added to another column
		{
		    col [n].addPile (temp);
		    success = true;
		}
	    }
	    else if (choice == 2)
	    {
		if (fp [n].canAdd (temp) == true) //verify if the pile can be added to a flipper space
		{
		    fp [n].addPile (temp);
		    success = true;
		}
	    }
	    else if (choice == 3)
	    {
		if (fd [n].canAdd (temp) == true) //verify if the pile can be added to a foundation deck
		{
		    fd [n].addPile (temp);
		    success = true;
		}
	    }

	    if (success == false)
	    {
		if ((taken >= 0) && (taken <= 6)) //pile was taken from a column
		{
		    col [taken].addPile (temp); //place the temp pile back into the column where the pile was taken from
		}
		else if ((taken >= 7) && (taken <= 13)) //pile was taken from a flipper space
		{
		    fp [taken - 7].addPile (temp); //place the temp pile (which is a single card) back into the flipper space where the pile was taken from
		}
	    }
	    OKtoMove = false;
	    repaint (); 
	    
	    if ((fd [0].complete () == true) && (fd [1].complete () == true) && (fd [2].complete () == true) && (fd [3].complete () == true)) //checks if game has been won
	    {
		new DialogClass (2); //popup dialog to tell player that they won
		if (dialogCommunicator == true)
		{
		    start (); //Play again
		    repaint ();
		}
		else
		{
		    System.exit (0);
		} 
	    }
	}
    }


    public void mouseDragged (MouseEvent e)
    {
	if (OKtoMove == true) //object can be dragged
	{
	    temp.setLpoint (e.getX () + dmx, e.getY () + dmy);
	    temp.draw (g);
	    repaint ();
	}
    }


    public void mouseMoved (MouseEvent e)
    {

    }
}
