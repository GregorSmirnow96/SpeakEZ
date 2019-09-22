package com.example.speakez.DataAccessLayer.Spotify;

import com.example.speakez.DataAccessLayer.Spotify.RESTRequests.RequestSender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongRetriever
{
    public List<String> querySongURIs(String songName)
    {
        Map<String, String> requestHeaderValues= new HashMap<>();
        requestHeaderValues.put(
            "Authorization",
            "Bearer " + Authenticator.getInstance().getAccessToken());

        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put("q", songName);
        requestParameters.put("type", "track");

        RequestSender requestSender = new RequestSender(
            "https://api.spotify.com/v1/search",
            "GET",
            requestParameters,
            requestHeaderValues,
            null);
        try
        {
            requestSender.execute();
            String requestResponseString = requestSender.waitForResponse();
            int i = 0;
        }
        catch (Exception e)
        {
            System.out.println();
        }

        return null;
    }
}
