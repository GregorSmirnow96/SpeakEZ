package com.example.speakez.Peer2PeerLayer.HostCommunication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;

import com.example.speakez.Peer2PeerLayer.Peer2PeerConnection;

import org.mortbay.log.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class ServerSocketThread extends AsyncTask<Void, Void, Void>
{
    private UUID mHostUUID;
    private BluetoothAdapter mBluetoothAdapter;
    private InputStream mInputStream;
    private OutputStream mOutputStream;
    private boolean mThreadIsAlive;

    public ServerSocketThread(UUID hostUUID)
    {
        mHostUUID = hostUUID;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mThreadIsAlive = true;
    }

    @Override
    protected Void doInBackground(Void... voids)
    {
        try
        {
            serveClientRequests();
        }
        catch (IOException e)
        {
            Log.debug(e.getCause());
        }

        return null;
    }

    public void serveClientRequests() throws IOException
    {
        BluetoothServerSocket serverSocket = mBluetoothAdapter
            .listenUsingInsecureRfcommWithServiceRecord(
                "SpeakEZ Host",
                mHostUUID);

        while (mThreadIsAlive)
        {
            BluetoothSocket socket = serverSocket.accept();
            mInputStream = socket.getInputStream();
            mOutputStream = socket.getOutputStream();

            Peer2PeerConnection clientConnection = new Peer2PeerConnection(
                mInputStream,
                mOutputStream);
            clientConnection.openConnection();
            HostBluetoothHub.getInstance().registerDownstreamDeviceConnection(clientConnection);
        }
    }

    public void killThread()
    {
        mThreadIsAlive = false;
    }
}
