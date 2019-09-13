package com.example.speakez.DataAccessLayer.Spotify;

/**
 * @summary
 *  This class handles authenticating with the Spotify API. It allows the user to retrieve an active
 *  access token for requesting songs.
 */
public class Authenticator
{
    private final String base64EncodedCredentials =
        "ZmMxZmM5YTdhMzg3NGRiMWFiZjM2MjU5Zjc5Y2M5NTg6YmZjY2E0YjUyZTMxNDQ2YTgyMzFlOTk0MDI4ZjUyZjU=";
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
    public String regenerateAccessToken()
    {
        // TODO: Make API call for access token.

        return mAccessToken;
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
