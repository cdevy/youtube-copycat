package codingweek2016.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import codingweek2016.MainMenu;
import codingweek2016.MainWindow;

@SuppressWarnings("serial")
public class SettingsView extends AbstractView {
	
	private JLabel yTImg;
	
	private JTextField heightField = new JTextField("Window height");
	private JTextField widthField = new JTextField("Window width");
	private JTextField maxvidField = new JTextField("Number of videos to be displayed");
	private JTextField apikeyField = new JTextField("API key");

	private JButton saveButton = new JButton("Save");
	private JPanel settingsGrid = new JPanel();
	
	public SettingsView(MainWindow mW) {
		mainWindow = mW;
		mainMenu = new MainMenu(mainWindow);
		
		this.setLayout(new BorderLayout());
		
		BoxLayout layout = new BoxLayout(settingsGrid,BoxLayout.Y_AXIS);
		settingsGrid.setLayout(layout);
		settingsGrid.setBorder(new EmptyBorder(100, 20, 120, 20));
		
		maxvidField.setPreferredSize(new Dimension(300,25));
		heightField.setPreferredSize(new Dimension(300,25));
		widthField.setPreferredSize(new Dimension(300,25));
		apikeyField.setPreferredSize(new Dimension(300,25));
		
		saveButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	mainWindow.setMainView(new SearchView(mainWindow));
            }
        });
		
		heightField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            }
        });
		
		widthField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            }
        });
		
		maxvidField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            }
        });
		
		apikeyField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            }
        });
		
		
		JPanel heightPanel = new JPanel();
		heightPanel.setLayout(new FlowLayout());
		heightPanel.add(heightField);
		settingsGrid.add(heightPanel);
		
		JPanel widthPanel = new JPanel();
		widthPanel.setLayout(new FlowLayout());
		widthPanel.add(widthField);
		settingsGrid.add(widthPanel);
		
		JPanel maxvidPanel = new JPanel();
		maxvidPanel.setLayout(new FlowLayout());
		maxvidPanel.add(maxvidField);
		settingsGrid.add(maxvidPanel);
		
		JPanel apikeyPanel = new JPanel();
		apikeyPanel.setLayout(new FlowLayout());
		apikeyPanel.add(apikeyField);
		settingsGrid.add(apikeyPanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(saveButton);
		settingsGrid.add(buttonPanel);
		
		JPanel north = new JPanel();
		north.setLayout(new FlowLayout());
		
		this.add(mainMenu,BorderLayout.WEST);
		this.add(settingsGrid, BorderLayout.CENTER);
		try {
			ImageIcon img = new ImageIcon(ImageIO.read(new File("src/main/resources/youtube.png")));
			yTImg = new JLabel(new ImageIcon(img.getImage().getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH)));
			north.add(yTImg);
			this.add(north,BorderLayout.NORTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
