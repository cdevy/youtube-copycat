package codingweek2016;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
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
            @Override
            public void run() {
                initFX(jfxPanel, id);
            }			
        });
		
		this.add(jfxPanel);
		
		pack();
		setVisible(true);
	}
	
	private static void initFX(JFXPanel jfxPanel, String id) {
		WebView view = new WebView();
		WebEngine webEngine = view.getEngine();
				webEngine.load(
				"http://www.youtube.com/embed/"+id+"?autoplay=0"
	    );
		view.setPrefSize(200, 200);
        jfxPanel.setScene(new Scene(view));
	}
	
}
