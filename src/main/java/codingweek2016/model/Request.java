package codingweek2016.model;

import java.util.Observable;

import com.google.api.services.youtube.YouTube;

public abstract class Request extends Observable {
	
	protected static final String PROPERTIES_FILENAME = "youtube.properties";
	protected static final long NUMBER_OF_VIDEOS_RETURNED = 25;
	protected static YouTube youtube;

}
