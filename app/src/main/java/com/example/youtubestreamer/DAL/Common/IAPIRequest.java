package com.example.youtubestreamer.DAL.Common;

import org.json.JSONException;

import java.io.IOException;

// IAPIRequest is the interface for a song-provider-API request meta data. The meta data's structure
// will vary from provider to provider. I.E.: YouTube API requests will require different parameters
// than Spotify's.
public interface IAPIRequest
{
    public IAPIResponse send() throws IOException, JSONException;
}
