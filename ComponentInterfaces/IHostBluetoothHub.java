package com.example.speakez.ComponentInterfaces;

import com.example.speakez.Peer2PeerLayer.Peer2PeerConnection;

/**
 * @summary
 *  This is the interface exposed by the module responsible for handling the host's bluetooth
 *  communication.
 */
public interface IHostBluetoothHub
{
    /**
     * @summary
     *  This method starts the host server so that clients can connect and interact with the
     *  playlist.
     */
    void launchServer();

    /**
     * @summary
     *  This method sends a json encoding of the playlist to all registered clients. This allows
     *  them to see which songs are coming up and remove / add songs appropriately.
     */
    void sendPlaylistStateToAllDownstreamDevices();

    /**
     * @summary
     *  This method registers a downstream device with the host. Down stream devices receive updated
     *  playlist states, and they have the ability to add / remove songs from the playlist.
     * @param clientConnection
     *  The connection between this device and the upstream device.
     */
    void registerDownstreamDeviceConnection(Peer2PeerConnection clientConnection);

    /**
     * @summary
     *  This method registers a downstream device with the host.
     * @param clientConnection
     *  The connection between this device and the upstream device.
     */
    void unregisterDownstreamDeviceConnection(Peer2PeerConnection clientConnection);
}
