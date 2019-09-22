package com.example.speakez.Peer2PeerLayer.ClientCommunication;

import com.example.speakez.ComponentInterfaces.IClientBluetoothHub;
import com.example.speakez.ComponentInterfaces.IHostBluetoothHub;
import com.example.speakez.Peer2PeerLayer.HostCommunication.ServerSocketThread;
import com.example.speakez.Peer2PeerLayer.Peer2PeerConnection;

import org.mortbay.log.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

public class ClientBluetoothHub implements IClientBluetoothHub
{
    private Peer2PeerConnection mHostConnection;

    private static ReentrantLock sInstanceRetrievalLock = new ReentrantLock();
    private static ClientBluetoothHub sInstance;

    public static ClientBluetoothHub getInstance()
    {
        sInstanceRetrievalLock.lock();
        sInstance = sInstance == null
            ? new ClientBluetoothHub()
            : sInstance;
        sInstanceRetrievalLock.unlock();

        return sInstance;
    }

    private ClientBluetoothHub()
    {
    }

    @Override
    public void setUpstreamDeviceConnection(Peer2PeerConnection hostConnection)
    {
        mHostConnection = hostConnection;
    }

    @Override
    public void sendSongRemovalRequestUpstream(String songUri)
    {
        try
        {
            mHostConnection.sendMessage(songUri); // Format this into a request with URI + Request-type = REMOVE_SONG.
        }
        catch (Exception e)
        {
            Log.debug(e.getCause());
        }
    }

    @Override
    public void sendSongRequestUpstream(String songUri)
    {
        try
        {
            mHostConnection.sendMessage(songUri); // Format this into a request with URI + Request-type = ADD_SONG.
        }
        catch (Exception e)
        {
            Log.debug(e.getCause());
        }
    }
}
