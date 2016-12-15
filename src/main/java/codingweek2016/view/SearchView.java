package codingweek2016.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.mortbay.resource.JarResource;


import codingweek2016.MainMenu;
import codingweek2016.MainWindow;
import codingweek2016.model.SearchRequest;
import extraction.GetJarResources;

@SuppressWarnings("serial")
public class SearchView extends AbstractView {
	
	private JLabel logo;
	//private URL youtubeiconurl = getClass().getResource("./youtube.png");

	private GetJarResources jar = new GetJarResources("youtubeCopycat.jar");
	
	private SearchRequest request;
	
	private JButton searchButton = new JButton("Search");
	private JTextField searchField = new JTextField();

	private JEditorPane resultHead = new JEditorPane();
	private JPanel resultGrid = new JPanel();
	
	public SearchView(MainWindow mW) {
		mainWindow = mW;
		mainMenu = new MainMenu(mainWindow);
		
		request = new SearchRequest();
		request.addObserver(this);
		
		this.setLayout(new BorderLayout());
		
		searchField.setPreferredSize(new Dimension(300,25));
		
		searchButton.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseEntered(MouseEvent arg0) {
				searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent arg0) {
				searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseReleased(MouseEvent arg0) {
				// Do nothing
			}
        });
		
		searchButton.addActionListener(new ActionListener() {
			  
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
	            			head += "<p><center><b>First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + text + "\".<b/><br/><br/><br/><br/>";
	            		}
	            		
	            		head += "</body></html>";
	            		
	            		resultHead.setOpaque(false);
	            		resultHead.setContentType("text/html");
	            		resultHead.setText(head);
	            		
	            		BorderLayout grid = new BorderLayout();
	            		resultGrid.removeAll();
	            		resultGrid.setLayout(grid);
	            		
	            		JScrollPane scrollBar = new JScrollPane(request.display());
	            		scrollBar.setBorder(null);
	            		
	            		resultGrid.add(resultHead, BorderLayout.NORTH);
	            		resultGrid.add(scrollBar, BorderLayout.CENTER);
	            		resultGrid.revalidate();
	            		resultGrid.repaint();
	                }
            	}
            }
        });		
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout());
		
		//ImageIcon img = new ImageIcon(ImageIO.read(new File(youtubeiconurl.getPath())));
		Image img = Toolkit.getDefaultToolkit().createImage(jar.getResource("youtube.png"));
		ImageIcon icon = new ImageIcon(img);
		logo = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH)));
		searchPanel.add(logo);
		
		
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		
		this.add(searchPanel, BorderLayout.NORTH);
		this.add(resultGrid, BorderLayout.CENTER);
		this.add(mainMenu,BorderLayout.WEST);
	}

}
