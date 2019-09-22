package com.example.speakez.Peer2PeerLayer;

import android.os.AsyncTask;

import com.example.speakez.ComponentInterfaces.IHost;
import com.example.speakez.UserModelLayer.HostModel.HostSession;

import java.io.IOException;
import java.io.InputStream;

public class InboxThread extends AsyncTask<Void, Void, Void>
{
    private InputStream mInputStream;
    private boolean mThreadIsAlive;
    private IHost mHost;

    public InboxThread(InputStream inputStream)
    {
        mInputStream = inputStream;
        mThreadIsAlive = true;
        mHost = HostSession.getInstance();
    }

    @Override
    protected Void doInBackground(Void... voids)
    {
        int i = 0;
        try
        {
            readIncomingMessages();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private void readIncomingMessages() throws IOException
    {
        while (mThreadIsAlive)
        {
            String nextMessage = readNextMessage();
            mHost.enqueueRequest(nextMessage);
        }
    }

    private String readNextMessage() throws IOException
    {
        String message = "";
        while (!messageHasBeenReadFully(message))
        {
            message += (char) mInputStream.read();
        }

        return message;
    }

    private boolean messageHasBeenReadFully(String message)
    {
        return message.contains(BluetoothCommunicationConstants.MESSAGE_END);
    }

    public void killThread()
    {
        mThreadIsAlive = false;
    }
}
