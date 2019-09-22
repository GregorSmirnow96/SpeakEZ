package com.example.speakez.Peer2PeerLayer.HostCommunication;

import com.example.speakez.ComponentInterfaces.IHostBluetoothHub;
import com.example.speakez.Peer2PeerLayer.Peer2PeerConnection;

import org.mortbay.log.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

public class HostBluetoothHub implements IHostBluetoothHub
{
    private List<Peer2PeerConnection> mClientConnections;
    private boolean mHostServerIsRunning;

    private static ReentrantLock sClientConnectionsLock = new ReentrantLock();
    private static ReentrantLock sInstanceRetrievalLock = new ReentrantLock();
    private static HostBluetoothHub sInstance;

    public static HostBluetoothHub getInstance()
    {
        sInstanceRetrievalLock.lock();
        sInstance = sInstance == null
            ? new HostBluetoothHub()
            : sInstance;
        sInstanceRetrievalLock.unlock();

        return sInstance;
    }

    private HostBluetoothHub()
    {
        mClientConnections = new ArrayList<>();
        mHostServerIsRunning = false;
    }

    @Override
    public void launchServer()
    {
        if (!mHostServerIsRunning)
        {
            UUID serverUUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            ServerSocketThread serverSocketThread = new ServerSocketThread(serverUUID);
            serverSocketThread.execute();

            mHostServerIsRunning = true;
        }
    }

    @Override
    public void sendPlaylistStateToAllDownstreamDevices()
    {
        sClientConnectionsLock.lock();
        for (Peer2PeerConnection clientConnection : mClientConnections)
        {
            try
            {
                clientConnection.sendMessage("This string should encode the playlist state.");
            }
            catch (Exception e)
            {
                Log.debug(e.getCause());
            }
        }
        sClientConnectionsLock.unlock();
    }

    @Override
    public void registerDownstreamDeviceConnection(Peer2PeerConnection clientConnection)
    {
        sClientConnectionsLock.lock();
        mClientConnections.add(clientConnection);
        sClientConnectionsLock.unlock();
    }

    @Override
    public void unregisterDownstreamDeviceConnection(Peer2PeerConnection clientConnection)
    {
        sClientConnectionsLock.lock();
        mClientConnections.remove(clientConnection);
        sClientConnectionsLock.unlock();
    }
}
