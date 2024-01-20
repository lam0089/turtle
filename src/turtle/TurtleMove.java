package turtle;

import java.awt.Color;
/**
 * TurtleMove contains one move of a turtle and its colour
 * each move starts with (x1, y1) and ends with (x2, y2).
 * each move has a colour
 * @author Lam
 *
 */
public class TurtleMove {
	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private Color colour;
	
	public TurtleMove(int x1, int y1, int x2, int y2, Color c) {
		this.x1=x1;
		this.y1=y1;
		this.y2=y2;
		this.x2=x2;
		colour = c;
	}
	public Color getColour() {
		return colour;
	}
	public int getX1() {
		return x1;		
	}
	public int getY1() {
		return y1;		
	}
	public int getX2() {
		return x2;		
	}
	public int getY2() {
		return y2;		
	}
}
