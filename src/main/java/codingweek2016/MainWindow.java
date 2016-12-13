package codingweek2016;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import codingweek2016.model.SearchRequest;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
	public MainWindow(SearchRequest request) {
		super("Youtube Copycat");
		setPreferredSize(new Dimension(1000,700));
				
		setLayout(new BorderLayout());
		
		View v = new View(request);
		
		this.add(v);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

}