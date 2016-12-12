package codingweek2016;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import codingweek2016.features.Model;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
	public MainWindow(Model model) {
		super("Youtube Copycat");
		setPreferredSize(new Dimension(700,500));
				
		setLayout(new BorderLayout());
		
		View v = new View(model);
		
		this.add(v);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

}