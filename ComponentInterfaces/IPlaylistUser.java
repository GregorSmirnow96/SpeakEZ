package com.example.speakez.ComponentInterfaces;

/**
 * @summary
 *  This interface defines the necessary behaviour of any entity that wants to interact with the the
 *  playlist.
 */
public interface IPlaylistUser
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
     *  This method removes the first song in the playlist whose URI matches the provided one.
     * @param songUri
     *  The Spotify URI of the song to be added.
     */
    void removeSong(String songUri);
}
