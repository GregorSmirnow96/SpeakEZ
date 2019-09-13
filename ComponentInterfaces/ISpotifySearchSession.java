package com.example.speakez.ComponentInterfaces;

import java.util.List;

/**
 * @summary
 *  This interface allows the consumer to iterate through the results of a Spotify API query.
 */
public interface ISpotifySearchSession
{
    /**
     * @summary
     *  This method gets the next 10 results from a Spotify search. This allows the user to load
     *  more songs from the Spotify API if the song they are looking for isn't in the first few
     *  results.
     * @return
     *  A list of the next 10 results from thei Spotify query.
     */
    List<Object> GetNext10Results(); /* Once the type is known, change the input type. */
}
