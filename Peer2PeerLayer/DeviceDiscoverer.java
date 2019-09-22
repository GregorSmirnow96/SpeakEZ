package com.example.speakez.Peer2PeerLayer;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class DeviceDiscoverer
{
    private Activity mCurrentActivity;
    private List<BluetoothDevice> mNearbyBluetoothDevices;
    private BluetoothAdapter mBluetoothAdapter;
    private BroadcastReceiver mBroadcastReceiver;

    public DeviceDiscoverer(
        Activity currentActivity,
        List<BluetoothDevice> nearbyBluetoothDevices,
        BluetoothAdapter bluetoothAdapter,
        BroadcastReceiver broadcastReceiver)
    {
        mCurrentActivity = currentActivity;
        mNearbyBluetoothDevices = nearbyBluetoothDevices;
        mBluetoothAdapter = bluetoothAdapter;
        mBroadcastReceiver = broadcastReceiver;
    }

    public void startDeviceDiscovery()
    {
        IntentFilter bluetoothFilter = new IntentFilter();
        bluetoothFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        bluetoothFilter.addAction(BluetoothDevice.ACTION_FOUND);
        bluetoothFilter.addAction(BluetoothDevice.ACTION_UUID);
        bluetoothFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        mCurrentActivity.registerReceiver(
            mBroadcastReceiver,
            bluetoothFilter);

        int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
        ActivityCompat.requestPermissions(
            mCurrentActivity,
            new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
            MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);

        mBluetoothAdapter.startDiscovery();
    }
}
