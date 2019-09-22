package com.example.speakez;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.icu.util.Output;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.speakez.Peer2PeerLayer.ClientCommunication.ClientBluetoothHub;
import com.example.speakez.Peer2PeerLayer.DeviceDiscoverer;
import com.example.speakez.Peer2PeerLayer.DevicePairer;
import com.example.speakez.Peer2PeerLayer.HostCommunication.HostBluetoothHub;
import com.example.speakez.Peer2PeerLayer.Peer2PeerConnection;
import com.example.speakez.Peer2PeerLayer.SpeakEZBroadcastReceiver;
import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
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

    private void connected()
    {
    }

    public void onButtonClick(android.view.View view)
    {
        try
        {
            mSpotifyAppRemote.getPlayerApi().play("spotify:artist:5rSXSAkZ67PYJSvpUpkOr7");
        }
        catch (Exception e)
        {
            System.out.println();
            // Add handling
        }
    }

    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    List<BluetoothDevice> mNearbyBluetoothDevices = new ArrayList<>();
    public void searchForDevices(android.view.View view)
    {
        BroadcastReceiver broadcastReceiver = new SpeakEZBroadcastReceiver(mNearbyBluetoothDevices);
        DeviceDiscoverer deviceDiscoverer = new DeviceDiscoverer(
            this,
            mNearbyBluetoothDevices,
            mBluetoothAdapter,
            broadcastReceiver);
        deviceDiscoverer.startDeviceDiscovery();
    }

    private final String clientDeviceName = "moto e6";
    public void pairWithClientDevice(android.view.View view)
    {
        BluetoothDevice deviceToConnectTo = null;
        for (BluetoothDevice bluetoothDevice : mNearbyBluetoothDevices)
        {
            String deviceName = bluetoothDevice.getName();
            if (deviceName != null &&
                deviceName.equals(clientDeviceName))
            {
                deviceToConnectTo = bluetoothDevice;
                break;
            }
        }

        if (deviceToConnectTo == null)
        {
            Toast.makeText(
                this,
                "The device couldn't be found.",
                Toast.LENGTH_LONG);
            return;
        }

        DevicePairer devicePairer = new DevicePairer();
        try
        {
            devicePairer.createBond(deviceToConnectTo);
        }
        catch (Exception e)
        {
            // TODO: Log the error.
            int i = 0;
        }
    }

    public void launchServer(android.view.View view)
    {
        HostBluetoothHub.getInstance().launchServer();
    }

    private final String hostDeviceName = "Galaxy J3 V";
    public void connectToHostDevice(android.view.View view)
    {
        BluetoothDevice device = null;
        for (BluetoothDevice aDevice : mNearbyBluetoothDevices) {
            String deviceName = aDevice.getName();
            if (deviceName != null &&
                deviceName.equals(hostDeviceName))
            {
                device = aDevice;
                break;
            }
        }

        try
        {
            BluetoothSocket socket = device.createInsecureRfcommSocketToServiceRecord(
                UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
            socket.connect();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            Peer2PeerConnection hostConnection = new Peer2PeerConnection(
                inputStream,
                outputStream);

            ClientBluetoothHub.getInstance().setUpstreamDeviceConnection(hostConnection);
        }
        catch (IOException e)
        {
            // TODO: Log the exception.
        }
    }

    public void sendMessage(android.view.View view)
    {
        ClientBluetoothHub.getInstance().sendSongRequestUpstream("A URI!");
    }
}