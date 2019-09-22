package com.example.speakez.Peer2PeerLayer;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import java.util.List;

public class SpeakEZBroadcastReceiver extends BroadcastReceiver
{
    private List<BluetoothDevice> mNearbyBluetoothDevices;

    public SpeakEZBroadcastReceiver(List<BluetoothDevice> nearbyBluetoothDevices)
    {
        mNearbyBluetoothDevices = nearbyBluetoothDevices;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();

        if (BluetoothDevice.ACTION_UUID.equals(action))
        {
            BluetoothDevice deviceExtra = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            Parcelable[] uuidExtra = intent.getParcelableArrayExtra(BluetoothDevice.EXTRA_UUID);
            System.out.println("DeviceExtra address - " + deviceExtra.getAddress());
            if (uuidExtra != null)
            {
                for (Parcelable p : uuidExtra)
                {
                    System.out.println("uuidExtra - " + p);
                    // uuid = p.toString();
                    return;
                }
            }
            else
            {
                System.out.println("uuidExtra is still null");
            }
        }

        // Finding devices
        if (BluetoothDevice.ACTION_FOUND.equals(action))
        {
            // Get the BluetoothDevice object from the Intent
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            // Add the name and address to an array adapter to show in a ListView
            //     mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            mNearbyBluetoothDevices.add(device);
        }
        else
        {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        }
    }
}
