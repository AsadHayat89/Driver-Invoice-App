package com.example.newtravel.Static;

import android.bluetooth.BluetoothDevice;

import java.util.ArrayList;

public class StaticHolder {
    public static int lastData=0;
    public static ArrayList<Integer> txtData=new ArrayList<Integer>();
    public static BluetoothDevice mmDevice;
    public static boolean checkConnection=false;

}
