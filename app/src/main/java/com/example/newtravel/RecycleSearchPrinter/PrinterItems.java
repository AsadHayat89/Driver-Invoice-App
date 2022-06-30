package com.example.newtravel.RecycleSearchPrinter;

import android.bluetooth.BluetoothDevice;

import java.io.Serializable;

public class PrinterItems{
    private String name;
    private BluetoothDevice Device;

    public BluetoothDevice getDevice() {
        return Device;
    }

    public PrinterItems(String name, BluetoothDevice device) {
        this.name = name;
        Device = device;
    }

    public void setDevice(BluetoothDevice device) {
        Device = device;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
