package com.example.youtubestreamer;

import java.util.LinkedList;
import java.util.Queue;

public class Playlist
{
    private static Playlist instance;
    private Playlist()
    {
        this.youTubeSongIDQueue = new LinkedList<>();
    }
    public static Playlist getInstance()
    {
        return instance == null
            ? instance = new Playlist()
            : instance;
    }

    private Queue<String> youTubeSongIDQueue;

    public int size()
    {
        return this.youTubeSongIDQueue.size();
    }

    public void cueSong(String songID)
    {
        this.youTubeSongIDQueue.add(songID);
    }

    public String getNextSongID()
    {
        return this.youTubeSongIDQueue.remove();
    }
}
