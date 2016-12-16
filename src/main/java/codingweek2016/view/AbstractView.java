package codingweek2016.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import codingweek2016.MainWindow;
import codingweek2016.model.Video;

@SuppressWarnings("serial")
public abstract class AbstractView extends JPanel implements Observer {
	protected MainWindow mainWindow;
	protected JPanel mainMenu;
	
	protected List<Video> videos = new ArrayList<Video>();
		
	
	@SuppressWarnings("unchecked")
	public void update(Observable observable, Object videos) {
		if (videos instanceof List<?>) {
			this.setVideos((List<Video>) videos);
		}
	}


	public List<Video> getVideos() {
		return videos;
	}


	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
}
