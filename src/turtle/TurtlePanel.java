package turtle;

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
 * The TurtelPanel contains a GraphicPanel and a text field.
 * It reads an input file and checks the command inputs. 
 * @author Lam
 *
 */
public class TurtlePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GraphicPanel graphics = new GraphicPanel();

	public TurtlePanel(JTextField command) {
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
	 * @param input the command to control the turtle
	 */
	private void checkCommand(String input) {
		Turtle new_turtle = null;
		try {
			// create a new turtle given a name
			if (input.contains("turtle")) {
				String name = input.split(" ")[1];
				new_turtle = new Turtle(name, getWidth() / 2, 352);
				graphics.addTurtle(new_turtle);
			// update if the pen is up or down
			} else if (input.contains("pen")) {
				String name = input.split(" ")[1];
				String upDown = input.split(" ")[2];
				graphics.penUp(name, upDown);
			// turn to left with a given degree
			} else if (input.contains("left")) {
				String name = input.split(" ")[1];
				int degree = Integer.parseInt(input.split(" ")[2]);
				graphics.rotate(name, 360 - degree);
			// turn to right with a given degree
			} else if (input.contains("right")) {
				String name = input.split(" ")[1];
				int degree = Integer.parseInt(input.split(" ")[2]);
				graphics.rotate(name, degree);
			// move forward with a given step
			} else if (input.contains("move")) {
				String name = input.split(" ")[1];
				int distance = Integer.parseInt(input.split(" ")[2]);
				graphics.moveForward(name, distance);
			// change the colour of the turtle lines
			} else if (input.contains("colour")) {
				String name = input.split(" ")[1];
				String c = input.split(" ")[2];
				graphics.changeColour(name, c);
			} else {
				JOptionPane.showMessageDialog(null, "Invalid command, try again");
			}
			
		// catch the exception if the number format is wrong or the array index is out of bound
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
			System.out.println("Invalid command format. Try again.");
			JOptionPane.showMessageDialog(null, "Invalid command format. Try again.");
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
				if (!line.contains("#")) {		// if the line starts with '#', then it is a comment and skip the line
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