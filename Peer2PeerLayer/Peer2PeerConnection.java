package com.example.speakez.Peer2PeerLayer;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Peer2PeerConnection
{
    private InputStream mInputStream;
    private OutputStream mOutputStream;

    public Peer2PeerConnection(
        InputStream inputStream,
        OutputStream outputStream)
    {
        mInputStream = inputStream;
        mOutputStream = outputStream;
    }

    public void openConnection()
    {
        InboxThread inboxThread = new InboxThread(mInputStream);
        inboxThread.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void sendMessage(String message) throws IOException
    {
        String messageWithEndSequence = message + BluetoothCommunicationConstants.MESSAGE_END;
        mOutputStream.write(messageWithEndSequence.getBytes());
    }

    public void close() throws IOException
    {
        mInputStream.close();
        mOutputStream.close();
    }
}
