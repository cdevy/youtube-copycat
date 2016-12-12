package codingweek2016;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class App extends JFrame implements ActionListener {
	
    private JLabel text = new JLabel("Vidéo à chercher :");
    private JButton search = new JButton("Search");
	
	public App() {
		super("Youtube Copycat");
		setPreferredSize(new Dimension(300,320));
				
		setLayout(new BorderLayout());
		
        text.setHorizontalAlignment(SwingConstants.CENTER);
        Font sensorValueFont = new Font("Sans Serif", Font.BOLD, 18);
        text.setFont(sensorValueFont);

        this.add(text, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 1));
        buttonsPanel.add(search);        
        this.add(buttonsPanel, BorderLayout.SOUTH);

	}

	public static void main(String[] args) {
		new App();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}