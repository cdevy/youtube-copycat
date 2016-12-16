package codingweek2016.model;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.common.collect.Lists;

public class MyUploads extends Request {

	private static List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.readonly");
	private Iterator<PlaylistItem> playlistEntries;
	private int numberOfVideos;
	
	JScrollPane scrollPane;
	
	public MyUploads() throws IOException {
		super(Authentification.authorize(scopes, "myuploads"), "youtube-myuploads");
		
        try {
            YouTube.Channels.List channelRequest = youtube.channels().list("contentDetails");
            channelRequest.setMine(true);
            channelRequest.setFields("items/contentDetails,nextPageToken,pageInfo");
            ChannelListResponse channelResult = channelRequest.execute();

            List<Channel> channelsList = channelResult.getItems();

            if (channelsList != null) {
                String uploadPlaylistId = channelsList.get(0).getContentDetails().getRelatedPlaylists().getUploads();
                
                List<PlaylistItem> playlistItemList = new ArrayList<PlaylistItem>();

                YouTube.PlaylistItems.List playlistItemRequest = youtube.playlistItems().list("id,contentDetails,snippet");
                playlistItemRequest.setPlaylistId(uploadPlaylistId);

                playlistItemRequest.setFields("items(contentDetails/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url,snippet/publishedAt),nextPageToken,pageInfo");

                String nextToken = "";

                do {
                    playlistItemRequest.setPageToken(nextToken);
                    PlaylistItemListResponse playlistItemResult = playlistItemRequest.execute();

                    playlistItemList.addAll(playlistItemResult.getItems());

                    nextToken = playlistItemResult.getNextPageToken();
                } while (nextToken != null);
                
                numberOfVideos = playlistItemList.size();
                playlistEntries = playlistItemList.iterator();
            }

        } catch (GoogleJsonResponseException e) {
            e.printStackTrace();
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());

        } catch (Throwable t) {
            t.printStackTrace();
        }
	}
	
	public JPanel display() {
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(numberOfVideos,1);
		layout.setVgap(10);
		panel.setLayout(layout);
		
        while (playlistEntries.hasNext()) {
        	PlaylistItem playlistItem = playlistEntries.next();
            Video entry = new Video(playlistItem);
            panel.add(entry);
        }
        return panel;
	}
	
	public int getNbOfVideos() {
		return numberOfVideos;
	}

}
