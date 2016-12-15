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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import codingweek2016.MainMenu;
import codingweek2016.MainWindow;
import extraction.GetJarResources;

@SuppressWarnings("serial")
public class SettingsView extends AbstractView {
	
	private GetJarResources jar = new GetJarResources("youtubeCopycat.jar");
	
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
		mainMenu.setBackground(Color.WHITE);
		
		this.setLayout(new BorderLayout());
		
		BoxLayout layout = new BoxLayout(settingsGrid,BoxLayout.Y_AXIS);
		settingsGrid.setLayout(layout);
		settingsGrid.setBorder(new EmptyBorder(100, 20, 120, 20));
		
		maxvidField.setPreferredSize(new Dimension(300,25));
		heightField.setPreferredSize(new Dimension(300,25));
		widthField.setPreferredSize(new Dimension(300,25));
		apikeyField.setPreferredSize(new Dimension(300,25));
		
		Image upload = Toolkit.getDefaultToolkit().createImage(jar.getResource("icons/saveIcon.png"));
		ImageIcon img = new ImageIcon(upload);
		saveButton.setIcon(new ImageIcon(img.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		
		saveButton.setPreferredSize(new Dimension(120,30));
		
		saveButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	mainWindow.setMainView(new SearchView(mainWindow));
            }
        });
		
		saveButton.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseEntered(MouseEvent arg0) {
				saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				saveButton.setBackground(Color.WHITE);
				saveButton.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.red));
			}

			public void mouseExited(MouseEvent arg0) {
				saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				saveButton.setBackground(new JButton().getBackground());
				saveButton.setBorder(new JButton().getBorder());
			}

			public void mousePressed(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseReleased(MouseEvent arg0) {
				// Do nothing
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
		
		heightField.setEnabled(false);
		heightField.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent event) {
        		heightField.setEnabled(true);
        		heightField.requestFocus();
            }
        });
		heightField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (heightField.getText().equals("Window height")){
					heightField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (heightField.getText().equals("")){
					heightField.setText("Window height");
					heightField.setEnabled(false);
				}
			}
        });
		
		widthField.setEnabled(false);
		widthField.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent event) {
        		widthField.setEnabled(true);
        		widthField.requestFocus();
            }
        });
		widthField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (widthField.getText().equals("Window width")){
					widthField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (widthField.getText().equals("")){
					widthField.setText("Window width");
					widthField.setEnabled(false);
				}
			}
        });
		
		maxvidField.setEnabled(false);
		maxvidField.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent event) {
        		maxvidField.setEnabled(true);
        		maxvidField.requestFocus();
            }
        });
		maxvidField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (maxvidField.getText().equals("Number of videos to be displayed")){
					maxvidField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (maxvidField.getText().equals("")){
					maxvidField.setText("Number of videos to be displayed");
					maxvidField.setEnabled(false);
				}
			}
        });
		
		apikeyField.setEnabled(false);
		apikeyField.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent event) {
        		apikeyField.setEnabled(true);
        		apikeyField.requestFocus();
            }
        });
		apikeyField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (apikeyField.getText().equals("API key")){
					apikeyField.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (apikeyField.getText().equals("")){
					apikeyField.setText("API key");
					apikeyField.setEnabled(false);
				}
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
		north.setBackground(Color.WHITE);
		
		this.add(mainMenu,BorderLayout.WEST);
		this.add(settingsGrid, BorderLayout.CENTER);
		
		Image img2 = Toolkit.getDefaultToolkit().createImage(jar.getResource("youtube.png"));
		ImageIcon icon = new ImageIcon(img2);
		yTImg = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH)));
		north.add(yTImg);
		
		this.add(north,BorderLayout.NORTH);
	}

}
