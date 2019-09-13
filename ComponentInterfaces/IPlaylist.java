package com.example.speakez.ComponentInterfaces;

import java.util.Iterator;

/**
 * @summary
 *  This interface defines the behavior of the data structure that holds the playlist's state.
 */
public interface IPlaylist
{
    /**
     * @summary
     *  This method queues up a song.
     * @param songUri
     *  The Spotify URI of the song to be added.
     */
    void enqueueSong(String songUri);

    /**
     * @summary
     *  This method retrieves the next song's Spotify URI.
     * @return
     *  The Spotify URI of the next song in the playlist.
     */
    String getNextSong();

    /**
     * @summary
     *  This method removes the first song in the playlist whose URI matches the provided one.
     * @param songUri
     *  The Spotify URI of the song to be added.
     */
    void removeSong(String songUri);

    /**
     * @summary
     *  This method gets an iterator for the playlist.
     * @return
     *  An iterator for all songs in the playlist.
     */
    Iterator<String> getIterator();
}
