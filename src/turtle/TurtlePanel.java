package turtle;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The TurtelPanel contains a GraphicPanel and a command TextField. It reads an
 * input file and checks the command inputs.
 * Each turtle has a name.
 * If the turtle moves around with its pen down, it leaves a trial on the panel.
 * The colour of the turtle's trail can be changed to red, green, blue or black
 * @author Lam
 *
 */
public class TurtlePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GraphicPanel graphics;

	private enum TurtleCommand {
		TURTLE, MOVE, LEFT, RIGHT, PEN, COLOUR
	}

	public TurtlePanel(JTextField command) {
		graphics = new GraphicPanel();
		add(graphics);
		add(command);
		// when 'Enter' is clicked, it reads the text field and processes the input
		command.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkCommand(command.getText());
				command.setText("");
				graphics.repaint();
			}
		});
	}

	/**
	 * checkCommand takes a command and processes it
	 * 
	 * @param input the command to control the turtle
	 */
	private void checkCommand(String input) {
		String[] parts = input.split(" ");
		String turtleName;
		Turtle new_turtle = null;
		
		try {
			turtleName = input.split(" ")[1];
			if (parts.length == 3) { // search if the given turtle name exists
				new_turtle = graphics.searchTurtle(turtleName);
			}
			TurtleCommand command = TurtleCommand.valueOf(parts[0].toUpperCase()); // Case-insensitive
			switch (command) {
			case TURTLE:
				// create a new turtle given a name
				new_turtle = new Turtle(turtleName, getWidth() / 2, 352);
				graphics.addTurtle(new_turtle);
				break;
			case MOVE:
				// move forward with a given step
				int distance = Integer.parseInt(input.split(" ")[2]);
				new_turtle.moveForward(distance);
				break;
			case LEFT:
				// turn to left with a given degree
				int leftDegree = Integer.parseInt(input.split(" ")[2]);
				new_turtle.rotateTurtle(360 - leftDegree);
				break;
			case RIGHT:
				// turn to right with a given degree
				int rightDegree = Integer.parseInt(input.split(" ")[2]);
				new_turtle.rotateTurtle(rightDegree);
				break;
			case PEN:
				// update if the pen is up or down
				String upDown = input.split(" ")[2];
				if (upDown.contains("up")) {
					new_turtle.setPenUp(true);
				} else if (upDown.contains("down")) {
					new_turtle.setPenUp(false);
				}
				break;
			case COLOUR:
				// change the colour of the turtle trails
				String newColour = input.split(" ")[2];
				changeColour(new_turtle, newColour);
				break;
			}
			// catch the exception if the number format is wrong or the array index is out
			// of bound
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
			System.out.println("Invalid command format. Try again.");
			JOptionPane.showMessageDialog(null, "Invalid command format. Try again.");
		}
	}

	private void changeColour(Turtle turtle, String c) {
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
	
	public void readFile(File file) throws IllegalArgumentException {
		if (!file.getName().toLowerCase().endsWith(".txt")) {
			throw new IllegalArgumentException("The file must be a .txt file");
		}

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));

			String line;
			while ((line = reader.readLine()) != null) {
				// if the line starts with '#', then it is a comment and skip the line
				if (!line.contains("#")) {
					System.out.println("read line " + line);
					checkCommand(line);
					graphics.repaint();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}