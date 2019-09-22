package com.example.speakez.Peer2PeerLayer;

import android.bluetooth.BluetoothDevice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DevicePairer
{
    public boolean removeBond(BluetoothDevice bluetoothDevice) throws
        ClassNotFoundException,
        NoSuchMethodException,
        InvocationTargetException,
        IllegalAccessException
    {
        Class bluetoothDeviceClass = Class.forName("android.bluetooth.BluetoothDevice");
        Method removeBondMethod = bluetoothDeviceClass.getMethod("removeBond");
        return (boolean) removeBondMethod.invoke(bluetoothDevice);
    }


    public boolean createBond(BluetoothDevice bluetoothDevice) throws
        ClassNotFoundException,
        NoSuchMethodException,
        InvocationTargetException,
        IllegalAccessException
    {
        Class bluetoothDeviceClass = Class.forName("android.bluetooth.BluetoothDevice");
        Method createBondMethod = bluetoothDeviceClass.getMethod("createBond");
        return (boolean) createBondMethod.invoke(bluetoothDevice);
    }
}
