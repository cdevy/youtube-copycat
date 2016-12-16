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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import codingweek2016.App;
import codingweek2016.MainMenu;
import codingweek2016.MainWindow;
import codingweek2016.model.SearchRequest;
import extraction.GetJarResources;

@SuppressWarnings("serial")
public class SearchView extends AbstractView {
	
	private JLabel logo;

	private GetJarResources jar = new GetJarResources("youtubeCopycat.jar");
	
	private SearchRequest request;
	
	private JButton searchButton = new JButton("Search");
	private JTextField searchField = new JTextField();

	private JEditorPane resultHead = new JEditorPane();
	private JPanel resultGrid = new JPanel();
	
	public SearchView(MainWindow mW) {
		mainWindow = mW;
		
		mainMenu = new MainMenu(mainWindow);
		mainMenu.setBackground(Color.WHITE);
		
		request = new SearchRequest();
		request.addObserver(this);
		
		this.setLayout(new BorderLayout());
		
		ActionListener search = new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	
            	String text = searchField.getText();
            	
            	if (text.length()>0) {
            		
	            	try {
						request.loadVideos(request.searchKeyWord(text));
					} catch (IOException e1) {
						System.out.println(e1);
						e1.printStackTrace();
					}
	
	            	if (videos != null) {
	            		
	            		String head = "<html><body>";
	            		
	            		if (!videos.iterator().hasNext()) {
	            			head += "<p><center><b> There aren't any results for your query.<b/></center></p>";
	            		} else {
	            			head += "<p><center><b>First " + App.NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + text + "\".<b/><br/><br/><br/><br/>";
	            		}
	            		
	            		head += "</body></html>";
	            		
	            		resultHead.setOpaque(false);
	            		resultHead.setContentType("text/html");
	            		resultHead.setEditable(false);
	            		resultHead.setText(head);
	            		
	            		BorderLayout grid = new BorderLayout();
	            		resultGrid.removeAll();
	            		resultGrid.setLayout(grid);
	            		
	            		final JScrollPane scrollBar = new JScrollPane(request.display());
	            		scrollBar.setBorder(null);
	            		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	              		   public void run() { 
	              			  scrollBar.getVerticalScrollBar().setValue(0);
	              			  scrollBar.getHorizontalScrollBar().setValue(0);
	              			  scrollBar.getVerticalScrollBar().setUnitIncrement(16);
	              		   }
	              		});
	            		
	            		resultGrid.add(resultHead, BorderLayout.NORTH);
	            		resultGrid.add(scrollBar, BorderLayout.CENTER);
	            		resultGrid.revalidate();
	            		resultGrid.repaint();
	                }
            	}
            }
        };
		
		searchField.setPreferredSize(new Dimension(300,30));
		searchField.addActionListener(search);
		
		Image img1 = Toolkit.getDefaultToolkit().createImage(jar.getResource("icons/searchIcon.png"));
		ImageIcon icon = new ImageIcon(img1);
		searchButton.setIcon(new ImageIcon(icon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		
		searchButton.setPreferredSize(new Dimension(120,30));
		
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
		
		searchButton.addActionListener(search);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout());
		
		Image img = Toolkit.getDefaultToolkit().createImage(jar.getResource("youtube.png"));
		ImageIcon icon2 = new ImageIcon(img);
		logo = new JLabel(new ImageIcon(icon2.getImage().getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH)));
		searchPanel.add(logo);
		

		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		
		searchPanel.setBackground(Color.WHITE);
		
		this.add(searchPanel, BorderLayout.NORTH);
		this.add(resultGrid, BorderLayout.CENTER);
		this.add(mainMenu,BorderLayout.WEST);
	}

}
