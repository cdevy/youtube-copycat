package codingweek2016;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import codingweek2016.view.SettingsView;
import extraction.GetJarResources;
import codingweek2016.view.MyUploadsView;
import codingweek2016.view.SearchView;
import codingweek2016.view.TrendingView;
import codingweek2016.view.UploadView;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {
	
	private GetJarResources jar = new GetJarResources("youtubeCopycat.jar");
		
	private JPanel logPanel = new JPanel();

	private JButton myUploadsButton = new JButton();
	private JButton uploadButton = new JButton();
	private JButton searchButton = new JButton();
	private JButton trendingButton = new JButton();
	private JButton settingsButton = new JButton();
	
	private Dimension dim4Buttons = new Dimension(140,30);

	private MainWindow mainWindow;

	
	public MainMenu(MainWindow mW) {
		mainWindow = mW;
		
		GridLayout layout = new GridLayout(5,1);
		layout.setVgap(10);
		logPanel.setLayout(layout);
		
		searchButton.setPreferredSize(dim4Buttons);
		myUploadsButton.setPreferredSize(dim4Buttons);
		uploadButton.setPreferredSize(dim4Buttons);
		trendingButton.setPreferredSize(dim4Buttons);
		settingsButton.setPreferredSize(dim4Buttons);
		
		Image trends = Toolkit.getDefaultToolkit().createImage(jar.getResource("icons/trendsIcon.png"));
		Image settings = Toolkit.getDefaultToolkit().createImage(jar.getResource("icons/settingsIcon.png"));
		Image search = Toolkit.getDefaultToolkit().createImage(jar.getResource("icons/searchIcon.png"));
		Image upload = Toolkit.getDefaultToolkit().createImage(jar.getResource("icons/uploadIcon.png"));
		Image myuploads = Toolkit.getDefaultToolkit().createImage(jar.getResource("icons/myuploadsIcon.png"));
		
		ImageIcon img;
		
		img = new ImageIcon(upload);
		uploadButton.setIcon(new ImageIcon(img.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		
		img = new ImageIcon(myuploads);
		myUploadsButton.setIcon(new ImageIcon(img.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH)));
		
		img = new ImageIcon(search);
		searchButton.setIcon(new ImageIcon(img.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		
		img = new ImageIcon(trends);
		trendingButton.setIcon(new ImageIcon(img.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		
		img = new ImageIcon(settings);
		settingsButton.setIcon(new ImageIcon(img.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
				
		searchButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	mainWindow.setMainView(new SearchView(mainWindow));
            }
        });
		
		searchButton.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseEntered(MouseEvent arg0) {
				searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				searchButton.setBackground(Color.WHITE);
				searchButton.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.red));
			}

			public void mouseExited(MouseEvent arg0) {
				searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				searchButton.setBackground(new JButton().getBackground());
				searchButton.setBorder(new JButton().getBorder());
			}

			public void mousePressed(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseReleased(MouseEvent arg0) {
				// Do nothing
			}
        });
		
		myUploadsButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	mainWindow.setMainView(new MyUploadsView(mainWindow));
            }
        });
		
		myUploadsButton.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseEntered(MouseEvent arg0) {
				myUploadsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				myUploadsButton.setBackground(Color.WHITE);
				myUploadsButton.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.red));
			}

			public void mouseExited(MouseEvent arg0) {
				myUploadsButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				myUploadsButton.setBackground(new JButton().getBackground());
				myUploadsButton.setBorder(new JButton().getBorder());
			}

			public void mousePressed(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseReleased(MouseEvent arg0) {
				// Do nothing
			}
        });		

		uploadButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	mainWindow.setMainView(new UploadView(mainWindow));
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
		
		trendingButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	try {
					mainWindow.setMainView(new TrendingView(mainWindow));
				} catch (IOException e1) {
					System.out.println(e1);
					e1.printStackTrace();
				}
            }
        });
		
		trendingButton.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseEntered(MouseEvent arg0) {
				trendingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				trendingButton.setBackground(Color.WHITE);
				trendingButton.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.red));
			}

			public void mouseExited(MouseEvent arg0) {
				trendingButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				trendingButton.setBackground(new JButton().getBackground());
				trendingButton.setBorder(new JButton().getBorder());
			}

			public void mousePressed(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseReleased(MouseEvent arg0) {
				// Do nothing
			}
        });
		
		settingsButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	mainWindow.setMainView(new SettingsView(mainWindow));
            }
        });
		
		settingsButton.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseEntered(MouseEvent arg0) {
				settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				settingsButton.setBackground(Color.WHITE);
				settingsButton.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.red));
			}

			public void mouseExited(MouseEvent arg0) {
				settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				settingsButton.setBackground(new JButton().getBackground());
				settingsButton.setBorder(new JButton().getBorder());
			}

			public void mousePressed(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseReleased(MouseEvent arg0) {
				// Do nothing
			}
        });
		
		this.setLayout(new FlowLayout());
		
		recreateMenuPanel(false);

		this.add(logPanel);
	}
	
	public void recreateMenuPanel(boolean recreate) {
		if (recreate) {
			logPanel.removeAll();
			logPanel.revalidate();
			logPanel.repaint();
		}

		searchButton.setText("Search");
		settingsButton.setText("Settings");
		trendingButton.setText("Trends");
		uploadButton.setText("Upload");
		myUploadsButton.setText("My Uploads");

		logPanel.add(searchButton);
		logPanel.add(trendingButton);
		logPanel.add(myUploadsButton);
		logPanel.add(uploadButton);
		logPanel.add(settingsButton);
		logPanel.setBackground(Color.WHITE);
	}

}
