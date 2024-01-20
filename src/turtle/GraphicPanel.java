package turtle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * GraphicPanel is to draw all turtles. It takes the commands from TurtlePanel's
 * textfield and processes the command
 * 
 * @author Lam
 *
 */

public class GraphicPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<Turtle> turtles = new ArrayList<Turtle>();
	private final int panelWidth = 800;
	private final int panelHeight = 750;

	private BufferedImage bufferedImage;
	private final Color backgroundColour = Color.WHITE;

	GraphicPanel() {

		setPreferredSize(new Dimension(panelWidth, panelHeight));

		bufferedImage = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_RGB);

		setMaximumSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));

		clear();
	}

	public void penUp(String name, String upDown) {
		Turtle turtle = searchTurtle(name);
		if (turtle == null)
			return;
		if (upDown.contains("up")) {
			turtle.setPenUp(true);
		} else if (upDown.contains("down")) {
			turtle.setPenUp(false);
		} else
			System.out.println("Incorrect command for pen up or down. Try again");

	}

	public void addTurtle(Turtle new_turtle) {
		turtles.add(new_turtle);
	}

	public Turtle searchTurtle(String name) {
		for (Turtle turtle : turtles) {
			String n = turtle.getName();
			if (n.equals(name)) {
				return turtle;
			}
		}
		System.out.println("The given turtle name " + name + " is not found.");
		return null;
	}

	public void rotate(String name, int angle) {
		Turtle turtle = searchTurtle(name);
		if (turtle == null)
			return;

		turtle.rotateAnticlockwise(angle);
	}

	public void changeColour(String name, String c) {
		Turtle turtle = searchTurtle(name);
		if (turtle == null)
			return;
		if (c.contains("green")) {
			turtle.setColour(new Color(0, 204, 0));
		} else if (c.contains("red")) {
			turtle.setColour(new Color(250, 0, 0));
		} else if (c.contains("black")) {
			turtle.setColour(new Color(0, 0, 0));
		} else if (c.contains("blue")) {
			turtle.setColour(new Color(0, 0, 250));
		} else {
			System.out.println("Cannot recongise your input colour");
			JOptionPane.showMessageDialog(null, "Invalid colour, try again");
		}
	}

	/**
	 * if the turtle already exists in the list, it moves with the given distance
	 * 
	 * @param name
	 * @param distance
	 */
	public void moveForward(String name, int distance) {

		Turtle turtle = searchTurtle(name);
		if (turtle == null)
			return;

		turtle.moveForward(distance);
	}
	/**
	 * clear the content of the graphic panel with the background colour
	 */
	public void clear() {

		Graphics g = bufferedImage.getGraphics();
		g.setColor(backgroundColour);
		g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
	}
	/**
	 * use a FOR loop to draw each turtle
	 */
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(bufferedImage, 0, 0, null);
		for (Turtle turtle : turtles) {
			turtle.draw(g);
		}
	}

}
