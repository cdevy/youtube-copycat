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
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import codingweek2016.model.Account;

import com.google.api.services.youtube.YouTube;
import com.jayway.jsonpath.JsonPath;

import codingweek2016.model.Authentification;
import codingweek2016.model.SearchRequest;
import codingweek2016.view.SearchView;
import codingweek2016.view.SettingsView;
import codingweek2016.view.UploadView;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {
	
	private static final String PROPERTIES_FILENAME = "youtube.properties";
	
	private Account account = null;
	
	@SuppressWarnings("unused")
	private static YouTube youtube;
	
	private JPanel logPanel = new JPanel();
	private JButton searchButton = new JButton("Search");
	private JButton suggestionButton = new JButton(new ImageIcon("src/main/resources/icons/ic_whatshot_black_36dp_1x.png"));
	private JButton uploadButton = new JButton("Upload");
	private JButton settingsButton = new JButton(new ImageIcon("src/main/resources/icons/ic_settings_black_36dp_1x.png"));
	//private JButton logButton = new JButton("Log in");
	private JLabel nameLabel = new JLabel("   You are logged in.");
	private Dimension dim4Buttons = new Dimension(140,50);
	private String usrName = "";
	private MainWindow mainWindow;
	
	/*private JPanel subscriptionsPanel = new JPanel();
	private JButton logButton = new JButton("Log in");
	private JLabel nameLabel = new JLabel("");
	private JLabel enterName = new JLabel("Enter your name: ");
	private JTextField enterNameZone = new JTextField();
	private JButton enterNameButton = new JButton("Confirm");*/

	
	public MainMenu(MainWindow mW) {
		mainWindow = mW;
		
		GridLayout layout = new GridLayout(5,1);
		layout.setVgap(10);
		logPanel.setLayout(layout);
		
		//logButton.setPreferredSize(dim4Buttons);
		uploadButton.setPreferredSize(dim4Buttons);
		searchButton.setPreferredSize(dim4Buttons);
		suggestionButton.setPreferredSize(dim4Buttons);
		settingsButton.setPreferredSize(dim4Buttons);
		
		//settingsButton.setIcon(new ImageIcon("/resources/icons/ic_settings_black_36dp_1x.png")); 
		
		searchButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	mainWindow.setMainView(new SearchView(mainWindow));
            }
        });
		
		uploadButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	mainWindow.setMainView(new UploadView(mainWindow));
            }
        });
		
		suggestionButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
		
		settingsButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	mainWindow.setMainView(new SettingsView(mainWindow));
            }
        });
		
		/*enterNameZone.setPreferredSize(new Dimension(50,20));*/
		
		/*logButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	if (account == null) {
	            	try {
						account = new Account();
						youtube = new YouTube.Builder(Authentification.HTTP_TRANSPORT, Authentification.JSON_FACTORY, account.getCredential())
				                .setApplicationName("youtube-cmdline-localizations-sample").build();
						
						recreateMenuPanel(true);

						
				        
				        //JsonFactory jsonfactory = new JsonFactory();
				        //Writer writer = new StringWriter();
						//JsonGenerator jsonGenerator = jsonfactory.createJsonGenerator(writer);
						//System.out.println(account.getCredential().getJsonFactory().toPrettyString(jsonGenerator));
						//System.out.println(youtube.channels().list("snippet").getJsonContent());
						/*
						logPanel.add(enterName);
						logPanel.add(enterNameZone);
						logPanel.add(enterNameButton);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
            	}
            }
        });*/
		/*
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
            	
        });*/
		
		
		
		this.setLayout(new FlowLayout());
		/*if (account ==  null) {		
			logPanel.add(logButton);
		} */
		
		
		recreateMenuPanel(false);

		this.setLayout(new FlowLayout()); 
		this.add(logPanel);
		//this.add(subscriptionsPanel);
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
		
		URL webSite = new URL("https://www.googleapis.com/youtube/v3/channels?part=id%2Csnippet%2Cstatistics%2CcontentDetails%2CtopicDetails&forUsername="+usrName+"&key="+apiKey);
		
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
	
	public void recreateMenuPanel(boolean recreate) {
		if (recreate) {
			logPanel.removeAll();
			logPanel.revalidate();
			logPanel.repaint();
		}
		suggestionButton.setText("Trends");
		if (account != null) {
			logPanel.add(nameLabel);
		}
		logPanel.add(uploadButton);
		logPanel.add(searchButton);
		logPanel.add(suggestionButton);
		logPanel.add(settingsButton);
		
	}

}
