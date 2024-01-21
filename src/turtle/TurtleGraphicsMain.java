package turtle;
/**
 * This Java program simulates virtual 'turtles' which move around a window.
 * A turtle moves when there is an input command. It has a 'pen', which can be 'up' or 'down'.
 * When the turtle moves around and its pen is down, it leaves a coloured trail.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.StreamSupport;

/**
 * MyTurtleGraphics is a JFrame whose size is 800 x 800. It contains
 * TurtelPanel, input panel and file panel
 * 
 * @author Lam
 *
 */
public class TurtleGraphicsMain extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static final int FRAME_WIDTH = 800;	// the default width of the Frame
	public static final int FRAME_HEIGHT = 800;	// the default height of the Frame

	private JButton findFileBttn = new JButton(); 		// a button to search for an input file
	private JButton executeFileBttn = new JButton(); 	// a button to execute the input file
	JLabel fileInputLabel = new JLabel();
	JLabel commandInputLabel;
	private File selectedFile = null; 					// a local file
	private JTextField fileText = new JTextField(16);	// a textfield to display the chosen local file
	JTextField command;									// a textfield to receive commands
	TurtlePanel turtlePanel; // a Turtle panel
	JPanel inputPanel;
	JPanel filePanel;

	public static void main(String[] args) throws InterruptedException {
		SwingUtilities.invokeLater(() -> {
			// Code to be executed on the Event Dispatch Thread
			new TurtleGraphicsMain().setVisible(true);
		});
	}

	/**
	 * constructor: creates label, text, buttons
	 */
	public TurtleGraphicsMain() {
		setTitle("-- Simple Turtle Graphics --");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		command = new JTextField(15);
		turtlePanel = new TurtlePanel(command);

		commandInputLabel = new JLabel("Enter your command in the textfield:");
		fileInputLabel.setText("Input File:");

		findFileBttn.addActionListener(this);
		findFileBttn.setText("Search");

		executeFileBttn.addActionListener(this);
		executeFileBttn.setText("Execute File");

		fileText.setEditable(false);

		// inputPanel contains a label and a textfield  
		inputPanel = new JPanel();
		inputPanel.add(commandInputLabel);
		inputPanel.add(command);

		// filePanel contains a label, textfield for a local file, a 'Search' button and a 'Execute' button
		filePanel = new JPanel();
		filePanel.add(fileInputLabel);
		filePanel.add(fileText);
		filePanel.add(findFileBttn);
		filePanel.add(executeFileBttn);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(turtlePanel);
		add(inputPanel);
		add(filePanel);
	}

	/**
	 * The function is to choose a local file, the 'selectedFile' value stores the location of the file.
	 */
	private void chooseLocalFile() {
		JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));

		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			Path path = Paths.get(jfc.getSelectedFile().getAbsolutePath());
			String[] splitPath = StreamSupport.stream(path.spliterator(), false).map(Path::toString)
					.toArray(String[]::new);
			this.selectedFile = new File(splitPath[splitPath.length - 1]);

		}
	}

	@Override
	/**
	 * When 'Search' button is clicked, a file can be selected. When 'Execute' button
	 * is clicked, the selected file will be read.
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == findFileBttn) {
			chooseLocalFile();
			if (selectedFile != null) {
				fileText.setText(selectedFile.toString());
			}
		} else if (event.getSource() == executeFileBttn) {
			if (selectedFile != null) {
				// read the input file
				turtlePanel.readFile(selectedFile);
				// reset a file
				fileText.setText("");
				selectedFile = null;
			} else {
				JOptionPane.showMessageDialog(null, "Please select a file.");
			}
		}
	}

}
