package codingweek2016;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
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
	
	private JTextArea commentEntry;
	
	private JPanel scrollComment = new JPanel();
	private JPanel createComment = new JPanel();
	private JScrollPane scroll;

	public CommentsWindow(String title, final String id){
		super("Comments on " + title);
		this.setPreferredSize(new Dimension(500,800));
		
		this.setLayout(new BorderLayout(30, 30));
		
		
        createComment.setLayout(new BoxLayout(createComment, BoxLayout.X_AXIS));
        
        commentEntry = new JTextArea("Write your comment here"); 
        commentEntry.setEnabled(false);
        commentEntry.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent event) {
        		commentEntry.setEnabled(true);
        		commentEntry.requestFocus();
            }
        });
        commentEntry.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (commentEntry.getText().equals("Write your comment here")) {
					commentEntry.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (commentEntry.getText().equals("")) {
					commentEntry.setText("Write your comment here");
					commentEntry.setEnabled(false);
				}
			}
        	
        });
        createComment.add(commentEntry);
       
        
        JButton publish = new JButton("Publish");
        publish.setPreferredSize(new Dimension(150,40));
        createComment.add(publish);
        
                
         
        Comment com = new Comment(id);
        List<CommentThread> videoComments = new ArrayList<CommentThread>();
        
        try {
			videoComments = com.getComments();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        
        scroll = new JScrollPane(com.display(videoComments));
        scroll.setPreferredSize(new Dimension(450,610));
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
  		   public void run() { 
  			 scroll.getVerticalScrollBar().setValue(0);
  			 scroll.getHorizontalScrollBar().setValue(0);
  		   }
  		});
        scrollComment.add(scroll);
		scrollComment.setBorder(null);
		
		
		/* Action when clicking on the button publish */
        publish.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	String text = commentEntry.getText();
            	Comment com = new Comment(id);
            	com.postcomment(text);
            	
            	scrollComment.removeAll();
            	
            	List<CommentThread> videoComments = new ArrayList<CommentThread>();
            	try {
        			videoComments = com.getComments();
        		} catch (IOException ex) {
        			ex.printStackTrace();
        		}
            	scroll = new JScrollPane(com.display(videoComments));
            	scroll.setPreferredSize(new Dimension(450,610));
            	javax.swing.SwingUtilities.invokeLater(new Runnable() {
          		   public void run() { 
          			 scroll.getVerticalScrollBar().setValue(0);
          			scroll.getHorizontalScrollBar().setValue(0);
          		   }
          		});
            	scrollComment.add(scroll);
            	scrollComment.setBorder(null);
            	
            	commentEntry.setText("Write your comment here");
				commentEntry.setEnabled(false);
				
				scrollComment.revalidate();
            	scrollComment.repaint();
            	//pack();
            	            	
            }
        });
        
        this.add(createComment, BorderLayout.NORTH);
        this.add(scrollComment, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}
	
}
