// Author: Matthew Leung
// ICS4U1 Final Project
// Penguin Solitaire

import java.awt.*;      // Java's Abstract Windowing Toolkit package - includes class Color
import java.util.*;     // Vector class is in the 'util' package

//A pile is very different from a deck (only the addCard, dealCard, and getCard methods are common to both classes)
//A pile is drawn differently; does not have a width, height, or centre coordinates; is not a shape; cannot be erased; and serves as a different purpose
//For those reasons, PileClass does not inherit from DeckClass
public class PileClass
{
    private Vector deck = new Vector (0, 1);
    private int lx, ly; //pile top left x coordinate, pile top left y coordinate, card height, card width

    public PileClass ()
    {

    }


    public PileClass (int x, int y)
    {
	setLpoint (x, y);
    }


    public void setLpoint (int x, int y)
    {
	lx = x;
	ly = y;
    }


    public int getLx ()
    {
	return lx;
    }


    public int getLy ()
    {
	return ly;
    }


    //////////////////////////////////////////////////////////////////////
    //Methods used to add or remove a pile

    public void addCard (CardClass newCard, int pos)  //add a card to the pile
    {
	if ((pos == 0 && deck.size () == 0) || pos > deck.size ())
	{
	    deck.addElement (newCard);
	}
	else
	{
	    deck.insertElementAt (newCard, pos);
	}
	fixPile ();
    }


    public CardClass dealCard (int Pos)  //remove card from deck and returns it
    {
	return (CardClass) deck.remove (Pos);
    }


    public CardClass getCard (int pos)  //returns a specific card from the pile
    {
	return (CardClass) deck.get (pos);
    }


    public void addPile (PileClass newPile) //adds a pile to the current pile
    {
	final int initialSize = newPile.getPileSize ();
	for (int i = 0 ; i < initialSize ; i++)
	{
	    addCard (newPile.dealCard (0), deck.size () + 1);
	}
    }


    public PileClass getPile (int pos)  //removes and returns the part of the pile that the user selects
    {
	PileClass p = new PileClass ();
	final int initialSize = deck.size ();

	for (int i = pos ; i < initialSize ; i++)
	{
	    p.addCard (dealCard (pos), 50);
	}
	return p;
    }


    //////////////////////////////////////////////////////////////////////
    //Useful functions

    public int getPosInPile (int x, int y)  //returns the card position that the user selects
    {
	int pos = 0;
	if (isInside (x, y) == true) //checks if the coordinate is inside the pile
	{
	    if (deck.size () == 0) //error
	    {
		pos = -1;
	    }
	    else
	    {
		pos = (int) Math.floor ((y - getLy ()) / (Globals.cwh.y / 3)); //calculates the position in the pile
		if (pos > deck.size () - 1) //if the calculated position is greater than deck.size () - 1, then return the position as the bottom card in the pile
		{
		    pos = deck.size () - 1;
		}
	    }
	}
	else //error
	{
	    pos = -1;
	}

	return pos;
    }


    public boolean isInside (int x, int y)  //checks if point is inside the pile
    {
	if ((x > lx) && (y > ly) && (x < lx + Globals.cwh.x) && (y < ly + Globals.cwh.y + ((deck.size ()) - 1) * (Globals.cwh.y / 3)))
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    public int getPileSize ()  //returns the number of cards in the pile
    {
	return deck.size ();
    }


    /////////////////////////////////////////////////////////////////
    //functions to check if pile is allowed to be moved

    public boolean canAdd (PileClass p)  //checks if a certain pile p can be added to the current pile
    {
	if (deck.size () > 0) //if the pile is not empty
	{
	    if ((p.getCard (0)).getSuit () == (getCard (deck.size () - 1)).getSuit ()) //suit must be the same
	    {
		if (getCard (deck.size () - 1).getValueInt () != 2) //if the bottom card of the current pile is not a 2
		{
		    if (p.getCard (0).getValueInt () == getCard (deck.size () - 1).getValueInt () - 1) //added card must have a rank 1 less than the card at the bottom of the pile
		    {
			return true;
		    }
		}
		else //if the bottom card of the curent pile is a 2, the top card of pile p must be Ace
		{
		    if (p.getCard (0).getValueInt () == 14)
		    {
			return true;
		    }
		}
	    }
	}
	else //if the pile is empty, only a pile with top card that is one less in rank than the beak can be added
	{
	    if (Globals.beak != 2) //if the beak is not a 2
	    {
		if (p.getCard (0).getValueInt () == Globals.beak - 1)
		{
		    return true;
		}
	    }
	    else //if the beak is a 2, only a pile with top card Ace can be added
	    {
		if (p.getCard (0).getValueInt () == 14)
		{
		    return true;
		}
	    }
	}
	return false;
    }


    public boolean canDrag (int pos)  //checks if a part of the pile can be removed from the current pile
    {
	if (pos == deck.size () - 1)
	{
	    return true;
	}
	else
	{
	    final int s = getCard (pos).getSuit ();
	    for (int i = pos + 1 ; i < deck.size () ; i++)
	    {
		if (getCard (i).getSuit () != s) //in order for a pile to be draggable, the suit of every card in it must be the same
		{
		    return false;
		}
		if (getCard (i - 1).getValueInt () != 2) //if the card above is not a 2
		{
		    if (getCard (i - 1).getValueInt () - 1 != getCard (i).getValueInt ()) //cards must be in descending order
		    {
			return false;
		    }
		}
		else //if the card above is a 2, the current card must be an Ace
		{
		    if (getCard (i).getValueInt () != 14)
		    {
			return false;
		    }
		}
	    }
	    return true;
	}

    }


    /////////////////////////////////////////////////////////////////

    private void fixPile ()  //fixes the location of each card in the pile so that it matches with the pile's coordinates
    {
	if (deck.size () > 0)
	{
	    for (int i = 0 ; i < deck.size () ; i++)
	    {
		((CardClass) deck.get (i)).setHeight (Globals.cwh.y);
		((CardClass) deck.get (i)).setCentre (lx + (Globals.cwh.x / 2), ly + Globals.cwh.y / 2 + (i * (Globals.cwh.y / 3)));
	    }
	}
    }


    /////////////////////////////////////////////////////////////////

    public void draw (Graphics g)
    {
	if (deck.size () > 0)
	{
	    fixPile ();
	    for (int i = 0 ; i < deck.size () ; i++)
	    {
		getCard (i).draw (g);
	    }
	}
    }
}
