package turtle;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Turtle class contains the x- and y- positions, colour, moves and pen up/down
 * 
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

	public void rotateAnticlockwise(int degree) {
		orientation = (orientation - degree + 360) % 360; // in case the orientation-degree is minus degree
	}

	/**
	 * moveForward changes the position of the turtle by the given distance
	 */
	public void moveForward(int distance) {
		int newX = (int) (xPos + distance * Math.cos(Math.toRadians(orientation)));
		int newY = (int) (yPos - distance * Math.sin(Math.toRadians(orientation)));

		// Ensure the new position is within the panel boundaries
		if (newX < 0)
			newX = Math.max(0, Math.min(newX, maxWidth - 1));
		if (newY < 0)
			newY = Math.max(0, Math.min(newY, maxHeight - 1));

		System.out.println("moveForward " + xPos + ", " + yPos + ", " + newX + ", " + newY);
		if (!penUp) {
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
	 * draw the lines moved by the turtle
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {

		g.fillOval(xPos, yPos, 3, 3);
		for (TurtleMove move : moves) {
			g.setColor(move.getColour());
			g.drawLine(move.getX1(), move.getY1(), move.getX2(), move.getY2());
			// System.out.println("draw a new line " + move.getX1() + " "+ move.getY1() + ",
			// " + move.getX2() + ", " + move.getY2());
		}
//        System.out.println("draw a new turtle " + xPos + ", " + yPos);

	}
}
