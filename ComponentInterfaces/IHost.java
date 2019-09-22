package com.example.speakez.ComponentInterfaces;

/**
 * @summary
 *  This interface lays out all actions that can be invoked by the host.
 */
public interface IHost extends IPlaylistUser
{
    /**
     * @summary
     *  This method adds a client's request to a queue of requests.
     * @param request
     *  The client request being enqueued.
     */
    void enqueueRequest(String request);
}
