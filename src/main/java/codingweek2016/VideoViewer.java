package codingweek2016;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

@SuppressWarnings("serial")
public class VideoViewer extends JFrame {
	
	public VideoViewer(String title, final String id) {
		super(title);
		
		setPreferredSize(new Dimension(600,500));
				
		setLayout(new BorderLayout());
		
		final JFXPanel jfxPanel = new JFXPanel();
		Platform.runLater(new Runnable() {
            public void run() {
                initFX(jfxPanel, id);
            }			
        });
		
		this.add(jfxPanel);
		
		pack();
		setVisible(true);
	}
	
	private static void initFX(JFXPanel jfxPanel, String id) {
		/*StringBuilder sb = new StringBuilder();
	    sb.append("<embed width=\"420\" height=\"315\"src=\"http://www.youtube.com/embed/"+id+"?autoplay=1\">");//("<video width=\"320\" height=\"240\" controls><source src=\"https://www.youtube.com/embed/"+id+"?autoplay=1\" type=\"video/mp4\"></video>");
	    String fontText = sb.toString();
	    WebView fontWebView = new WebView();;
		fontWebView.getEngine().loadContent(fontText);
		jfxPanel.setScene(new Scene(fontWebView));*/
		
		
		WebView view = new WebView();
		WebEngine webEngine = view.getEngine();
		webEngine.load("http://www.youtube.com/embed/"+id+"?autoplay=1");
		view.setPrefSize(200, 200);
        jfxPanel.setScene(new Scene(view));
 	}
	
}
