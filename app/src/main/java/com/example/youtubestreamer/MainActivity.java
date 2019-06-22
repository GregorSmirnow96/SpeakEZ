package com.example.youtubestreamer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.youtubestreamer.DAL.YouTubeDataAccess.YouTubeAPIRequest;
import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import static com.example.youtubestreamer.DAL.StaticAccess.QueryYouTubeSongData;

public class MainActivity extends AppCompatActivity
{
    private static final int REQUEST_CODE = 1337;

    private static final String CLIENT_ID = "fc1fc9a7a3874db1abf36259f79cc958";
    private static final String REDIRECT_URI = "YouTubeStreamer://";
    private SpotifyAppRemote mSpotifyAppRemote;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        /*  */
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(
            CLIENT_ID,
            AuthenticationResponse.Type.TOKEN,
            REDIRECT_URI);

        builder.setScopes(new String[]{"streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(
            this,
            REQUEST_CODE,
            request);
        /*  */

        ConnectionParams connectionParams = new ConnectionParams.Builder(CLIENT_ID)
            .setRedirectUri(REDIRECT_URI)
            .showAuthView(true)
            .build();

        SpotifyAppRemote.connect(
            this,
            connectionParams,
            new Connector.ConnectionListener()
            {
                @Override
                public void onConnected(SpotifyAppRemote spotifyAppRemote)
                {
                    mSpotifyAppRemote = spotifyAppRemote;
                    Log.d("MainActivity", "Connected! Yay!");

                    // Now you can start interacting with App Remote
                    connected();
                }

                @Override
                public void onFailure(Throwable throwable)
                {
                    Log.e("MainActivity", throwable.getMessage(), throwable);

                    // Something went wrong when attempting to connect! Handle errors here
                }
            });
    }

    public void onClickBtn(View view)
    {
        try
        {
            mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DX7K31D69s4M1");
            QueryYouTubeSongData(new YouTubeAPIRequest("I want it that way", 5));
        }
        catch (Exception e)
        {
            System.out.println();
            // Add handling
        }
    }

    private void connected()
    {
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE)
        {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);

            AuthenticationResponse.Type responseType = response.getType();
            switch (responseType)
            {
                // Response was successful and contains auth token
                case TOKEN:
                    // Handle successful response
                    break;

                // Auth flow returned an error
                case ERROR:
                    // Handle error response
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
            }
        }
    }
}