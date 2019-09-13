package com.example.speakez.ComponentInterfaces;

/**
 * @summary
 *  This is the interface exposed by the module responsible for handling clients' bluetooth
 *  communication.
 */
public interface IClientBluetoothHub
{
    /**
     * @summary
     *  This method sets the device that playlist-update-requests will be sent to.
     * @param deviceInfo
     *  The information needed to connect to the upstream device.
     */
    void setUpstreamDevice(Object deviceInfo); /* Once the type is known, change the input type. */

    /**
     * @summary
     *  This method sends a request to the upstream device to remove the specified song from the
     *  playlist.
     * @param songUri
     *  The Spotify URI of the song to be removed.
     */
    void sendSongRemovalRequestUpstream(String songUri);

    /**
     * @summary
     *  This method sends a request to the upstream device to add the specified song to the
     *  playlist.
     * @param songUri
     *  The Spotify URI of the song to be added.
     */
    void sendSongRequestUpstream(String songUri);
}
