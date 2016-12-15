package codingweek2016.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import codingweek2016.MainMenu;
import codingweek2016.MainWindow;

@SuppressWarnings("serial")
public class SettingsView extends AbstractView {
	
	private JTextField heightField = new JTextField();
	private JTextField widthField = new JTextField();
	private JTextField maxvidField = new JTextField();
	private JTextField apikeyField = new JTextField();
	private JLabel labelHeight = new JLabel("Window height: ");
	private JLabel labelWidth = new JLabel("Window width: ");
	private JLabel maxvidlabel = new JLabel("Number of videos to be displayed: ");
	private JLabel apikeylabel = new JLabel("API key: ");
	private JButton saveButton = new JButton("Save");
	private JPanel settingsGrid = new JPanel();
	
	public SettingsView(MainWindow mW) {
		mainWindow = mW;
		mainMenu = new MainMenu(mainWindow);
		
		this.setLayout(new BorderLayout());
		
		GridLayout layout = new GridLayout(5,2);
		layout.setVgap(10);
		settingsGrid.setLayout(layout);
		/*GroupLayout layout = new GroupLayout(settingsGrid);
		settingsGrid.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);*/
		//settingsGrid.setLayout(new BoxLayout(settingsGrid, BoxLayout.Y_AXIS));//(new FlowLayout());
		
		maxvidField.setPreferredSize(new Dimension(300,25));
		heightField.setPreferredSize(new Dimension(300,25));
		widthField.setPreferredSize(new Dimension(300,25));
		apikeyField.setPreferredSize(new Dimension(300,25));
		
		saveButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	mainWindow.setMainView(new SearchView(mainWindow));
            }
        });
		
		/*JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		p1.add(labelHeight, BorderLayout.WEST);
		p1.add(heightField, BorderLayout.EAST);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.add(labelWidth, BorderLayout.WEST);
		p2.add(widthField, BorderLayout.EAST);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		p3.add(maxvidlabel, BorderLayout.WEST);
		p3.add(maxvidField, BorderLayout.EAST);
		
		JPanel p4 = new JPanel();
		p4.setLayout(new BorderLayout());
		p4.add(apikeylabel, BorderLayout.WEST);
		p4.add(apikeyField, BorderLayout.EAST);
		
		p1.setAlignmentX(Component.CENTER_ALIGNMENT);
		settingsGrid.add(p1);
		p2.setAlignmentX(Component.CENTER_ALIGNMENT);
		settingsGrid.add(p2);
		p3.setAlignmentX(Component.CENTER_ALIGNMENT);
		settingsGrid.add(p3);
		p4.setAlignmentX(Component.CENTER_ALIGNMENT);
		settingsGrid.add(p4);*/
		
		/*SequentialGroup hGroup = layout.createSequentialGroup();
		
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(labelHeight)
				.addComponent(labelWidth)
				.addComponent(maxvidlabel)
				.addComponent(apikeylabel));
		
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(heightField)
		        .addComponent(widthField)
		        .addComponent(maxvidField)
		        .addComponent(apikeyField));
		
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		
		vGroup.addGroup(layout.createParallelGroup()
				.addComponent(labelHeight)
				.addComponent(heightField));
		
		vGroup.addGroup(layout.createParallelGroup()
		        .addComponent(labelWidth)
		        .addComponent(widthField));
		
		vGroup.addGroup(layout.createParallelGroup()
		        .addComponent(maxvidlabel)
		        .addComponent(maxvidField));
		
		vGroup.addGroup(layout.createParallelGroup()
		        .addComponent(apikeylabel)
		        .addComponent(apikeyField));
		
		layout.setVerticalGroup(vGroup);*/
		
		settingsGrid.add(labelHeight);
		settingsGrid.add(heightField);
		
		settingsGrid.add(labelWidth);
		settingsGrid.add(widthField);
		
		settingsGrid.add(maxvidlabel);
		settingsGrid.add(maxvidField);
		
		settingsGrid.add(apikeylabel);
		settingsGrid.add(apikeyField);
				
		
		
		this.add(mainMenu,BorderLayout.WEST);
		this.add(settingsGrid, BorderLayout.CENTER);
		this.add(saveButton, BorderLayout.SOUTH);
		
	}

}
