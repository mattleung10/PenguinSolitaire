// Author: Matthew Leung
// ICS4U1 Final Project
// Penguin Solitaire

import java.awt.*;      // Java's Abstract Windowing Toolkit package - includes class Color

public class CardClass extends ShapeClass
{
    private int suit;
    private String value;
    private boolean up;

    public CardClass ()
    {
	setSuit (1);
	value = "2";
	setUp (true);
	setHeight (Globals.cwh.y);
    }
    
    public CardClass (int s, int v, boolean up)
    {
	setSuit (s);
	setValueByInt (v);
	setUp (up);
	setHeight (Globals.cwh.y);
    }


    public void setValueByInt (int iValue)
    {
	if (iValue >= 2 && iValue <= 10)
	{
	    value = Integer.toString (iValue);
	    // if you want to convert int to string, use String.valueOf(number) or Integer.toString(number)
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
    }


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


    public void setSize (int s)
    {
	setHeight (s * 20 + 40);
    }


    public void setSuit (int iSuit)
    {
	if (iSuit >= 1 && iSuit <= 4)
	{
	suit = iSuit;
	}
    }


    public void setUp (boolean iUp)
    {
	up = iUp;
    }

    ///////////////////////////////////////////////////////////////////
    // Communicator methods (accessors)

    public String getValue ()
    {
	return value;
    }


    public int getValueInt ()
    {
	if (value == "J")
	{
	    return 11;
	}
	else if (value == "Q")
	{
	    return 12;
	}
	else if (value == "K")
	{
	    return 13;
	}
	else if (value == "A")
	{
	    return 14;
	}
	else if (value == "Error")
	{
	    return 0; //prevents crash
	}
	else
	{
	    return Integer.parseInt (value);
	}
    }


    public int getSuit ()
    {
	return suit;
    }


    public boolean getUp ()
    {
	return up;
    }

    ///////////////////////////////////////////////////////////////////
    
    
    public void draw (Graphics g)
    {
	if (up == true)
	{
	    Color sc = Color.black;
	    if (suit == 1 || suit == 3)
	    {
		sc = Color.red;
	    }
	    else if (suit == 2 || suit == 4)
	    {
		sc = Color.black;
	    }
	    g.setColor (Color.white);
	    g.fillRect (getCenterX () - getWidth () / 2, getCenterY () - getHeight () / 2, getWidth (), getHeight ()); // syntax is: fillRect (x, y, w, h); x and y are top left corner
	    g.setColor (Color.black);
	    g.drawRect (getCenterX () - getWidth () / 2, getCenterY () - getHeight () / 2, getWidth (), getHeight ());

	    Font f1 = new Font ("SanSerif", Font.PLAIN, getHeight () / 5);
	    g.setColor (sc);
	    g.setFont (f1);
	    g.drawString (getValue (), getCenterX () - getWidth () / 2 + getWidth () / 25, getCenterY () - getHeight () / 2 + getHeight () / 5);

	    if (suit == 1)
	    {
		DiamondClass oDiamond = new DiamondClass ();
		oDiamond.setHeight (getHeight () / 4);
		oDiamond.setCentre (getCenterX (), getCenterY ());
		oDiamond.setColor (sc);
		oDiamond.draw (g);
		DiamondClass sDiamond = new DiamondClass (); //smaller shape below each number
		sDiamond.setHeight (getHeight ()/ 8);
		sDiamond.setCentre (getCenterX () - getWidth () / 2 + getWidth () / 9, getCenterY () - getHeight () / 2 + getHeight () / 5 + getHeight () / 15);
		sDiamond.setColor (sc);
		sDiamond.draw (g);
	    }
	    else if (suit == 2)
	    {
		ClubClass oClub = new ClubClass ();
		oClub.setHeight (getHeight () / 4);
		oClub.setCentre (getCenterX (), getCenterY ());
		oClub.setColor (sc);
		oClub.draw (g);
		ClubClass sClub = new ClubClass ();
		sClub.setHeight (getHeight ()/ 8);
		sClub.setCentre (getCenterX () - getWidth () / 2 + getWidth () / 9, getCenterY () - getHeight () / 2 + getHeight () / 5 + getHeight () / 15);
		sClub.setColor (sc);
		sClub.draw (g);
	    }
	    else if (suit == 3)
	    {
		HeartClass oHeart = new HeartClass ();
		oHeart.setHeight (getHeight () / 4);
		oHeart.setCentre (getCenterX (), getCenterY ());
		oHeart.setColor (sc);
		oHeart.draw (g);
		HeartClass sHeart = new HeartClass ();
		sHeart.setHeight (getHeight ()/ 8);
		sHeart.setCentre (getCenterX () - getWidth () / 2 + getWidth () / 9, getCenterY () - getHeight () / 2 + getHeight () / 5 + getHeight () / 15);
		sHeart.setColor (sc);
		sHeart.draw (g);
	    }
	    else if (suit == 4)
	    {
		SpadeClass oSpade = new SpadeClass ();
		oSpade.setHeight (getHeight () / 4);
		oSpade.setCentre (getCenterX (), getCenterY ());
		oSpade.setColor (sc);
		oSpade.draw (g);
		SpadeClass sSpade = new SpadeClass ();
		sSpade.setHeight (getHeight ()/ 8);
		sSpade.setCentre (getCenterX () - getWidth () / 2 + getWidth () / 9, getCenterY () - getHeight () / 2 + getHeight () / 5 + getHeight () / 15);
		sSpade.setColor (sc);
		sSpade.draw (g);
	    }
	}
	else
	{
	    g.setColor (Color.black);
	    g.fillRect (getCenterX () - getWidth () / 2, getCenterY () - getHeight () / 2, getWidth (), getHeight ());
	}
    }

    
    
    public void erase (Graphics g) {
	setColor (Color.white);
	g.fillRect (getCenterX () - getWidth () / 2, getCenterY () - getHeight () / 2, getWidth (), getHeight ());
    }
}


