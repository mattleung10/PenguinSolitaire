// Author: Matthew Leung
// ICS4U1 Final Project
// Penguin Solitaire

import java.awt.*;      // Java's Abstract Windowing Toolkit package - includes class Color
import java.util.*;     // Vector class is in the 'util' package

public class FoundationClass extends DeckClass
{
    public FoundationClass ()
    {
    }


    public boolean canAdd (PileClass p)  //checks if a pile can be added to the foundation deck
    {
	if (super.canAdd (p) == true)
	{
	    final CardClass newCard = p.getCard (0);
	    if (getDeckSize () > 0)
	    {
		final int s = getCard (0).getSuit ();
		if (newCard.getSuit () == s)
		{
		    if (getCard (0).getValueInt () != 14) //if the top card on the foundation deck is not an Ace
		    {
			if (newCard.getValueInt () == getCard (0).getValueInt () + 1)
			{
			    return true;
			}
		    }
		    else //if the top card on the foundation deck is an Ace
		    {
			if (newCard.getValueInt () == 2) //card that is added must be a 2
			{
			    return true;
			}
		    }
		}
	    }
	    else //if foundation deck is empty, a card with the same rank as the beak must be put in
	    {
		if (newCard.getValueInt () == Globals.beak)
		{
		    return true;
		}
	    }
	}
	return false;
    }


    public boolean complete ()  //checks if all the cards have been moved into this foundation deck (whether or not this foundation has been completed)
    {
	if (getDeckSize () == 13)
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    ///////////////////////////////////////////////////////////////////////////////

    private String getValueByInt (int iValue)  //used by draw (changes a numeric rank to string, ex. 14 becomes "A")
    {
	String value = "";
	if (iValue >= 2 && iValue <= 10)
	{
	    value = Integer.toString (iValue);
	}
	else if (iValue == 11)
	{
	    value = "J";
	}
	else if (iValue == 12)
	{
	    value = "Q";
	}
	else if (iValue == 13)
	{
	    value = "K";
	}
	else if (iValue == 14)
	{
	    value = "A";
	}
	else if (iValue < 2 || iValue > 14)
	{
	    value = "Error"; //error message
	}
	return value;
    }


    public void draw (Graphics g)
    {
	super.draw (g);
	if (getDeckSize () == 0)
	{
	    Font f1 = new Font ("SanSerif", Font.PLAIN, getHeight () / 5);
	    g.setColor (Color.white);
	    g.setFont (f1);
	    g.drawString (getValueByInt (Globals.beak), getCenterX () - getWidth () / 2 + getWidth () / 25, getCenterY () - getHeight () / 2 + getHeight () / 5);
	}
    }
}
