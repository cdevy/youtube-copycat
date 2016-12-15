package codingweek2016.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Properties;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;

public abstract class Request extends Observable {
	
	protected static final String PROPERTIES_FILENAME = "youtube.properties";
	protected static final long NUMBER_OF_VIDEOS_RETURNED = 25;
	protected static YouTube youtube;
	protected String apiKey;
	
	public Request(HttpRequestInitializer httpRequest, String appName) {
		Properties properties = new Properties();
        try {
            InputStream in = SearchRequest.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }
        try {

            youtube = new YouTube.Builder(Authentification.HTTP_TRANSPORT, Authentification.JSON_FACTORY, httpRequest).setApplicationName(appName).build();
            apiKey = properties.getProperty("youtube.apikey");
        } catch (Throwable t) {
            t.printStackTrace();
        }
	}

}