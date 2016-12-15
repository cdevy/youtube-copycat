package codingweek2016.model;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoSnippet;
import com.google.api.services.youtube.model.VideoStatus;
import com.google.common.collect.Lists;

public class UploadVideo extends Request {
	
	private static List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.upload");
	private static final String VIDEO_FILE_FORMAT = "video/*";
	
	boolean clickedButton = false;
	
	public UploadVideo(String videoPath, String videoStatus, String videoName, String videoDescription, List<String> videoTags) throws IOException {
		super(Authentification.authorize(scopes, "uploadvideo"),"youtube-uploadvideo");
		
		try {          
            //System.out.println("Uploading: " + SAMPLE_VIDEO_FILENAME);

            Video youtubeVideo = new Video();

            // Set video status (private, unlisted or public)
            VideoStatus status = new VideoStatus();
            status.setPrivacyStatus(videoStatus);
            youtubeVideo.setStatus(status);

            // Set video's metadata
            VideoSnippet snippet = new VideoSnippet();
            snippet.setTitle(videoName);
            snippet.setDescription(videoDescription);
            snippet.setTags(videoTags);
            
            youtubeVideo.setSnippet(snippet);

            InputStreamContent videoContent = new InputStreamContent(VIDEO_FILE_FORMAT,
                    new BufferedInputStream(new FileInputStream(videoPath)));//UploadVideo.class.getResourceAsStream(videoPath));

            // Insert the video
            YouTube.Videos.Insert videoInsert = youtube.videos().insert("snippet,statistics,status", youtubeVideo, videoContent);

            // Set the upload type and add an event listener.
            MediaHttpUploader uploader = videoInsert.getMediaHttpUploader();

            // Disable direct media upload
            uploader.setDirectUploadEnabled(false);

            // Call the API and upload the video.
            final Video returnedVideo = videoInsert.execute();
 
        } catch (GoogleJsonResponseException e) {
            System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
        } catch (Throwable t) {
            System.err.println("Throwable: " + t.getMessage());
            t.printStackTrace();
        }
		
	}

}
