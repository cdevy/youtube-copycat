package codingweek2016.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SettingsView extends AbstractView {
	
	private JTextField fieldLength = new JTextField();
	private JTextField fieldWidth = new JTextField();
	private JTextField maxvidField = new JTextField();
	private JTextField apikeyField = new JTextField();
	private JButton saveButton = new JButton("Save");
	private JPanel settingsGrid = new JPanel();
	
	
	public SettingsView() {
		
		GridLayout layout = new GridLayout(5,1);
		layout.setVgap(10);
		settingsGrid.setLayout(layout);
		
		maxvidField.setPreferredSize(new Dimension(300,25));
		fieldLength.setPreferredSize(new Dimension(300,25));
		fieldWidth.setPreferredSize(new Dimension(300,25));
		apikeyField.setPreferredSize(new Dimension(300,25));
		
		saveButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            }
        });
		
		settingsGrid.add(fieldLength);
		settingsGrid.add(fieldWidth);
		settingsGrid.add(maxvidField);
		settingsGrid.add(apikeyField);
		settingsGrid.add(saveButton);
		
		this.add(settingsGrid, BorderLayout.CENTER);
	}

}
