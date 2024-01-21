package turtle;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Turtle is shown as a small circle. 
 * Each turtle has a name. Its default position is at the centre of the frame and facing north.
 * Each turtle moves when there is a command. If the pen is down, it leaves a trail. The colour of trails can be changed.
 * Each turtle can only move within the range of the frame.
 * @author Lam
 *
 */

public class Turtle {

	String name; // the name of the turtle
	int xPos; // x position of the turtle
	int yPos; // y position of the turtle
	Color color; // the colour of move lines
	boolean penUp = false; // is the pen up or down?
	private ArrayList<TurtleMove> moves; // an array of moves done by the turtle
	private int maxWidth; // each turtle should move within the range of the given max width
	private int maxHeight; // each turtle should move within the range of the given max height

	private int orientation; // each turtle has an orientation

	/**
	 * Create a new Turtle object.
	 * 
	 * @param n is the name of the turtle
	 * @param x is the x-coordination of the turtle
	 * @param y is the y-coordination of the turtle
	 */
	public Turtle(String n, int x, int y) {
		xPos = x;
		yPos = y;
		name = n;
		moves = new ArrayList<TurtleMove>();
		color = Color.BLACK; // colour is black by default
		orientation = 90; // Facing north by default

	}
	/**
	 * the turtle rotates given an input degree
	 * @param degree The degree the turtle rotates.
	 */
	public void rotateTurtle(int degree) {
		orientation = (orientation - degree + 360) % 360; // in case the orientation-degree is minus degree
	}

	/**
	 * moveForward changes the position of the turtle by the given distance
	 * @param distance How far the turtle will move
	 */
	public void moveForward(int distance) {
		int newX = (int) (xPos + distance * Math.cos(Math.toRadians(orientation)));
		int newY = (int) (yPos - distance * Math.sin(Math.toRadians(orientation)));

		// Ensure the new position is within the panel boundaries
		if (newX < 0) {
			newX = Math.max(0, Math.min(newX, maxWidth - 1));
		}
		if (newY < 0) {
			newY = Math.max(0, Math.min(newY, maxHeight - 1));
		}
		
		if (!penUp) { // if pen is not up or you can say the pen is down
			moves.add(new TurtleMove(xPos, yPos, newX, newY, color));
		}
		xPos = newX;
		yPos = newY;
	}

	public String getName() {
		return name;
	}

	public void setColour(Color c) {
		this.color = c;
	}

	public void setPenUp(boolean up) {
		penUp = up;
	}

	public void setMaxWidth(int width) {
		maxWidth = width;
	}

	public void setMaxHeight(int height) {
		maxHeight = height;
	}

	/**
	 * Display the turtle as a small circle and draw the trails moved by the turtle
	 * 
	 * @param g The graphics in which the turtle is displayed
	 */
	public void draw(Graphics g) {

		g.fillOval(xPos, yPos, 3, 3); // display a turtle as a small circle
		for (TurtleMove move : moves) {
			g.setColor(move.getColour());
			g.drawLine(move.getX1(), move.getY1(), move.getX2(), move.getY2());
		}
	}
}
