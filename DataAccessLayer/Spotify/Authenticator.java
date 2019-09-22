package com.example.speakez.DataAccessLayer.Spotify;

import com.example.speakez.DataAccessLayer.Spotify.RESTRequests.RequestSender;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

/**
 * @summary
 *  This class handles authenticating with the Spotify API. It allows the user to retrieve an active
 *  access token for requesting songs.
 */
public class Authenticator
{
    private String mAccessToken;

    /* The static components of this class' singleton implementation. */
    private static Authenticator instance;

    public static Authenticator getInstance()
    {
        return instance == null
            ? instance = new Authenticator()
            : instance;
    }

    /**
     * @summary
     *  This constructor is private to support this class' Singleton implementation.
     */
    private Authenticator()
    {
        regenerateAccessToken();
    }

    /**
     * @summary
     *  This method makes a call to Spotify's API to retrieve an active Spotify access token. This
     *  token is required for other Spotifu API requests.
     * @return
     *  An active Spotify access token.
     */
    public void regenerateAccessToken()
    {
        Map<String, String> requestHeaderValues= new HashMap<>();
        requestHeaderValues.put("Authorization", "Basic " + SpotifyConstants.BASE64_ENCODED_CREDENTIALS);
        requestHeaderValues.put("Content-Type", "application/x-www-form-urlencoded");
        String requestBody = "grant_type=client_credentials";

        RequestSender requestSender = new RequestSender(
            "https://accounts.spotify.com/api/token",
            "POST",
            new HashMap<String, String>(),
            requestHeaderValues,
            requestBody);
        try
        {
            requestSender.execute();
            String requestResponseString = requestSender.waitForResponse();
            int jsonObjectStartIndex = requestResponseString.indexOf('{');
            requestResponseString = requestResponseString.substring(jsonObjectStartIndex);
            JsonObject jsonObject = new JsonParser().parse(requestResponseString).getAsJsonObject();
            mAccessToken = jsonObject.get("access_token").getAsString();
        }
        catch (Exception e)
        {
            // TODO: Report Spotify authentication not working.
        }
    }

    /**
     * @summary
     *  This method gets this app's (possibly expired) Spotify access token.
     * @return
     *  The most recently generated Spotify access token.
     */
    public String getAccessToken()
    {
        return mAccessToken;
    }
}
