package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Queue;

public class NRFBLEModule {
    public static final String TAG = "NRFBLEModule";
    private BluetoothAdapter mBtAdapter = null;

    private Context context = null;
    private Activity activity = null;
    private static NRFBLEModule instance = null;
    private Queue<String> m_recv_queue = new LinkedList<String>();

    public static NRFBLEModule getInstance() {
        if (instance == null) {
            instance = new NRFBLEModule();
        }

        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
        this.activity = (Activity) this.context;
    }

    public void setmBtAdapter(BluetoothAdapter btAdapter) {
        this.mBtAdapter = btAdapter;
    }

    public void ClearQueue() {
        m_recv_queue.clear();
    }

    public void PushQueue(String data) {
        m_recv_queue.add(data);
    }

    public String PopQueue() {
        if (m_recv_queue.isEmpty())
            return null;

        return m_recv_queue.poll();
    }

    public void ConnectToggle() {
        if (!mBtAdapter.isEnabled()) {
            Log.i(TAG, "onClick - BT not enabled yet");
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            this.activity.startActivityForResult(enableIntent, MainActivity.REQUEST_ENABLE_BT);
        }
        else {
            Intent newIntent = new Intent(this.context, DeviceListActivity.class);
            this.activity.startActivityForResult(newIntent, MainActivity.REQUEST_SELECT_DEVICE);
        }
    }

    private void showMessage(String msg) {
        Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show();

    }
}