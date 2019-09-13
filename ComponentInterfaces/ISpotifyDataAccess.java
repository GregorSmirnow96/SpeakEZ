package com.example.speakez.ComponentInterfaces;

/**
 * @smumary
 *  This is the interface exposed by the module responsible for retrieving data from the Spotify
 *  API.
 */
public interface ISpotifyDataAccess
{
    /**
     * @summary
     *  This method creates a connection to the Spotify API. This enables the caller to search for a
     *  song.
     * @param songName
     *  The name of the song that the user queried.
     * @return
     *  An instance of ISpotifySearchSession for retrieving a Spotify song.
     */
    ISpotifySearchSession createNewSearchSession(String songName);
}
