package codingweek2016;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import codingweek2016.view.SearchView;
import codingweek2016.view.AbstractView;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
	private AbstractView currentView = new SearchView(this);;
	
	public MainWindow() {
		super("Youtube Copycat");
		setPreferredSize(new Dimension(1200,700));
				
		setLayout(new BorderLayout());
		
		this.setMainView(new SearchView(this));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public void setMainView(AbstractView view) {
		this.remove(currentView);
		this.add(view);
		currentView = view;
		revalidate();
		repaint();
	}

}