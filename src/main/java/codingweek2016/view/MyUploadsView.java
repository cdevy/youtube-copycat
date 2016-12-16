package codingweek2016.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import codingweek2016.MainMenu;
import codingweek2016.MainWindow;
import codingweek2016.model.MyUploads;
import extraction.GetJarResources;

@SuppressWarnings("serial")
public class MyUploadsView extends AbstractView {
	
	private GetJarResources jar = new GetJarResources("youtubeCopycat.jar");

	private MyUploads playlist;
	private JLabel yTImg;
	
	public MyUploadsView(MainWindow mW){
		mainWindow = mW;
		mainMenu = new MainMenu(mainWindow);
		mainMenu.setBackground(Color.WHITE);
		
		this.setLayout(new BorderLayout());
		
		try {
			playlist = new MyUploads();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JPanel playlists = new JPanel();
		playlists.setLayout(new BorderLayout());

		JEditorPane editorPane = new JEditorPane();
		editorPane.setOpaque(false);
		editorPane.setContentType("text/html");
		editorPane.setEditable(false);
		if (playlist.getNbOfVideos()!=0) {
			editorPane.setText("<html><body><p><center><b>Total videos uploaded: " + playlist.getNbOfVideos() + ".<br/><br/><br/><br/><b/></center></p></body></html>");
		} else {
			editorPane.setText("<html><body><p><center><b>No video uploaded.<b/></center></p></body></html>");
		}

		playlists.add(editorPane, BorderLayout.NORTH);

		JPanel scroll = new JPanel();
		scroll.setLayout(new BorderLayout());
		scroll.setBorder(BorderFactory.createMatteBorder(10,10,10,10,(new JButton()).getBackground()));
		
		final JScrollPane scrollPane = new JScrollPane(playlist.display());
		scrollPane.setBorder(null);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
  		   public void run() { 
  			   	scrollPane.getVerticalScrollBar().setValue(0);
  			 	scrollPane.getHorizontalScrollBar().setValue(0);
  				scrollPane.getVerticalScrollBar().setUnitIncrement(16);
  		   }
  		});
		
		scroll.add(scrollPane, BorderLayout.CENTER);
        playlists.add(scroll, BorderLayout.CENTER);
		
		JPanel north = new JPanel();
		north.setLayout(new FlowLayout());
		north.setBackground(Color.WHITE);
		
		this.add(mainMenu,BorderLayout.WEST);
		this.add(playlists, BorderLayout.CENTER);
		
		Image img2 = Toolkit.getDefaultToolkit().createImage(jar.getResource("youtube.png"));
		ImageIcon icon = new ImageIcon(img2);
		yTImg = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH)));
		north.add(yTImg);
		
		this.add(north,BorderLayout.NORTH);
	}
}
