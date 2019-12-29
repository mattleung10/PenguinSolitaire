// Author: Matthew Leung
// ICS4U1 Final Project
// Penguin Solitaire

import java.awt.*;      // Java's Abstract Windowing Toolkit package - includes class Color
import java.util.*;     // Vector class is in the 'util' package

public class FlipperClass extends DeckClass
{
    public FlipperClass ()
    {
    }
    
    
    public boolean canAdd (PileClass p)
    {
	if ((super.canAdd (p) == true) && (getDeckSize () == 0)) //flipper space must be empty
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }
    
    
    public PileClass returnPile () //removes and returns a card (in the form of a pile) from the flipper space
    {
	PileClass p = new PileClass ();
	p.addCard (dealCard (0), 0);
	return p;
    }
    
}
