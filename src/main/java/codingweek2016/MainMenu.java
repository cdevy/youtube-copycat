package codingweek2016;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.services.youtube.YouTube;

import codingweek2016.model.Account;
import codingweek2016.model.Authentification;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {
	
	private Account account = null;
	
	private static YouTube youtube;
	
	private JPanel logPanel = new JPanel();
	private JPanel subscriptionsPanel = new JPanel();
	private JButton logButton = new JButton("Log in");
	
	public MainMenu() {
		
		
		
		logButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	new GoogleClientSecrets();
            	if (account == null) {
	            	try {
						account = new Account();
						youtube = new YouTube.Builder(Authentification.HTTP_TRANSPORT, Authentification.JSON_FACTORY, account.getCredential())
				                .setApplicationName("youtube-cmdline-localizations-sample").build();
						
						logPanel.remove(logButton);
						logPanel.revalidate();
						logPanel.repaint();
						logPanel.add(new JLabel("Logged in"));
						
						//addSubscribtions();
						//Reader clientSecretReader = new InputStreamReader(Auth.class.getResourceAsStream("/client_secrets.json"));
				       // GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(Auth.JSON_FACTORY, clientSecretReader);

				        // Checks that the defaults have been replaced (Default = "Enter X here").
				        
						//URL webSite = new URL("https://www.googleapis.com/youtube/v3/channels?part=id%2Csnippet%2Cstatistics%2CcontentDetails%2CtopicDetails&forUsername=KarlWolfVEVO&key="+clientSecrets.getDetails().getClientSecret());
				        //URLConnection connection = webSite.openConnection();
				        //BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						System.out.println("You are logged in under: "+youtube.channels().list("snippet.defaultLanguage"));//youtube.channels().list("snippets.title").execute());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
            	}
            }
        });
		this.setLayout(new FlowLayout());
		if (account ==  null) {		
			logPanel.add(logButton);
		} 
		
		this.add(logPanel);
		this.add(subscriptionsPanel);
	}
	
	public void addSubscribtions() {
		
		
	}

}
