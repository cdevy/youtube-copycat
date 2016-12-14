package codingweek2016;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;

import codingweek2016.model.Account;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.services.youtube.YouTube;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import codingweek2016.model.Authentification;
import codingweek2016.model.SearchRequest;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {
	
	private static final String PROPERTIES_FILENAME = "youtube.properties";
	
	private Account account = null;
	
	private static YouTube youtube;
	
	private JPanel logPanel = new JPanel();
	private JButton searchButton = new JButton("Search");
	private JButton suggestionButton = new JButton("Suggestions");
	private JButton settingsButton = new JButton("Settings");
	
	/*private JPanel subscriptionsPanel = new JPanel();
	private JButton logButton = new JButton("Log in");
	private JLabel nameLabel = new JLabel("");
	private JLabel enterName = new JLabel("Enter your name: ");
	private JTextField enterNameZone = new JTextField();
	private JButton enterNameButton = new JButton("Confirm");*/
	
	private String usrName = "";
	
	public MainMenu() {
		GridLayout layout = new GridLayout(3,1);
		layout.setVgap(10);
		logPanel.setLayout(layout);
		
		searchButton.setPreferredSize(new Dimension(150,50));
		suggestionButton.setPreferredSize(new Dimension(150,50));
		settingsButton.setPreferredSize(new Dimension(150,50));
		
		searchButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
		
		suggestionButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
		
		settingsButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
		
		logPanel.add(searchButton);
		logPanel.add(suggestionButton);
		logPanel.add(settingsButton);

		this.setLayout(new FlowLayout()); 
		this.add(logPanel);
		
		/*enterNameZone.setPreferredSize(new Dimension(50,20));
		
		logButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	if (account == null) {
	            	try {
						account = new Account();
						youtube = new YouTube.Builder(Authentification.HTTP_TRANSPORT, Authentification.JSON_FACTORY, account.getCredential())
				                .setApplicationName("youtube-cmdline-localizations-sample").build();
						
						logPanel.remove(logButton);
						logPanel.revalidate();
						logPanel.repaint();
						
				        
				        //JsonFactory jsonfactory = new JsonFactory();
				        //Writer writer = new StringWriter();
						//JsonGenerator jsonGenerator = jsonfactory.createJsonGenerator(writer);
						//System.out.println(account.getCredential().getJsonFactory().toPrettyString(jsonGenerator));
						//System.out.println(youtube.channels().list("snippet").getJsonContent());
						
						logPanel.add(enterName);
						logPanel.add(enterNameZone);
						logPanel.add(enterNameButton);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
            	}
            }
        });
		
		enterNameButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	usrName = enterNameZone.getText();
            	URL user;
            	int responseCode = 0;
				try {
					user = new URL("http://www.youtube.com/user/" + usrName);
					HttpURLConnection huc = (HttpURLConnection) user.openConnection();
					responseCode = huc.getResponseCode();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println(responseCode);
            	if (responseCode != 404) {
	            	logPanel.removeAll();
	            	logPanel.revalidate();
					logPanel.repaint();
					nameLabel.setText("Bienvenue "+ usrName + " !");
	            	logPanel.add(nameLabel);
            	}
            }
            	
        });
		
		this.setLayout(new FlowLayout());
		if (account ==  null) {		
			logPanel.add(logButton);
		} 
		
		this.add(logPanel);
		this.add(subscriptionsPanel);*/
	}
	
	public void connect() throws IOException {
		Properties properties = new Properties();
		try {
            InputStream in = SearchRequest.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e1) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e1.getCause()
                    + " : " + e1.getMessage());
            System.exit(1);
        }
		String apiKey = properties.getProperty("youtube.apikey");
		
		URL webSite = null;
		webSite = new URL("https://www.googleapis.com/youtube/v3/channels?part=id%2Csnippet%2Cstatistics%2CcontentDetails%2CtopicDetails&forUsername="+usrName+"&key="+apiKey);
		
        URLConnection connection = null;
		connection = webSite.openConnection();
		
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        String str = "";
        String line = "";
        while((line = in.readLine()) != null){
        	str += line;
        } 
        				        
        List<String> title = JsonPath.read(str,"$.items..snippet.title");

		System.out.println("You are logged in under: "+title.get(0));
	}
	
	public void addSubscribtions() {
		
		
	}

}
