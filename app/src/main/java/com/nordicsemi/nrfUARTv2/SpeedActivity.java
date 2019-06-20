package com.nordicsemi.nrfUARTv2;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;


import com.github.mikephil.charting.components.YAxis;
import com.nordicsemi.nrfUARTv2.UartService;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;

import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;

//
//import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.URI;
import java.util.Scanner;
//

//그래프출력
import android.graphics.Color;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Delayed;
import java.io.File;
import java.lang.Object;
//


public class SpeedActivity extends Activity {

    private LineChart lineChart;
    private ListView messageListView;
    private ArrayAdapter<String> listAdapter;
    public double[]  testcal2 = new double[500000]; //실험  속력
    public double[]  testnewcal1 = new double[500000]; // 실험  길이를 맞출 속력
    public double maxcal1 = 0;
    public double maxcal2 = 0;
    public int len2=0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);
        messageListView = (ListView) findViewById(R.id.listMessage);
        listAdapter = new ArrayAdapter<String>(this, R.layout.message_detail);
        messageListView.setAdapter(listAdapter);
        messageListView.setDivider(null);

        testcal2 = MainActivity.Trans_testcal2;
        testnewcal1=MainActivity.Trans_testnewcal1;
        maxcal1 = MainActivity.Trans_maxcal1;
        maxcal2 = MainActivity.Trans_maxcal2;
        len2 = MainActivity.Trans_len2;

        lineChart = (LineChart) findViewById(R.id.chart);

        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
        ArrayList<Entry> valsComp2 = new ArrayList<Entry>();

        ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>();

        LineDataSet lineDataSet1 = new LineDataSet(valsComp1, "속도 (예시)");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.RED);

        LineDataSet lineDataSet2 = new LineDataSet(valsComp2, "속도 (신규)");
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setColor(Color.BLUE);

        for(int i=0; i<len2; i++)
        {
            if ( i==0)
            {
                valsComp1.add(new Entry((float)-1,i));
                valsComp2.add(new Entry((float)-1,i));
            }else
            {
                valsComp1.add(new Entry((float)testcal2[i],i));
                valsComp2.add(new Entry((float)testnewcal1[i],i));
            }
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for(int p=0; p<len2; p++)
        {
            xVals.add(p+"");
        }

        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);

        lineChart.setData(new LineData(xVals,lineDataSets));
        lineChart.setVisibleXRangeMaximum(65f);
        YAxis yAxis = lineChart.getAxisLeft();
        double result=0;
        if(maxcal1>maxcal2)
        {
            result = maxcal1;
        }else
        {
            result = maxcal2;
        }
        if(result<10)
        {
            yAxis.setAxisMinValue(-40f);
        }else if (result<30)
        {
            yAxis.setAxisMinValue(-100f);
        }else if (result<70)
        {
            yAxis.setAxisMinValue(-4000f);
        }
        yAxis.setDrawGridLines(true);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        lineChart.invalidate(); //실시간 그래프 찍기
        listAdapter.add("신규 테이블의 스윙 최고 속도 : " + String.format("%.2f",maxcal1)+"km/h");
        listAdapter.add("예시 테이블의 스윙 최고 속도 : " + String.format("%.2f",maxcal2)+"km/h");
    }
}
