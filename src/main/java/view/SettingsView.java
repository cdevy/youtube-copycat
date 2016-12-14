package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SettingsView extends AbstractView {
	
	private JTextField longueurField = new JTextField();
	private JTextField largeurField = new JTextField();
	private JTextField maxvidField = new JTextField();
	private JTextField apikeyField = new JTextField();
	private JButton saveButton = new JButton("Sauvegarder");
	private JPanel settingsGrid = new JPanel();
	
	
	public SettingsView() {
		
		GridLayout layout = new GridLayout(5,1);
		layout.setVgap(10);
		settingsGrid.setLayout(layout);
		
		maxvidField.setPreferredSize(new Dimension(300,25));
		longueurField.setPreferredSize(new Dimension(300,25));
		largeurField.setPreferredSize(new Dimension(300,25));
		apikeyField.setPreferredSize(new Dimension(300,25));
		
		saveButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            }
        });
		
		settingsGrid.add(longueurField);
		settingsGrid.add(largeurField);
		settingsGrid.add(maxvidField);
		settingsGrid.add(apikeyField);
		settingsGrid.add(saveButton);
		
		this.add(settingsGrid, BorderLayout.CENTER);
	}

}
