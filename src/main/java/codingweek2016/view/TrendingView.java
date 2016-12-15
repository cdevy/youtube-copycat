package codingweek2016.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import codingweek2016.MainMenu;
import codingweek2016.MainWindow;

@SuppressWarnings("serial")
public class TrendingView extends AbstractView {
	
	private JLabel yTImg;

	
	public TrendingView(MainWindow mW) {
		mainWindow = mW;
		mainMenu = new MainMenu(mainWindow);
		
		this.setLayout(new BorderLayout());
		
		
		
		JPanel north = new JPanel();
		north.setLayout(new FlowLayout());
		
		this.add(mainMenu,BorderLayout.WEST);
		try {
			ImageIcon img = new ImageIcon(ImageIO.read(new File("src/main/resources/youtube.png")));
			yTImg = new JLabel(new ImageIcon(img.getImage().getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH)));
			north.add(yTImg);
			this.add(north,BorderLayout.NORTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
