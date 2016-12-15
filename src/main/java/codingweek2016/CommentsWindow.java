package codingweek2016;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.google.api.services.youtube.model.CommentThread;

import codingweek2016.model.Comment;



@SuppressWarnings("serial")
public class CommentsWindow extends JFrame {
	
	public CommentsWindow(String title, final String id){
		super("Comments on " + title);
		this.setPreferredSize(new Dimension(700,1000));
		
		this.setLayout(new BorderLayout(30, 30));
		
		JPanel createComment = new JPanel();
        createComment.setLayout(new BoxLayout(createComment, BoxLayout.X_AXIS));
        
        JTextArea commentEntry = new JTextArea("Enter here your comment"); 
        createComment.add(commentEntry);
        
        JButton publish = new JButton("publish");
        publish.setPreferredSize(new Dimension(150,40));
        createComment.add(publish);
        
        this.add(createComment, BorderLayout.NORTH);
        
        Comment com = new Comment(id);
        List<CommentThread> videoComments = new ArrayList<CommentThread>();
        
        try {
			videoComments = com.getComments();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        
        JScrollPane scrollComment = new JScrollPane(com.display(videoComments));
		scrollComment.setBorder(null);
		this.add(scrollComment, BorderLayout.CENTER);
        
		pack();
		setVisible(true);
	}
	
}
