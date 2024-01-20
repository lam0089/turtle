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

public class TurtlePanel extends JPanel
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GraphicPanel graphics = new GraphicPanel();
    
    public TurtlePanel(JTextField command) {
        add(graphics);
        add(command);

        command.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
            	checkCommand(command.getText());
                command.setText("");
                graphics.repaint();
            }
        });
    }
    
    private void checkCommand(String input) {
    	Turtle new_turtle = null;
    	try {
    	if (input.contains("turtle")) 
    	{    		
    		
    		String name = input.split(" ")[1];
    		new_turtle = new Turtle(name, getWidth() / 2, 352);
    		graphics.addTurtle(new_turtle);
    		
    	}
    	else if (input.contains("pen"))
        {
 
    		String name = input.split(" ")[1];
        	String upDown = input.split(" ")[2];
            graphics.penUp(name, upDown);
        }
        else if (input.contains("left"))
        {
        	String name = input.split(" ")[1];
        	int degree = Integer.parseInt(input.split(" ")[2]);
            graphics.rotate(name, 360-degree);
        }
        else if (input.contains("right"))
        {
        	String name = input.split(" ")[1];
        	int degree = Integer.parseInt(input.split(" ")[2]);
            graphics.rotate(name, degree);
        }
        else if (input.contains("move"))
        {	
        	String name = input.split(" ")[1];
        	int distance = Integer.parseInt(input.split(" ")[2]);
            graphics.moveForward(name, distance);
        }
        else if (input.contains("colour"))
        {
        	String name = input.split(" ")[1];
        	String c = input.split(" ")[2];
        	graphics.changeColour(name, c);
        }
        else if (input.contains("reset"))
        {
            graphics.clear();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Invalid command, try again");
        }
    	} catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            System.out.println("Invalid command format. Try again.");
            JOptionPane.showMessageDialog(null, "Invalid command format. Try again.");
        }
    }
    
    public void readFile(File file) throws IllegalArgumentException {        
		if(!file.getName().toLowerCase().endsWith(".txt")) {
			throw new IllegalArgumentException("The file must be a .txt file");
		}
		
		BufferedReader reader = null;
		try{
			reader =new BufferedReader(new FileReader(file));
		
			String line;
			while((line = reader.readLine()) != null) {
				
				if(!line.contains("#")) {
					System.out.println("read line " + line);
					String[] s = line.split(" ");
					checkCommand(line);
					graphics.repaint();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

}