package codingweek2016;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.awt.FlowLayout;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.google.api.services.youtube.model.CommentThread;

public class VideoViewer {
	
	public VideoViewer(String title, final String id) {
		
		String url = "http://www.youtube.com/embed/"+id+"?autoplay=0";

        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setBounds(20,20,700,600);
        shell.setText(title);

        Browser browser = new Browser(shell, SWT.NONE);
        browser.setBounds(100,30,500,300);
        browser.setUrl(url);
        
        // ESSAI POUR INTEGRER UN JPANEL
        Composite cmp = new Composite(shell, SWT.EMBEDDED);
        cmp.setLayout(new RowLayout());
        
        java.awt.Frame frame = SWT_AWT.new_Frame(cmp);
        java.awt.Panel panel = new java.awt.Panel(new java.awt.BorderLayout());
        
        final javax.swing.JPanel commentsArea = new javax.swing.JPanel();
        
        JPanel createComment = new JPanel();
        createComment.setLayout(new FlowLayout());
        JTextArea commentEntry = new JTextArea();
        createComment.add(commentEntry);
        JButton publish = new JButton("publish");
        createComment.add(publish);
        commentsArea.add(createComment);
        
        
        /*
        Comment com = new Comment(id);
        try {
			List<CommentThread> comments = com.getComments();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
        
        panel.add(commentsArea);
        frame.add(panel);
        
        // FIN ESSAI POUR INTEGRER UN JPANEL
        
        
        shell.open();

        browser.setVisible(true);
        shell.setVisible(true);
        
        /* While the viewer window is open,  */
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
	}
}
