package com.example.speakez.ComponentInterfaces;

/**
 * @summary
 *  This is the interface exposed by the module responsible for handling the host's bluetooth
 *  communication.
 */
public interface IHostBluetoothHub
{
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
     * @param deviceInfo
     *  The information needed to connect to the upstream device.
     */
    void registerDownstreamDevice(Object deviceInfo); /* Once the type is known, change the input type. */

    /**
     * @summary
     *  This method registers a downstream device with the host.
     * @param deviceInfo
     *  The information needed to connect to the upstream device.
     */
    void unregisterDownstreamDevice(Object deviceInfo); /* Once the type is known, change the input type. */
}
