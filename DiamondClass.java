// Author: Matthew Leung
// ICS4U1 Final Project
// Penguin Solitaire

import java.awt.*;      // Java's Abstract Windowing Toolkit package - includes class Color

public class DiamondClass extends SuitClass
{
    public void draw (Graphics g)
    {
	// declare two arrays for X & Y coordinates of Diamond
	int iPointsX[] = new int [4];
	int iPointsY[] = new int [4];

	// calculate points on diamond & store in the arrays
	iPointsX [0] = getCenterX () - getWidth () / 2;
	iPointsY [0] = getCenterY ();
	iPointsX [1] = getCenterX ();
	iPointsY [1] = getCenterY () - getHeight () / 2;
	iPointsX [2] = getCenterX () + getWidth () / 2;
	iPointsY [2] = getCenterY ();
	iPointsX [3] = getCenterX ();
	iPointsY [3] = getCenterY () + getHeight () / 2;

	// draw the diamond using methods available from the Console object (c)
	g.setColor (getColor ());
	g.fillPolygon (iPointsX, iPointsY, 4);
    }
}
