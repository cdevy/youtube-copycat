package codingweek2016.model;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CommentSnippet;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import com.google.common.collect.Lists;

@SuppressWarnings("serial")
public class Comment extends JPanel {
	
    private static YouTube youtube;
    
    @SuppressWarnings("unused")
	private String videoId;
    
    public Comment (String id){
    	videoId = id;
    }
	
	public List<CommentThread> getComments() throws IOException {
		
		List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.force-ssl");
		 
        try {
            // Authorize the request.
            Credential credential = Authentification.authorize(scopes, "commentthreads");

            // This object is used to make YouTube Data API requests.
            youtube = new YouTube.Builder(Authentification.HTTP_TRANSPORT, Authentification.JSON_FACTORY, credential).setApplicationName("youtube-cmdline-commentthreads-sample").build();
            
           // YouTube.Search.List search = youtube.search().list("id,snippet");

			//YouTube.CommentThreads.List request = youtube.commentThreads().list("");
			CommentThreadListResponse videoCommentsListResponse = youtube.commentThreads().list("snippet").setVideoId("xcv0EN3TMZ0").setTextFormat("plainText").execute();
		    List<CommentThread> videoComments = videoCommentsListResponse.getItems();
		    
		    /*
		    if (videoComments.isEmpty()) {
                System.out.println("Can't get video comments.");
            } else {
                // Print information from the API response.
                System.out
                        .println("\n================== Returned Video Comments ==================\n");
                for (CommentThread videoComment : videoComments) {
                    CommentSnippet snippet = videoComment.getSnippet().getTopLevelComment().getSnippet();
                    System.out.println("  - Author: " + snippet.getAuthorDisplayName());
                    System.out.println("  - Comment: " + snippet.getTextDisplay());
                    System.out
                            .println("\n-------------------------------------------------------------\n");
                }
            }*/
            
		    return videoComments;
		    
        } catch (GoogleJsonResponseException e) {
            System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode()
                    + " : " + e.getDetails().getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
            
        } catch (Throwable t) {
            System.err.println("Throwable: " + t.getMessage());
            t.printStackTrace();
        }
		return null;
	}
	
	public JPanel display( List<CommentThread> videoComments){
		JPanel commentlist = new JPanel();
		
		for (CommentThread videoComment : videoComments) {
			GridLayout grid = new GridLayout(25,1);
			grid.setVgap(10);
			commentlist.setLayout(grid);
			CommentSnippet snippet = videoComment.getSnippet().getTopLevelComment().getSnippet();
			String author = snippet.getAuthorDisplayName();
			String comment = snippet.getTextDisplay();
			JPanel commentaire = new JPanel();
			commentaire.setLayout(new FlowLayout());
			commentaire.add(new JLabel(author));
			commentaire.add(new JLabel(comment));
			commentlist.add(commentaire);
		}
		return commentlist;	
	}
}