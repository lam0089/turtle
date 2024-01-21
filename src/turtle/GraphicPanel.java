package turtle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * GraphicPanel is a drawing area which displays all turtles and their trails.
 * The background of the panel is white.
 * It contains a list of turtle created from the local file or input commands.
 * 
 * @author Lam
 *
 */

public class GraphicPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<Turtle> turtles = new ArrayList<Turtle>();

	private BufferedImage bufferedImage;
	private final Color backgroundColour = Color.WHITE;

	GraphicPanel() {

		setPreferredSize(new Dimension(TurtleGraphicsMain.FRAME_WIDTH, TurtleGraphicsMain.FRAME_HEIGHT));

		bufferedImage = new BufferedImage(TurtleGraphicsMain.FRAME_WIDTH, TurtleGraphicsMain.FRAME_HEIGHT,
				BufferedImage.TYPE_INT_RGB);

		setMaximumSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));

		clear();
	}

	/**
	 * add a new turtle in the ArrayList
	 * 
	 * @param new_turtle the newly created turtle
	 */
	public void addTurtle(Turtle new_turtle) {
		System.out.println("add a new Turtle " + new_turtle.getName());
		turtles.add(new_turtle);
	}

	/**
	 * search for a turtle from the ArrayList
	 * 
	 * @param name the name of turtle
	 * @return the turtle if it exists in the ArrayList
	 */
	public Turtle searchTurtle(String name) {
		for (Turtle turtle : turtles) {
			String n = turtle.getName();
			if (n.equals(name)) {
				return turtle;
			}
		}
		return null;
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
