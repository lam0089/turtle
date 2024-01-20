package turtle;

import javax.swing.*;
import java.awt.*;
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
public class MyTurtleGraphicsMain extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int width = 800;
	private int height = 800;

	private JButton findFileBttn = new JButton(); // a button to search for an input file
	private JButton executeFileBttn = new JButton(); // a button to execute the input file
	JLabel fileInputLabel = new JLabel();
	private File selectedFile = null; // a local file
	private JTextField fileText = new JTextField(16);
	TurtlePanel panel; // a Turtle panel
	JPanel inputPanel;
	JPanel filePanel;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new MyTurtleGraphicsMain().setVisible(true);
		});
	}

	/**
	 * constructor: creates label, text, buttons
	 */
	public MyTurtleGraphicsMain() {
		setTitle("-- Simple Turtle Graphics --");
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JTextField command = new JTextField(15);
		panel = new TurtlePanel(command);
		panel.setPreferredSize(new Dimension(width, 750));

		JLabel label = new JLabel("Enter your command in the textfield:");
		fileInputLabel.setText("Input File:");

		findFileBttn.addActionListener(this);
		findFileBttn.setText("Search");

		executeFileBttn.addActionListener(this);
		executeFileBttn.setText("Execute File");

		fileText.setEditable(false);

		inputPanel = new JPanel();
		inputPanel.setPreferredSize(new Dimension(width, 50));
		inputPanel.add(label, BorderLayout.EAST);
		inputPanel.add(command, BorderLayout.SOUTH);

		filePanel = new JPanel();
		filePanel.setPreferredSize(new Dimension(width, 50));
		filePanel.add(fileInputLabel);
		filePanel.add(fileText);
		filePanel.add(findFileBttn);
		filePanel.add(executeFileBttn);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(panel, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.SOUTH);
		add(filePanel, BorderLayout.NORTH);
	}

	/**
	 * The function is to choose a local file, it updates 'selectedFile' value
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
	 * When 'Search' button is clicked, a file can be selected When 'Execute' button
	 * is clicked, the selected file will be read
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
				panel.readFile(selectedFile);
				// reset a file
				fileText.setText("");
				selectedFile = null;
			} else {
				JOptionPane.showMessageDialog(null, "Please select a file.");
			}
		}
	}

}
