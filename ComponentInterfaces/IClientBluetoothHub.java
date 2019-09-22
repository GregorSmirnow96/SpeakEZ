package com.example.speakez.ComponentInterfaces;

import com.example.speakez.Peer2PeerLayer.Peer2PeerConnection;

/**
 * @summary
 *  This is the interface exposed by the module responsible for handling clients' bluetooth
 *  communication.
 */
public interface IClientBluetoothHub
{
    /**
     * @summary
     *  This method sets the connection to the device that playlist-update-requests will be sent to.
     * @param hostConnection
     *  The connection between this device and the upstream device.
     */
    void setUpstreamDeviceConnection(Peer2PeerConnection hostConnection);

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
