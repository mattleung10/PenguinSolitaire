// Author: Matthew Leung
// ICS4U1 Final Project
// Penguin Solitaire

import java.awt.*;      // Java's Abstract Windowing Toolkit package - includes class Color

public abstract class ShapeClass
{
    private int iWidth, iHeight, iCentreX, iCentreY;
    private Color iColor;

    public ShapeClass ()
    {
	iWidth = 100;
	iHeight = 100;
	iCentreX = 100;
	iCentreY = 100;
	iColor = Color.red;
    }


    public void setWidth (int iNewWidth)
    {
	iWidth = iNewWidth;
    }


    public void setHeight (int iNewHeight)
    {
	iHeight = iNewHeight;
    }


    public void setCentre (int iNewCentreX, int iNewCentreY)
    {
	iCentreX = iNewCentreX;
	iCentreY = iNewCentreY;
    }


    public void setColor (Color iNewColor)
    {

	iColor = iNewColor;
    }


    ///////////////////////////////////////////////////////////////////
    // Communicator methods (accessors)

    public int getWidth ()
    {
	return iWidth;
    }


    public int getHeight ()
    {
	return iHeight;
    }


    public int getCenterX ()
    {
	return iCentreX;
    }


    public int getCenterY ()
    {
	return iCentreY;
    }


    public Color getColor ()
    {
	return iColor;
    }


    ///////////////////////////////////////////////////////////////////

    public abstract void draw (Graphics g);

    public void delay (int iDelayTime)
    {
	long lFinalTime = System.currentTimeMillis () + iDelayTime;
	do
	{
	}
	while (lFinalTime >= System.currentTimeMillis ());
    }


    public void erase (Graphics g)
    {
	Color oldColor = getColor ();
	setColor (Color.white);
	draw (g);
	setColor (oldColor);
    }


    public boolean isInside (int x, int y)
    {
	if ((x > getCenterX () - (getWidth () / 2)) && (x < getCenterX () + (getWidth () / 2)) && (y > getCenterY () - (getHeight () / 2)) && (y < getCenterY () + (getHeight () / 2)))
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }
}
