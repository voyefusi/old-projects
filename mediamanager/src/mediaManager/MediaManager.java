package mediaManager;
import java.util.*;
import MyList.*;

public interface MediaManager {
	
	/**
	 * Adds the specified song to the database.  Song/Movie names are unique in the database.
	 * @param name
	 * @param durationInSeconds
	 * @param artists
	 * @param genre
	 * @param album
	 * @return true if the song is added and false otherwise (e.g., the song already exist).
	 */
	public boolean addSong(String name, int durationInSeconds, String artists, String genre, String album);
	
	/**
	 * Adds the specified movie to the database.  Movie/Song names are unique in the database.
	 * @param name
	 * @param durationInSeconds
	 * @param artists
	 * @param genre
	 * @param resolution
	 * @param rating
	 * @return true if the movie is added and false otherwise (e.g., the movie already exists).
	 */
	public boolean addMovie(String name, int durationInSeconds, String artists,
						    String genre, String resolution, String rating);
	
	/**
	 * Removes the movie/song from the database.
	 * @param mediaFileName
	 * @return true if the media is removed and false otherwise (e.g., it does not exist).
	 */
	public boolean removeMediaFile(String mediaFileName);
	
	/**
	 * Returns the information associated with the specified media file (song or movie). For a song
	 * the ArrayList will have name, durationInSeconds, artists, genre, playCount, album; for a movie the
	 * ArrayList will have name, durationInSeconds, artists, genre, playCount, resolution, and rating.
	 * @param mediaFileName
	 * @return ArrayList with information or null (if mediaFileName not found).
	 */
	public ArrayList<String> getMediaFileInfo(String mediaFileName);	
	
	/**
	 * Increases by one the play count associated with the mediaFile.  No operation will take place
	 * if mediaFileName is not found.
	 * @param mediaFileName
	 */
	public void increasePlayCount(String mediaFileName);
	
	/**
	 * Returns the play count.
	 * @param mediaFileName
	 * @return returns the play count or -1 (if media not found).
	 */
	public int getPlayCount(String mediaFileName);
	 
	/* PlayList methods */
	/** Creates a playlist.
	 * @return true if the playlist has been created and false otherwise (e.g., the 
	 * playlist already exists). 
	 */
	public boolean createPlayList(String playListName);
	
	/**
	 * Removes the playlist.
	 * @param playListName
	 * @return true if the playlist has been removed and false otherwise (e.g., the 
	 * playlist does not exist).
	 */
	public boolean removePlayList(String playListName);
	
	/**
	 * Adds the mediaFileName to the playlist.  The new entry is added at the end
	 * of the current list of entries associated with the playlist.
	 * @param playListName
	 * @param mediaFileName
	 * @return true if the add operation was completed successfully and false otherwise (e.g.,
	 * playlist does not exist). 
	 */
	public boolean addMediaToPlayList(String playListName, String mediaFileName);
	
	/**
	 * Removes the specified entry from the playlist.
	 * @param playListName
	 * @param mediaFileName
	 * @return true if the remove operation was completed successfully and false otherwise (e.g.,
	 * playlist does not exist).  
	 */
	public boolean removeMediaFromPlayList(String playListName, String mediaFileName);
	
	/**
	 * Returns an ArrayList with the media file names in the playlist.
	 * @param playListName
	 * @return ArrayList or null (if no playlist exist).
	 */
	public ArrayList<String> getPlayList(String playListName);
	
	/**
	 * Returns a set with the names of playlists.
	 * @return set with names or empty set.
	 */
	public Set<String> getAllPlayLists();
	
	/* Search methods */
	/** Returns a sorted linked list based on the media file name.
	 * @return sorted linked list.
	 */
	public MySortedLinkedList<String> getAllMediaFileNames();
	
	/**
	 * Returns a sorted linked list of song names.
	 * @return sorted linked list.
	 */
	public MySortedLinkedList<String> getAllSongNames();

	/**
	 * Returns a sorted linked list of movie names.
	 * @return sorted linked list.
	 */
	public MySortedLinkedList<String> getAllMovieNames();
	
	
	/**
	 * Returns a sorted linked list with the names of media files satisfying the specified
	 * parameters restrictions.  If a field has a value of null, then that criteria is
	 * ignored in the search.  If durationInSeconds is -1, then the duration is ignored.  
	 * If a durationInSeconds value is provided, then media with a duration less than or equal
	 * to that value will be included in the search results.
	 * @param durationInSeconds
	 * @param artists
	 * @param genre
	 * @param album
	 * @param resolution
	 * @param rating
	 * @return sorted linked list sorted by name
	 */
	public MySortedLinkedList<String> getMediaFileNamesWith(int durationInSeconds, String artists, String genre,
						   String album, String resolution, String rating);
	
	/**
	 * Returns an ArrayList sorted by the play count (entries with a higher play count
	 * will appear first in the list). If two files have the same count, then
	 * use the media file name to sort the media files.
	 * @return sorted linked list sorted by play count.
	 */
	public ArrayList<String> getAllMediaFilesNamesByPlayCount();
	
	/**
	 * Returns a map mapping genre to the set of media file names associated with that genre.
	 * @return map
	 */
	public TreeMap<String, TreeSet<String>> getAllMediaFilesNamesByGenre();
}
