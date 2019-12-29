// Author: Matthew Leung
// ICS4U1 Final Project
// Penguin Solitaire

import java.awt.*;      // Java's Abstract Windowing Toolkit package - includes class Color

abstract public class SuitClass extends ShapeClass 
{
    public void setWidth (int w) {
	super.setWidth (w);
	super.setHeight (w * 5 / 4);
    }
    
    public void setHeight (int h) {
	super.setHeight (h);
	super.setWidth (h * 4 / 5);
    }
}
