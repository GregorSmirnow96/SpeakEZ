package com.example.speakez.UserModelLayer.HostModel;

import com.example.speakez.ComponentInterfaces.IHost;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class HostSession implements IHost
{
    private List<String> mPendingRequests;

    private static ReentrantLock sPendingRequestsLock = new ReentrantLock();
    private static ReentrantLock sInstanceRetrievalLock = new ReentrantLock();
    private static HostSession sInstance;

    public static HostSession getInstance()
    {
        sInstanceRetrievalLock.lock();
        sInstance = sInstance == null
            ? sInstance = new HostSession()
            : sInstance;
        sInstanceRetrievalLock.unlock();

        return sInstance;
    }

    private HostSession()
    {
        mPendingRequests = new ArrayList<>();
    }

    public void enqueueRequest(String request)
    {
        sPendingRequestsLock.lock();
        mPendingRequests.add(request);
        sPendingRequestsLock.unlock();
    }

    @Override
    public void enqueueSong(String songUri)
    {

    }

    @Override
    public void removeSong(String songUri)
    {

    }
}
