package codingweek2016.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import codingweek2016.MainMenu;
import codingweek2016.MainWindow;
import codingweek2016.model.UploadVideo;

@SuppressWarnings("serial")
public class UploadView extends AbstractView {
	
	private JLabel yTImg;
	private JPanel mainPanel = new JPanel();
	private JTextField pathField = new JTextField("Path of the video to upload");
	private JTextField nameField = new JTextField("Name");
	private JTextField statusField = new JTextField("Status (public, private or unlisted)");
	private JTextArea description = new JTextArea("Description");
	private JTextArea tags = new JTextArea("Tags, separated with commas");
	private JButton uploadButton = new JButton("Upload");
	
	public UploadView(MainWindow mW) {
		mainWindow = mW;
		mainMenu = new MainMenu(mainWindow);
		
		pathField.setPreferredSize(new Dimension(300,25));
		nameField.setPreferredSize(new Dimension(300,25));
		statusField.setPreferredSize(new Dimension(300,25));
		description.setPreferredSize(new Dimension(300,100));
		tags.setPreferredSize(new Dimension(300,100));
		
		this.setLayout(new BorderLayout());
		
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(50, 20, 70, 20));
		
		uploadButton.addActionListener(new ActionListener() {
			  
	            public void actionPerformed(ActionEvent e) {
	            	try {
						new UploadVideo(pathField.getText(), statusField.getText(), nameField.getText(), description.getText(), Arrays.asList(tags.getText().split(",")));
					} catch (IOException e1) {
						System.out.println(e1);
						e1.printStackTrace();
					}
	            }
	            	
		});
		
		JPanel pathPanel = new JPanel();
		pathPanel.add(pathField);
		JPanel namePanel = new JPanel();
		namePanel.add(nameField);
		JPanel statusPanel = new JPanel();
		statusPanel.add(statusField);
		JPanel descriptionPanel = new JPanel();
		descriptionPanel.add(description);
		JPanel tagPanel = new JPanel();
		tagPanel.add(tags);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(uploadButton);
		
		mainPanel.add(pathPanel);
		mainPanel.add(namePanel);
		mainPanel.add(statusPanel);
		mainPanel.add(descriptionPanel);
		mainPanel.add(tagPanel);
		mainPanel.add(buttonPanel);
		
		JPanel north = new JPanel();
		north.setLayout(new FlowLayout());
		this.add(mainMenu, BorderLayout.WEST);
		try {
			ImageIcon img = new ImageIcon(ImageIO.read(new File("src/main/resources/youtube.png")));
			yTImg = new JLabel(new ImageIcon(img.getImage().getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH)));
			north.add(yTImg);
			this.add(north,BorderLayout.NORTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.add(mainPanel,BorderLayout.CENTER);
	}

}
