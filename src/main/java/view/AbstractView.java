package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import codingweek2016.model.Video;

@SuppressWarnings("serial")
public abstract class AbstractView extends JPanel implements Observer {
	
	private List<Video> videos = new ArrayList<Video>();
	
	private static long NUMBER_OF_VIDEOS_RETURNED=25;

	private int longueur;
	private int largeur;
	
	
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


	public long getNUMBER_OF_VIDEOS_RETURNED() {
		return NUMBER_OF_VIDEOS_RETURNED;
	}


	public static void setNUMBER_OF_VIDEOS_RETURNED(
			long nUMBER_OF_VIDEOS_RETURNED) {
		NUMBER_OF_VIDEOS_RETURNED = nUMBER_OF_VIDEOS_RETURNED;
	}

}
