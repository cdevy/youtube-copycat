package codingweek2016.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Arrays;

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
import extraction.GetJarResources;

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
	private GetJarResources jar = new GetJarResources("youtubeCopycat.jar");

	
	public UploadView(MainWindow mW) {
		mainWindow = mW;
		mainMenu = new MainMenu(mainWindow);
		mainMenu.setBackground(Color.WHITE);
		
		pathField.setPreferredSize(new Dimension(300,25));
		nameField.setPreferredSize(new Dimension(300,25));
		statusField.setPreferredSize(new Dimension(300,25));
		description.setPreferredSize(new Dimension(300,100));
		tags.setPreferredSize(new Dimension(300,100));
		
		this.setLayout(new BorderLayout());
		
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(50, 20, 70, 20));
		
		pathField.setEnabled(false);
		pathField.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent event) {
        		pathField.setEnabled(true);
        		pathField.requestFocus();
            }
        });
		pathField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (pathField.getText().equals("Path of the video to upload")){
					pathField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (pathField.getText().equals("")){
					pathField.setText("Path of the video to upload");
					pathField.setEnabled(false);
				}
			}
        });
		
		nameField.setEnabled(false);
		nameField.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent event) {
        		nameField.setEnabled(true);
        		nameField.requestFocus();
            }
        });
		nameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (nameField.getText().equals("Name")){
					nameField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (nameField.getText().equals("")){
					nameField.setText("Name");
					nameField.setEnabled(false);
				}
			}
        });
		
		statusField.setEnabled(false);
		statusField.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent event) {
        		statusField.setEnabled(true);
        		statusField.requestFocus();
            }
        });
		statusField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (statusField.getText().equals("Status (public, private or unlisted)")){
					statusField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (statusField.getText().equals("")){
					statusField.setText("Status (public, private or unlisted)");
					statusField.setEnabled(false);
				}
			}
        });
		
		description.setEnabled(false);
		description.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent event) {
        		description.setEnabled(true);
        		description.requestFocus();
            }
        });
		description.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (description.getText().equals("Description")){
					description.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (description.getText().equals("")){
					description.setText("Description");
					description.setEnabled(false);
				}
			}
        });
		
		tags.setEnabled(false);
		tags.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent event) {
        		tags.setEnabled(true);
        		tags.requestFocus();
            }
        });
		tags.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (tags.getText().equals("Tags, separated with commas")){
					tags.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (tags.getText().equals("")){
					tags.setText("Tags, separated with commas");
					tags.setEnabled(false);
				}
			}
        });
		
		Image upload = Toolkit.getDefaultToolkit().createImage(jar.getResource("icons/uploadIcon.png"));
		ImageIcon img = new ImageIcon(upload);
		uploadButton.setIcon(new ImageIcon(img.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		
		uploadButton.setPreferredSize(new Dimension(120,30));
		
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
		
		uploadButton.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseEntered(MouseEvent arg0) {
				uploadButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				uploadButton.setBackground(Color.WHITE);
				uploadButton.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.red));
			}

			public void mouseExited(MouseEvent arg0) {
				uploadButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				uploadButton.setBackground(new JButton().getBackground());
				uploadButton.setBorder(new JButton().getBorder());
			}

			public void mousePressed(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseReleased(MouseEvent arg0) {
				// Do nothing
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
		north.setBackground(Color.WHITE);
		
		this.add(mainMenu, BorderLayout.WEST);
		
		Image img2 = Toolkit.getDefaultToolkit().createImage(jar.getResource("youtube.png"));
		ImageIcon icon = new ImageIcon(img2);
		
		yTImg = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH)));
		north.add(yTImg);
		
		this.add(north,BorderLayout.NORTH);
		this.add(mainPanel,BorderLayout.CENTER);
	}

}
