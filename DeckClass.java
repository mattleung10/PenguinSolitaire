// Author: Matthew Leung
// ICS4U1 Final Project
// Penguin Solitaire

import java.awt.*;      // Java's Abstract Windowing Toolkit package - includes class Color
import java.util.*;     // Vector class is in the 'util' package

public class DeckClass extends ShapeClass
{
    private Vector deck = new Vector (0, 1);

    public DeckClass ()
    {
    }


    public DeckClass (char deckType)
    {
	if (deckType == 's')  // standard deck
	{
	    for (int n = 2 ; n <= 14 ; n++)
	    {
		for (int s = 1 ; s <= 4 ; s++)
		{
		    CardClass oCard = new CardClass ();
		    oCard.setUp (true);
		    oCard.setSuit (s);
		    oCard.setValueByInt (n);
		    addCard (oCard, 53);
		}
	    }
	}
    }

    
    /////////////////////////////////////////////////////////////////////////////////

    public void addCard (CardClass newCard, int pos)  //add a card to the deck
    {
	if ((pos == 0 && deck.size () == 0) || pos > deck.size ())
	{
	    deck.addElement (newCard);
	}
	else
	{
	    deck.insertElementAt (newCard, pos);
	}
    }


    public CardClass dealCard (int Pos)  //remove
    {
	return (CardClass) deck.remove (Pos); // must type cast element
    }


    public void shuffle ()  //shuffle the deck
    {
	final int n = deck.size ();
	for (int i = 1 ; i <= n * 10 ; i++)
	{
	    addCard (dealCard ((int) (Math.random () * (n - 1))), (int) (Math.random () * (n - 1)));
	}
    }


    public CardClass getCard (int pos)  //returns a specfic card from the deck
    {
	return (CardClass) deck.get (pos);
    }


    public void addPile (PileClass p)  //adds a pile (if allowed) to the deck
    {
	if (canAdd (p) == true)
	{
	    addCard (p.dealCard (0), 0);
	    setCentreAndHeight (getCenterX (), getCenterY (), getHeight ());
	}
    }


    public boolean canAdd (PileClass p)  //checks if a pile can be added to the deck
    {
	if (p.getPileSize () == 1)
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    /////////////////////////////////////////////////////////////////////////////////

    public void setWidth (int w)
    {
	super.setWidth (w);
	super.setHeight (w * 10 / 7);
    }


    public void setHeight (int h)
    {
	super.setHeight (h);
	super.setWidth (h * 7 / 10);
    }


    public void setCentreAndHeight (int x, int y, int h)  //sets the centre and height (and therefore also the width) of every card in the deck
    {
	setCentre (x, y);
	setHeight (h);
	for (int i = 0 ; i < deck.size () ; i++)
	{
	    ((CardClass) deck.get (i)).setHeight (h);
	    ((CardClass) deck.get (i)).setCentre (x, y);
	}
    }


    public int getDeckSize ()  //returns the size of the deck
    {
	return deck.size ();
    }


    public void draw (Graphics g)
    {
	if (deck.size () > 0)
	{
	    if (((CardClass) deck.firstElement ()).getUp () == true)
	    {
		((CardClass) deck.firstElement ()).draw (g);
	    }
	    else
	    {
		g.setColor (Color.black);
		g.fillRect (getCenterX () - getWidth () / 2, getCenterY () - getHeight () / 2, getWidth (), getHeight ());
	    }
	}
	else
	{
	    g.setColor (Color.black);
	    g.drawRect (getCenterX () - getWidth () / 2, getCenterY () - getHeight () / 2, getWidth (), getHeight ());
	}
    }


    public void erase (Graphics g)
    {
	Color oldColor = getColor ();
	setColor (Globals.colorbg);
	g.drawRect (getCenterX () - getWidth () / 2, getCenterY () - getHeight () / 2, getWidth (), getHeight ());
	setColor (oldColor);
    }
}
