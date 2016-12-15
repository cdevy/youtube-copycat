package codingweek2016;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.samples.youtube.cmdline.Auth;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import com.google.common.collect.Lists;


public class InterfaceTest extends JFrame {
	private YouTube youtube;


	public InterfaceTest(){
		//Initialisation de la JFrame
		super("interface commentaire");
		//JFrame main = new JFrame();
		this.setPreferredSize(new Dimension(700,1400));
		
		this.setLayout(new BorderLayout());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
		JPanel createComment = new JPanel();
        createComment.setLayout(new GridLayout());
        JTextArea commentEntry = new JTextArea("Enter here your comment");

        createComment.add(commentEntry);
        JButton publish = new JButton("publish");
        createComment.add(publish);
        this.add(createComment, BorderLayout.NORTH);
        
        Comment com = new Comment("xcv0EN3TMZ0"); // A CREER AVEC id APRES
        List<CommentThread> videoComments = new ArrayList<CommentThread>();
        
        
        //POUR L'AUTHENTIFICATION
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.force-ssl"); 
        Credential credential;
		try {
			credential = Auth.authorize(scopes, "commentthreads");
		
        youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential)
                .setApplicationName("youtube-cmdline-commentthreads-sample").build();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// FIN POUR L'AUTHENTIFICATION
		
		
		
        try {
			//videoComments = com.getComments();
        	CommentThreadListResponse videoCommentsListResponse = youtube.commentThreads().list("snippet").setVideoId("xcv0EN3TMZ0").setTextFormat("plainText").execute();
    	    videoComments = videoCommentsListResponse.getItems();
			System.out.println("WESH");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //JScrollPane scrollComment = com.display(videoComments);
        //this.add(scrollComment, BorderLayout.CENTER);
		
        JScrollPane scrollComment = new JScrollPane(com.display(videoComments));
		scrollComment.setBorder(null);
		this.add(scrollComment, BorderLayout.CENTER);
        
        
        /*
        Comment com = new Comment(id);
        try {
			List<CommentThread> comments = com.getComments();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}
	
	public static void main(String[] args) {
		new InterfaceTest();
	}
}
