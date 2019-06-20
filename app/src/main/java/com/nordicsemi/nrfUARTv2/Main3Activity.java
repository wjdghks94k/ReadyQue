
/*
 * Copyright (c) 2015, Nordic Semiconductor
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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

public class Main3Activity extends Activity implements RadioGroup.OnCheckedChangeListener {
    public static final int REQUEST_SELECT_DEVICE = 1;
    public static final int REQUEST_ENABLE_BT = 2;
    public static final int UART_PROFILE_READY = 10;
    public static final String TAG = "nRFUART";
    public static final int UART_PROFILE_CONNECTED = 20;
    public static final int UART_PROFILE_DISCONNECTED = 21;
    public static final int STATE_OFF = 10;
    public String Temp="2"; //임시저장변수
    public String[] data = new String[100000]; //스필릿한 값 저장 저장배열

    TextView mRemoteRssiVal;
    RadioGroup mRg;
    private int mState = UART_PROFILE_DISCONNECTED;
    private UartService mService = null;
    private BluetoothDevice mDevice = null;
    private BluetoothAdapter mBtAdapter = null;
    private ListView messageListView;
    private ArrayAdapter<String> listAdapter;
    private Button btnConnectDisconnect,btnSend;
    private EditText edtMessage;
    //추가 변수
    private static String IP_ADDRESS = "";
    private static String TAG1 = "phptest";
    TextView txtview;
    phpdo task;
    init task2;
    InsertData task1;
    phpdo2 task3;

    //디비값 가져오기위한 변수
    public int[] data2 = new int[100000]; //스필릿한 값 저장 저장배열
    public int[] data3 = new int[100000]; //스플릿한 값 저장 저장배열
    public int[] x1 = new int[50000]; //스필릿한 값 저장 저장배열
    public int[] y1 = new int[50000]; //스필릿한 값 저장 저장배열
    public int[] z1 = new int[50000]; //스필릿한 값 저장 저장배열
    public int[] x_1 = new int[50000]; //스필릿한 값 저장 저장배열
    public int[] y_1 = new int[50000]; //스필릿한 값 저장 저장배열
    public int[] z_1 = new int[50000]; //스필릿한 값 저장 저장배열
    public int[] j1 = new int[50000]; //스필릿한 값 저장 저장배열
    public int[] b1 = new int[50000]; //스필릿한 값 저장 저장배열
    public int[] num1 = new int[50000]; //스필릿한 값 저장 저장배열
    public int[] x2 = new int[50000]; //스필릿한 값 저장 저장배열
    public int[] y2 = new int[50000]; //스필릿한 값 저장 저장배열
    public int[] z2 = new int[50000]; //스필릿한 값 저장 저장배열
    public int[] x_2 = new int[50000]; //스필릿한 값 저장 저장배열
    public int[] y_2 = new int[50000]; //스필릿한 값 저장 저장배열
    public int[] z_2 = new int[50000]; //스필릿한 값 저장 저장배열
    public int[] j2 = new int[50000]; //스필릿한 값 저장 저장배열
    public int[] b2 = new int[50000]; //스필릿한 값 저장 저장배열
    public int[] num2 = new int[50000]; //스필릿한 값 저장 저장배열
    public int compare ; //기존디비값 새로운 디비값 구분
    //
    public int len1;
    public int len2; //디비 값 개수 비교

    public int print_count =0; // 출력을 한번만 하기위해

    //테이블값을 이용한 속도 계산을 위한 변수
    public double[]  cal1 = new double[500000]; //속력
    public double[]  newcal1 = new double[500000]; // 길이를 맞출 속력
    public double[]  testcal1 = new double[500000]; //실험  속력
    public double[]  testcal2 = new double[500000]; //실험  속력
    public double[]  testnewcal1 = new double[500000]; // 실험  길이를 맞출 속력

    public double maxcal1 = 0;
    public double maxcal2 = 0;

    //좌표 비교변수
    public int x;
    public int y;

    //전달변수
    public static double[] Trans_testcal2 = new double[500000];
    public static double[] Trans_testnewcal1 = new double[500000];
    public static double Trans_maxcal1 = 0;
    public static double Trans_maxcal2 = 0;
    public static int Trans_len2 = 0;

    //그래프를 위한 변수
    private LineChart lineChart;
    public double[] AX_angle1 = new double[50000]; // 가속도 x를 각도로 바꿔준값이 채워지는 배열
    public double[] AY_angle1 = new double[50000]; // 가속도 y를 각도로 바꿔준값이 채워지는 배열
    public double[] AZ_angle1 = new double[50000]; // 가속도 z를 각도로 바꿔준값이 채워지는 배열
    public double[] GX_calculate1 = new double[50000]; // 각속도 x를 각도로 계산해주기 위한 배열
    public double[] GY_calculate1 = new double[50000]; // 각속도 y를 각도로 계산해주기 위한 배열
    public double[] GZ_calculate1 = new double[50000]; // 각속도 z를 각도로 계산해주기 위한 배열
    public double[] GX_angle1 = new double[50000]; // 각속도 x를 각도로 바꿔준값이 채워지는 배열
    public double[] GY_angle1 = new double[50000]; // 각속도 y를 각도로 바꿔준값이 채워지는 배열
    public double[] GZ_angle1 = new double[50000]; // 각속도 z를 각도로 바꿔준값이 채워지는 배열

    public double[] AX_angle2 = new double[50000]; // 가속도 x를 각도로 바꿔준값이 채워지는 배열
    public double[] AY_angle2 = new double[50000]; // 가속도 y를 각도로 바꿔준값이 채워지는 배열
    public double[] AZ_angle2 = new double[50000]; // 가속도 z를 각도로 바꿔준값이 채워지는 배열
    public double[] GX_calculate2 = new double[50000]; // 각속도 x를 각도로 계산해주기 위한 배열
    public double[] GY_calculate2 = new double[50000]; // 각속도 y를 각도로 계산해주기 위한 배열
    public double[] GZ_calculate2 = new double[50000]; // 각속도 z를 각도로 계산해주기 위한 배열
    public double[] GX_angle2 = new double[50000]; // 각속도 x를 각도로 바꿔준값이 채워지는 배열
    public double[] GY_angle2 = new double[50000]; // 각속도 y를 각도로 바꿔준값이 채워지는 배열
    public double[] GZ_angle2 = new double[50000]; // 각속도 z를 각도로 바꿔준값이 채워지는 배열
    public double[] FX_angle1 = new double[50000];
    public double[] FY_angle1 = new double[50000];
    public double[] FZ_angle1 = new double[50000];
    public double[] FX_angle2 = new double[50000];
    public double[] FY_angle2 = new double[50000];
    public double[] FZ_angle2 = new double[50000];
    public double result_F1=0;
    public double result_F2=0;
    public double result_F3=0;
    //터치값으로 disconnect
    public int touch_value;
    //추가변수 끝
    /*블루트스 연결에 사용*/
    TextView mTvBluetoothStatus;
    TextView mTvReceiveData;
    TextView mTvSendData;
    Button mBtnBluetoothOn;
    Button mBtnBluetoothOff;
    Button mBtnConnect;
    Button mBtnSendData;

    BluetoothAdapter mBluetoothAdapter;
    Set<BluetoothDevice> mPairedDevices;
    List<String> mListPairedDevices;

    Handler mBluetoothHandler;
    ConnectedBluetoothThread mThreadConnectedBluetooth;
    BluetoothDevice mBluetoothDevice;
    BluetoothSocket mBluetoothSocket;

    final static int BT_REQUEST_ENABLE = 1;
    final static int BT_MESSAGE_READ = 2;
    final static int BT_CONNECTING_STATUS = 3;
    final static UUID BT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private LineChart hit_lineChart;
    public String[] hit_data = new String[10]; //스필릿한 값 저장 저장배열
    private ArrayAdapter<String> hit_listAdapter;
    /*블루투스 연결관련 끝*/

    //진동 관련 변수

    Vibrator vibrator;
    Button buttonSpeed;

    public static double minimum(double a, double b, double c)
    {
        double minNumber = a;
        if(minNumber>b) minNumber=b;
        if(minNumber>c) minNumber=c;
        return minNumber;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main3);
        IP_ADDRESS = MainActivity.IP_ADDRESS;
        buttonSpeed =(Button) findViewById(R.id.btnSpeed);
        //속도버튼 부분
        buttonSpeed.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main3Activity.this, Speed3Activity.class);
                startActivity(intent);
            }
        });

        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBtAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        messageListView = (ListView) findViewById(R.id.listMessage);
        listAdapter = new ArrayAdapter<String>(this, R.layout.message_detail);
        messageListView.setAdapter(listAdapter);
        messageListView.setDivider(null);
        btnConnectDisconnect=(Button) findViewById(R.id.btn_select);
        btnSend=(Button) findViewById(R.id.sendButton);
        edtMessage = (EditText) findViewById(R.id.sendText);
        service_init();

        //////////////////////////bluetooth 연결////////////////
        mTvBluetoothStatus = (TextView) findViewById(R.id.tvBluetoothStatus);
        mTvReceiveData = (TextView) findViewById(R.id.tvReceiveData);
        mTvSendData = (EditText) findViewById(R.id.tvSendData);
        mBtnBluetoothOn = (Button) findViewById(R.id.btnBluetoothOn);
        mBtnBluetoothOff = (Button) findViewById(R.id.btnBluetoothOff);
        mBtnConnect = (Button) findViewById(R.id.btnConnect);
        mBtnSendData = (Button) findViewById(R.id.btnSendData);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


        mBtnBluetoothOn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothOn();
            }
        });
        mBtnBluetoothOff.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothOff();
            }
        });
        mBtnConnect.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                listPairedDevices();
            }
        });
        mBtnSendData.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mThreadConnectedBluetooth != null) {
                    mThreadConnectedBluetooth.write(mTvSendData.getText().toString());
                    mTvSendData.setText("");
                }
            }
        });
        mBluetoothHandler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == BT_MESSAGE_READ) {
                    String readMessage = null;
                    try {
                        readMessage = new String((byte[]) msg.obj, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    int n = 0;
                    hit_data[0]=String.valueOf(readMessage.substring(0,1));
                    /*두번째 값이 두자리인지 한자리인지 구분해서 split*/
                    //Object a = readMessage.substring(3,5);
                    hit_data[1]=String.valueOf(readMessage.substring(2,4)); //두자리 일단 저장
                    if (hit_data[1].charAt(1)=='*'){ //한자리면
                        hit_data[1] = String.valueOf(hit_data[1].charAt(0));
                    }

                    //mTvReceiveData.setText("궁금s : "+String.valueOf(readMessage.substring(3,4))+" readMessage:" + readMessage + "데이터값:" + hit_data[0]+hit_data[1]);

                    int[] x = {Integer.valueOf(hit_data[0])};// 가속도 x

                    int[] y = {Integer.valueOf(hit_data[1])}; // 가속도 y

                    hit_lineChart = (LineChart) findViewById(R.id.hit_chart);

                    ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
                    ArrayList<Entry> valsComp2 = new ArrayList<Entry>();


                    ArrayList<LineDataSet> lineDataSets = new ArrayList<>();

                    LineDataSet lineDataSet1 = new LineDataSet(valsComp1, "");
                    lineDataSet1.setDrawCircles(true);
                    lineDataSet1.setCircleSize(10);
                    lineDataSet1.setCircleColor(Color.parseColor("RED"));
                    lineDataSet1.setCircleColorHole(Color.RED);

                    LineDataSet lineDataSet2 = new LineDataSet(valsComp2, "");
                    lineDataSet2.setDrawCircles(false);
                    lineDataSet2.setCircleSize(10);
                    lineDataSet2.setCircleColor(Color.parseColor("RED"));
                    lineDataSet2.setCircleColorHole(Color.RED);

                    for(int i=0; i<x[0]+1; i++)
                    {
                        if(i==x[0])
                        {
                            valsComp1.add(new Entry(15-Integer.valueOf(y[0]),i));
                        }
                        if(i==0)
                        {
                            valsComp2.add(new Entry(15,i));
                        }
                    }

                    ArrayList<String> xVals = new ArrayList<String>();

                    for(int p=0; p<11; p++)
                    {
                        xVals.add(p+"");
                    }

                    lineDataSets.add(lineDataSet1);
                    lineDataSets.add(lineDataSet2);

                    hit_lineChart.setData(new LineData(xVals,lineDataSets));
                    hit_lineChart.setVisibleXRangeMaximum(65f);
                    hit_lineChart.invalidate(); //실시간 그래프 찍기
                }
            }


        };
        ///////////////////////여기까지///////////////////

        // Handle Disconnect & Connect button
        btnConnectDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBtAdapter.isEnabled()) {
                    Log.i(TAG, "onClick - BT not enabled yet");
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
                }
                else {
                    if (btnConnectDisconnect.getText().equals("Connect (끌어치기)")){

                        //Connect button pressed, open DeviceListActivity class, with popup windows that scan for devices

                        Intent newIntent = new Intent(Main3Activity.this, DeviceListActivity.class);
                        startActivityForResult(newIntent, REQUEST_SELECT_DEVICE);
                    } else {
                        //Disconnect button pressed
                        if (mDevice!=null)
                        {
                            mService.disconnect();

                        }
                    }
                }
            }
        });
        // Handle Send button
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.sendText);
                String message = editText.getText().toString();
                byte[] value;
                try {
                    //send data to service
                    value = message.getBytes("UTF-8");
                    mService.writeRXCharacteristic(value);
                    //Update the log with time stamp
                    String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                    listAdapter.add("["+currentDateTimeString+"] TX: "+ message);
                    messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                    edtMessage.setText("");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        });

        // Set initial UI state

        //DB에서 값꺼내와서 계산
        Button buttonInsert = (Button)findViewById(R.id.DBOUT);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num = "1";

                task = new phpdo();
                task.execute(num,IP_ADDRESS);

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        String num = "2";
                        task = new phpdo();
                        task.execute(num,IP_ADDRESS);//여기에 딜레이 후 시작할 작업들을 입력

                    }
                }, 1000);// 1초 정도 딜레이를 준 후 시작
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        listAdapter.add("최종 스트로크 유사율 : "+String.format("%.2f",(result_F1+result_F2+result_F3)/3)+"%"+"(x축 : "+String.format("%.2f",result_F1)+"%, "+"y축 : "+String.format("%.2f",result_F2)+"%, "+"z축 : "+String.format("%.2f",result_F3)+"%)");
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        Trans_testcal2 = testcal2;
                        Trans_testnewcal1 = testnewcal1;
                        Trans_maxcal1 = maxcal1;
                        Trans_maxcal2 = maxcal2;
                        Trans_len2 = len2;
                        int max = 70, min = -70 , up =0 , down=0, right=0, left=0;
                        int upmaxcount=0, downmincount=0 , rightcount=0, leftcount=0;
                        int len=0;
                        x=Integer.valueOf(hit_data[0]);
                        y=Integer.valueOf(hit_data[1]);
                        for (int i =0 ; i<len1 ; i++)
                        {
                            if (FX_angle1[i] > max)
                            {
                                downmincount=1;
                            }
                            if (FX_angle1[i] < min)
                            {
                                upmaxcount=1;
                            }
                            if (FZ_angle1[i] > max)
                            {
                                rightcount=1;
                            }
                            if (FZ_angle1[i] < min)
                            {
                                leftcount=1;
                            }
                        }

                        if (upmaxcount==1)
                        {
                            up=1;
                        }
                        if(downmincount==1)
                        {
                            down=1;
                        }

                        if (rightcount==1)
                        {
                            right=1;
                        }
                        if(leftcount==1)
                        {
                            left=1;
                        }

                        //상하 좌우 출력
                        if(up==1 && down==1)
                        {
                            listAdapter.add("상하로 흔들렸습니다.");
                        }else if (up==1)
                        {
                            listAdapter.add("위로 흔들렸습니다.");
                        }else if(down ==1)
                        {
                            listAdapter.add("아래로 흔들렸습니다.");
                        }else
                        {
                            listAdapter.add("상하 양호 합니다.");
                        }
                        if(right==1 && left==1)
                        {
                            listAdapter.add("좌우로 흔들렸습니다.");
                        }else if (right==1)
                        {
                            listAdapter.add("우로 흔들렸습니다.");
                        }else if(left ==1)
                        {
                            listAdapter.add("좌로 흔들렸습니다.");
                        }else
                        {
                            listAdapter.add("좌우 양호 합니다.");
                        }
                        //좌표로 이용한 타점 출력
                        if(x==1)
                        {
                            listAdapter.add("왼쪽 끝부분을 타격하였습니다. miss가 일어날 확률이 높습니다.");
                        }else if (x==9)
                        {
                            listAdapter.add("오른쪽 끝부분을 타격하였습니다. miss가 일어날 확률이 높습니다.");
                        }else if (y==1)
                        {
                            listAdapter.add("위쪽 끝부분을 타격하였습니다. miss가 일어날 확률이 높습니다.");
                        }else if (y==13)
                        {
                            listAdapter.add("아래쪽 끝부분을 타격하였습니다. miss가 일어날 확률이 높습니다.");
                        }else if (y<13 && y>=10)
                        {
                            listAdapter.add("끌어치기자세의 타점이 양호 합니다.");
                        }else if (y < 10)
                        {
                            listAdapter.add("끌어치기자세의 타점에서 위로 벗어났습니다.");
                        }
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                    }
                }, 2300);// 2.3초 정도 딜레이를 준 후 시작
                data2 = new int[100000];
                num1= new int[100000];
                x1= new int[100000];
                y1= new int[100000];
                z1= new int[100000];
                x_1= new int[100000];
                y_1= new int[100000];
                z_1= new int[100000];
                j1= new int[100000];
                b1= new int[100000];
                data3 = new int[100000];
                num2= new int[100000];
                x2= new int[100000];
                y2= new int[100000];
                z2= new int[100000];
                x_2= new int[100000];
                y_2= new int[100000];
                z_2= new int[100000];
                j2= new int[100000];
                b2= new int[100000];
                testcal1 = new double[500000];
                testcal2 = new double[500000];
                testnewcal1 = new double[500000];

            }
        });

        Button buttonInsert2 = (Button)findViewById(R.id.DBOUT2);
        buttonInsert2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num = "1";

                task3 = new phpdo2();
                task3.execute(num,IP_ADDRESS);

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        String num = "2";
                        task3 = new phpdo2();
                        task3.execute(num,IP_ADDRESS);;//여기에 딜레이 후 시작할 작업들을 입력

                    }
                }, 1000);// 1초 정도 딜레이를 준 후 시작

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        listAdapter.add("최종 스트로크 유사율 : "+String.format("%.2f",(result_F1+result_F2+result_F3)/3)+"%"+"(x축 : "+String.format("%.2f",result_F1)+"%, "+"y축 : "+String.format("%.2f",result_F2)+"%, "+"z축 : "+String.format("%.2f",result_F3)+"%)");
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        Trans_testcal2 = testcal2;
                        Trans_testnewcal1 = testnewcal1;
                        Trans_maxcal1 = maxcal1;
                        Trans_maxcal2 = maxcal2;
                        Trans_len2 = len2;
                        x=Integer.valueOf(hit_data[0]);
                        y=Integer.valueOf(hit_data[1]);
                        int max = 70, min = -70 , up =0 , down=0, right=0, left=0;
                        int upmaxcount=0, downmincount=0 , rightcount=0, leftcount=0;
                        int len=0;
                        for (int i =0 ; i<len1 ; i++)
                        {
                            if (FX_angle1[i] > max)
                            {
                                downmincount=1;
                            }
                            if (FX_angle1[i] < min)
                            {
                                upmaxcount=1;
                            }
                            if (FZ_angle1[i] > max)
                            {
                                rightcount=1;
                            }
                            if (FZ_angle1[i] < min)
                            {
                                leftcount=1;
                            }
                        }

                        if (upmaxcount==1)
                        {
                            up=1;
                        }
                        if(downmincount==1)
                        {
                            down=1;
                        }

                        if (rightcount==1)
                        {
                            right=1;
                        }
                        if(leftcount==1)
                        {
                            left=1;
                        }

                        //상하 좌우 출력
                        if(up==1 && down==1)
                        {
                            listAdapter.add("상하로 흔들렸습니다.");
                        }else if (up==1)
                        {
                            listAdapter.add("위로 흔들렸습니다.");
                        }else if(down ==1)
                        {
                            listAdapter.add("아래로 흔들렸습니다.");
                        }else
                        {
                            listAdapter.add("상하 양호 합니다.");
                        }
                        if(right==1 && left==1)
                        {
                            listAdapter.add("좌우로 흔들렸습니다.");
                        }else if (right==1)
                        {
                            listAdapter.add("우로 흔들렸습니다.");
                        }else if(left ==1)
                        {
                            listAdapter.add("좌로 흔들렸습니다.");
                        }else
                        {
                            listAdapter.add("좌우 양호 합니다.");
                        }
                        //좌표로 이용한 타점 출력
                        if(x==1)
                        {
                            listAdapter.add("왼쪽 끝부분을 타격하였습니다. miss가 일어날 확률이 높습니다.");
                        }else if (x==9)
                        {
                            listAdapter.add("오른쪽 끝부분을 타격하였습니다. miss가 일어날 확률이 높습니다.");
                        }else if (y==1)
                        {
                            listAdapter.add("위쪽 끝부분을 타격하였습니다. miss가 일어날 확률이 높습니다.");
                        }else if (y==13)
                        {
                            listAdapter.add("아래쪽 끝부분을 타격하였습니다. miss가 일어날 확률이 높습니다.");
                        }else if (y<13 && y>=10)
                        {
                            listAdapter.add("끌어치기자세의 타점이 양호 합니다.");
                        }else if (y < 10)
                        {
                            listAdapter.add("끌어치기자세의 타점에서 위로 벗어났습니다.");
                        }
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                    }
                }, 2300);// 2.3초 정도 딜레이를 준 후 시작
                data2=new int[100000];
                num1= new int[100000];
                x1= new int[100000];
                y1= new int[100000];
                z1= new int[100000];
                x_1= new int[100000];
                y_1= new int[100000];
                z_1= new int[100000];
                j1= new int[100000];
                b1= new int[100000];
                data3=new int[100000];
                num2= new int[100000];
                x2= new int[100000];
                y2= new int[100000];
                z2= new int[100000];
                x_2= new int[100000];
                y_2= new int[100000];
                z_2= new int[100000];
                j2= new int[100000];
                b2= new int[100000];
                testcal1 = new double[500000];
                testcal2 = new double[500000];
                testnewcal1 = new double[500000];

            }
        });
        //끝

        //DB에 비교값 저장하기 저장할 테이블을 s_values2를 초기화 하고 새로운값을 저장, 저장하면 새로층정한값을 저장한 테이블 s_values를 초기화
        Button buttonInsert1 = (Button)findViewById(R.id.DBSAVE);
        buttonInsert1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num = "1";

                task2 = new init();
                task2.execute(num,IP_ADDRESS);

            }
        });
        //끝

    }

    //UART service connected/disconnected
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder rawBinder) {
            mService = ((UartService.LocalBinder) rawBinder).getService();
            Log.d(TAG, "onServiceConnected mService= " + mService);
            if (!mService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }

        }

        public void onServiceDisconnected(ComponentName classname) {
            ////     mService.disconnect(mDevice);
            mService = null;
        }
    };

    private Handler mHandler = new Handler() {
        @Override

        //Handler events that received from UART service
        public void handleMessage(Message msg) {

        }
    };

    private final BroadcastReceiver UARTStatusChangeReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            final List<Object> myArray = new ArrayList<Object>();  ///추가했음

            final Intent mIntent = intent;
            //*********************//
            if (action.equals(UartService.ACTION_GATT_CONNECTED)) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                        Log.d(TAG, "UART_CONNECT_MSG");
                        btnConnectDisconnect.setText("Disconnect");
                        edtMessage.setEnabled(true);
                        btnSend.setEnabled(true);
                        ((TextView) findViewById(R.id.deviceName)).setText(mDevice.getName()+ " - ready");
                        listAdapter.add("["+currentDateTimeString+"] Connected to: "+ mDevice.getName());
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        mState = UART_PROFILE_CONNECTED;
                    }
                });
            }

            //*********************//
            if (action.equals(UartService.ACTION_GATT_DISCONNECTED)) {
                final byte[] txValue = intent.getByteArrayExtra(UartService.EXTRA_DATA); //내가추가함 값확인작업

                runOnUiThread(new Runnable() {
                    public void run() {
                        String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                        Log.d(TAG, "UART_DISCONNECT_MSG");
                        btnConnectDisconnect.setText("Connect (끌어치기)");
                        edtMessage.setEnabled(false);
                        btnSend.setEnabled(false);
                        ((TextView) findViewById(R.id.deviceName)).setText("Not Connected");
                        listAdapter.add("[" + currentDateTimeString + "] Disconnected to: " + mDevice.getName());
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                        Temp = NRFBLEModule.getInstance().PopQueue();

                        int k = 0; //실험
                        print_count=0;

                        while(Temp!=null) //실험
                        {
                            int splitStartPos = 0; //실험
                            int splitEndPos = 0; //실험
                            for(int i=0;i<8;i++)
                            {
                                if (k%8 == 0) {
                                    splitStartPos = 0;
                                    splitEndPos = Temp.indexOf(",");
                                    data[k]=String.valueOf(Temp.substring(splitStartPos, splitEndPos));
                                }else {
                                    splitStartPos = splitEndPos + 1;
                                    splitEndPos = Temp.indexOf(",", splitStartPos);
                                    data[k]=String.valueOf(Temp.substring(splitStartPos, splitEndPos));
                                }

                                k=k+1;
                            }
                            Temp = NRFBLEModule.getInstance().PopQueue();
                        }

                        //실험끝

                        mState = UART_PROFILE_DISCONNECTED;
                        mService.close();
                        //setUiState();

                        //disconnect실험
                    }
                });
                task1 = new InsertData();
                task1.execute("http://" + IP_ADDRESS + "/insertvalues2.php");//실험
            }


            //*********************//
            if (action.equals(UartService.ACTION_GATT_SERVICES_DISCOVERED)) {
                mService.enableTXNotification();
            }
            //*********************//
            if (action.equals(UartService.ACTION_DATA_AVAILABLE)) {

                final byte[] txValue = intent.getByteArrayExtra(UartService.EXTRA_DATA);
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            String text = new String(txValue, "UTF-8");
                            String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                            NRFBLEModule.getInstance().PushQueue(new String(txValue, "UTF-8"));
                            if (print_count==0)
                            {
                                listAdapter.add("센서에 연결이 되었습니다.");
                                messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                                messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                                print_count=1;
                                messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                                vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
                                vibrator.vibrate(1000); //연결되었을때 진동으로 알려줌
                            }
                            messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                            //실험 시작작 터치센서값이 들어오면 disconnect
                            touch_value = Integer.valueOf(hit_data[0]);
                            if (touch_value != 0)
                            {
                                x=Integer.valueOf(hit_data[0]);
                                y=Integer.valueOf(hit_data[1]);
                                String currentDateTimeString1 = DateFormat.getTimeInstance().format(new Date());
                                Log.d(TAG, "UART_DISCONNECT_MSG");
                                btnConnectDisconnect.setText("Connect (끌어치기)");
                                edtMessage.setEnabled(false);
                                btnSend.setEnabled(false);
                                ((TextView) findViewById(R.id.deviceName)).setText("Not Connected");
                                listAdapter.add("[" + currentDateTimeString + "] Disconnected to: " + mDevice.getName());
                                messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                                messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                                Temp = NRFBLEModule.getInstance().PopQueue();

                                int k = 0; //실험

                                while(Temp!=null) //실험
                                {
                                    int splitStartPos = 0; //실험
                                    int splitEndPos = 0; //실험
                                    for(int i=0;i<8;i++)
                                    {
                                        if (k%8 == 0) {
                                            splitStartPos = 0;
                                            splitEndPos = Temp.indexOf(",");
                                            data[k]=String.valueOf(Temp.substring(splitStartPos, splitEndPos));
                                        }else {
                                            splitStartPos = splitEndPos + 1;
                                            splitEndPos = Temp.indexOf(",", splitStartPos);
                                            data[k]=String.valueOf(Temp.substring(splitStartPos, splitEndPos));
                                            if(k%8==2)
                                            {
                                                data[k]= String.valueOf(Integer.valueOf(data[k])+85);
                                            }
                                        }

                                        k=k+1;
                                    }
                                    Temp = NRFBLEModule.getInstance().PopQueue();
                                }
                                double speed=0;
                                double max_speed = 0;
                                int i =0;
                                while(data[i+8]!=null) //속도계산
                                {
                                    speed = Math.abs(Integer.valueOf(data[i+8])+Integer.valueOf(data[i+9])+Integer.valueOf(data[i+10])-Integer.valueOf(data[i])-Integer.valueOf(data[i+1])-Integer.valueOf(data[i+2]))/0.3;
                                    if (max_speed < speed)
                                    {
                                        max_speed = speed;
                                    }
                                    i=i+8;
                                }
                                Trans_testcal2 = new double[500000];
                                Trans_testnewcal1 = new double[500000];
                                double result_speed = max_speed*3600/100000;

                                messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                                messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                                vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
                                if (result_speed < 10)
                                {
                                    vibrator.vibrate(500);
                                }else if (result_speed>=10 && result_speed < 30)
                                {
                                    vibrator.vibrate(1000);
                                }else if (result_speed>=30)
                                {
                                    vibrator.vibrate(1500);
                                }


                                //실험끝
                                touch_value=0;
                                mState = UART_PROFILE_DISCONNECTED;
                                mService.close();
                                //setUiState();
                                messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                                messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);
                                task1 = new InsertData();
                                task1.execute("http://" + IP_ADDRESS + "/insertvalues2.php");//실험

                            }


                            //실험끝부분

                        } catch (Exception e) {
                            Log.e(TAG, e.toString());
                        }
                    }
                });
                hit_data[0]=String.valueOf(0);
            }

            //*********************//
            if (action.equals(UartService.DEVICE_DOES_NOT_SUPPORT_UART)){
                showMessage("Device doesn't support UART. Disconnecting");
                mService.disconnect();
            }


        }
    };

    private void service_init() {
        Intent bindIntent = new Intent(this, UartService.class);
        bindService(bindIntent, mServiceConnection, Context.BIND_AUTO_CREATE);

        LocalBroadcastManager.getInstance(this).registerReceiver(UARTStatusChangeReceiver, makeGattUpdateIntentFilter());
    }
    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(UartService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(UartService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(UartService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(UartService.ACTION_DATA_AVAILABLE);
        intentFilter.addAction(UartService.DEVICE_DOES_NOT_SUPPORT_UART);
        return intentFilter;
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");

        try {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(UARTStatusChangeReceiver);
        } catch (Exception ignore) {
            Log.e(TAG, ignore.toString());
        }
        unbindService(mServiceConnection);
        mService.stopSelf();
        mService= null;

    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        if (!mBtAdapter.isEnabled()) {
            Log.i(TAG, "onResume - BT not enabled yet");
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case REQUEST_SELECT_DEVICE:
                //When the DeviceListActivity return, with the selected device address
                if (resultCode == Activity.RESULT_OK && data != null) {
                    String deviceAddress = data.getStringExtra(BluetoothDevice.EXTRA_DEVICE);
                    mDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(deviceAddress);

                    Log.d(TAG, "... onActivityResultdevice.address==" + mDevice + "mserviceValue" + mService);
                    ((TextView) findViewById(R.id.deviceName)).setText(mDevice.getName()+ " - connecting");
                    mService.connect(deviceAddress);


                }
                break;
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "Bluetooth has turned on ", Toast.LENGTH_SHORT).show();

                } else {
                    // User did not enable Bluetooth or an error occurred
                    Log.d(TAG, "BT not enabled");
                    Toast.makeText(this, "Problem in BT Turning ON ", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                Log.e(TAG, "wrong request code");
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }


    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

//    @Override
//    public void onBackPressed() {
//        if (mState == UART_PROFILE_CONNECTED) {
//            Intent startMain = new Intent(Intent.ACTION_MAIN);
//            startMain.addCategory(Intent.CATEGORY_HOME);
//            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(startMain);
//            showMessage("nRFUART's running in background.\n             Disconnect to exit");
//        }
//        else {
//            new AlertDialog.Builder(this)
//                    .setIcon(android.R.drawable.ic_dialog_alert)
//                    .setTitle(R.string.popup_title)
//                    .setMessage(R.string.popup_message)
//                    .setPositiveButton(R.string.popup_yes, new DialogInterface.OnClickListener()
//                    {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                        }
//                    })
//                    .setNegativeButton(R.string.popup_no, null)
//                    .show();
//        }
//    }

    //수정부분

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Main3Activity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG1, "POST response  - " + result);
        }

        //public StringBuilder sb = new StringBuilder();//실험변수
        @Override
        protected String doInBackground(String... params) {


            String serverURL = (String) params[0];

            int i=0; // 실험

            StringBuilder abcd= new StringBuilder() ;

            while (data[i]!=null)//실험
            {
                if (i==0)
                {
                    //postParameters1 = "x="+data[i]+" ";
                    abcd.append("x=");
                    abcd.append(data[i]);
                    abcd.append(" ");
                }else
                {
                    //postParameters1 = postParameters1 + data[i] + " ";
                    abcd.append(data[i]);
                    abcd.append(" ");
                }
                i=i+1;
            }

            data = new String[100000];;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(abcd.toString().getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG1, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);

                }


                bufferedReader.close();

                print_count=0;
                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG1, "InsertData: Error ", e);
                listAdapter.add("error");
                messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);

                return new String("Error: " + e.getMessage());
            }

        }
    } //수정끝부분

    //DB에서 PHP통해 값가져오는 부분
    //PHP를 이용한 DB값 꺼내오기
    private class phpdo extends AsyncTask<String,Void,String> {


        protected void onPreExecute(){

        }
        @Override
        protected String doInBackground(String... arg0) {

            try {
                String num =  arg0[0];
                String url1 =  arg0[1];

                compare = Integer.valueOf(num); //num값 정수형으로 저장

                String link = "http://"+url1+"/outvalues.php?num=" + num ;
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                //Scanner in = new Scanner(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                /*while ((line = in.nextLine()) != null) {
                    sb.append(line);
                    break;
                }*/
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result){


            int splitStartPos = 0;
            int splitEndPos = 0;
            int i=0;
            int a=result.length();

            if (compare == 1) //새로측정한 디비값 가져오기
            {
                while (true) //while문이 디비에서 값 result를 스플릿해서 배열에 담는 과정//
                {
                    if (i == 0)
                    {
                        splitStartPos = 0;
                        splitEndPos = result.indexOf(" ");
                        data2[i]=Integer.valueOf(result.substring(splitStartPos, splitEndPos));
                    }
                    else {
                        splitStartPos = splitEndPos + 1;
                        splitEndPos = result.indexOf(" ", splitStartPos);
                        if(splitEndPos == -1)
                            break;
                        data2[i]=Integer.valueOf(result.substring(splitStartPos, splitEndPos));
                    }

                    i=i+1;
                }
                i=i-1;
                //여기서 부터 디비값을 x,y,z,x_,y_,z_ 로 나눠서 배열에 담아주는 과정
                int l=0;
                int k=0;
                double cal_k=0;
                while(l<i)
                {
                    num1[k]=data2[l];
                    x1[k]=data2[l+1];
                    y1[k]=data2[l+2];
                    z1[k]=data2[l+3];
                    x_1[k]=data2[l+4];
                    y_1[k]=data2[l+5];
                    z_1[k]=data2[l+6];
                    j1[k]=data2[l+7];
                    b1[k]=data2[l+8];
                    l=l+9;
                    k=k+1;
                }
                i=i/9;
                len1 = i+1;
            }else if (compare == 2) //비교값으로 저장해둔 디비값 가져오기
            {
                while (true) //while문이 디비에서 값 result를 스플릿해서 배열에 담는 과정//
                {
                    if (i == 0)
                    {
                        splitStartPos = 0;
                        splitEndPos = result.indexOf(" ");
                        data3[i]=Integer.valueOf(result.substring(splitStartPos, splitEndPos));
                    }
                    else {
                        splitStartPos = splitEndPos + 1;
                        splitEndPos = result.indexOf(" ", splitStartPos);
                        if(splitEndPos == -1)
                            break;
                        data3[i]=Integer.valueOf(result.substring(splitStartPos, splitEndPos));
                    }
                    i=i+1;
                }
                i=i-1;
                //여기서 부터 디비값을 x,y,z,x_,y_,z_ 로 나눠서 배열에 담아주는 과정
                int l=0;
                int k=0;
                while(l<i)
                {
                    num2[k]=data3[l];
                    x2[k]=data3[l+1];
                    y2[k]=data3[l+2];
                    z2[k]=data3[l+3];
                    x_2[k]=data3[l+4];
                    y_2[k]=data3[l+5];
                    z_2[k]=data3[l+6];
                    j2[k]=data3[l+7];
                    b2[k]=data3[l+8];
                    l=l+9;
                    k=k+1;
                }
                i=i/9;

                len2 = i+1;
            }

            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {

                    testcal1[0]=0;
                    testcal2[0]=0;// 속도구하는 실험 새로운 식
                    for (int m=0;m<len1-1;m++) //테이블1 속도구하는 식
                    {
                        testcal1[m+1] =Math.abs(x1[m+1]+y1[m+1]+z1[m+1]-x1[m]-y1[m]-z1[m])/0.3;
                        if (maxcal1<testcal1[m+1]) //최대속도 구하기
                        {
                            maxcal1=testcal1[m+1];
                        }
                    }
                    for (int m=0;m<len2-1;m++) //테이블2 속도구하는 식
                    {
                        testcal2[m+1] =Math.abs(x2[m+1]+y2[m+1]+z2[m+1]-x2[m]-y2[m]-z2[m])/0.3;
                        if (maxcal2<testcal2[m+1]) //최대속도 구하기
                        {
                            maxcal2=testcal2[m+1];
                        }
                    }
                    //속도 구하는 실험 끝부분

                    maxcal1 = ((maxcal1)*3600)/100000; //시속으로 변환

                    maxcal2 = ((maxcal2)*3600)/100000; //시속으로 변환

                    double rate = (double)(len1-1)/(double)(len2-1);

                    newcal1[0] = cal1[0];
                    newcal1[len2-1]=cal1[len1-1];
                    for(int m=1; m<len2-1;m++)
                    {
                        rate = rate * m;
                        int rate_num = (int)rate;
                        double final_rate = rate - rate_num;
                        rate = ((double)len1-1)/((double)len2-1);

                        double plus_value10 = Math.abs(testcal1[rate_num]-testcal1[(rate_num)+1])*(final_rate); //속도구하는 실험부분
                        if(testcal1[rate_num] < testcal1[(rate_num)+1]) {
                            testnewcal1[m] = Math.min(testcal1[rate_num], testcal1[(rate_num)+1]) + plus_value10;
                        }
                        else if (testcal1[rate_num] > testcal1[(rate_num)+1]) {
                            testnewcal1[m] = Math.max(testcal1[rate_num], testcal1[(rate_num)+1]) - plus_value10;
                        }
                        else if (testcal1[rate_num] == testcal1[(rate_num)+1]) {
                            testnewcal1[m] = testcal1[rate_num];
                        }

                    }

                    //그래프 출력 및 유사율 계산

                    lineChart = (LineChart) findViewById(R.id.chart);

                    ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
                    ArrayList<Entry> valsComp2 = new ArrayList<Entry>();
                    ArrayList<Entry> valsComp3 = new ArrayList<Entry>();


                    ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>();

                    LineDataSet lineDataSet1 = new LineDataSet(valsComp1, "FX angle2 (예시)");
                    lineDataSet1.setDrawCircles(false);
                    lineDataSet1.setColor(Color.RED);

                    LineDataSet lineDataSet2 = new LineDataSet(valsComp2, "FY angle2 (예시)");
                    lineDataSet2.setDrawCircles(false);
                    lineDataSet2.setColor(Color.TRANSPARENT);

                    LineDataSet lineDataSet3 = new LineDataSet(valsComp3, "FZ angle2 (예시)");
                    lineDataSet3.setDrawCircles(false);
                    lineDataSet3.setColor(Color.BLUE);

                    //테이블1의 유사율
                    for(int k=0;k<len1;k++)
                    {
                        //AX_angle1[k] = (double)((Math.atan2(new_x1[k],new_z1[k]))*(180/Math.PI));
                        //AY_angle1[k] = (double)((Math.atan2(new_y1[k],new_z1[k]))*(180/Math.PI));
                        //AZ_angle1[k] = (double)((Math.atan2(new_z1[k],new_z1[k]))*(180/Math.PI));
                        AX_angle1[k] = (double)((Math.atan(y1[k]/Math.sqrt((x1[k]*x1[k])+(z1[k]*z1[k])))))*(180/Math.PI); //roll : 센서의 좌우로의 기울기 // 가속도x1 를 이용하여 roll을 구하는 방법
                        AY_angle1[k] = (double)((Math.atan((-1*x1[k])/Math.sqrt((y1[k]*y1[k])+(z1[k]*z1[k])))))*(180/Math.PI); // pitch : 센서의 앞뒤로의 기울기 // 가속도y1 을 이용하여 pitch를 구하는 방법
                    }

                    for(int n=0;n<len1;n++)
                    {
                        GX_calculate1[n]=(double)(x_1[n]*0.02*(180/Math.PI));
                        GY_calculate1[n]=(double)(y_1[n]*0.02*(180/Math.PI));
                        GZ_calculate1[n]=(double)(z_1[n]*0.02*(180/Math.PI));
                    }

                    GX_angle1[0] = GX_calculate1[0];
                    GY_angle1[0] = GY_calculate1[0];
                    GZ_angle1[0] = GZ_calculate1[0];

                    for(int h=1;h<len1;h++)
                    {
                        GX_angle1[h] = GX_calculate1[h-1]+GX_calculate1[h];
                        GY_angle1[h] = GY_calculate1[h-1]+GY_calculate1[h];
                        GZ_angle1[h] = GZ_calculate1[h-1]+GZ_calculate1[h];
                    }

                    for(int h=0;h<len1;h++) // 상보필터
                    {
                        //FX_angle1[h] = (0.98*GX_angle1[h]) + (0.02*AX_angle1[h]);
                        //FY_angle1[h] = (0.98*GY_angle1[h]) + (0.02*AY_angle1[h]);
                        FX_angle1[h] = (0.98*GX_angle1[h]) + (0.02*AX_angle1[h]);
                        FY_angle1[h] = (0.98*GY_angle1[h]) + (0.02*AY_angle1[h]);
                        FZ_angle1[h] = GZ_angle1[h];
                    }

                    //테이블2의 유사율
                    for(int k=0;k<len2;k++)
                    {
                        //AX_angle2[k] = (double)((Math.atan2(newx2[k],newz2[k]))*(180/Math.PI));
                        //AY_angle2[k] = (double)((Math.atan2(newy2[k],newz2[k]))*(180/Math.PI));
                        //AZ_angle2[k] = (double)((Math.atan2(newz2[k],newz2[k]))*(180/Math.PI));
                        AX_angle2[k] = (double)((Math.atan(y2[k]/Math.sqrt((x2[k]*x2[k])+(z2[k]*z2[k])))))*(180/Math.PI); //roll : 센서의 좌우로의 기울기 // 가속도x2 를 이용하여 roll을 구하는 방법
                        AY_angle2[k] = (double)((Math.atan((-1*x2[k])/Math.sqrt((y2[k]*y2[k])+(z2[k]*z2[k])))))*(180/Math.PI); // pitch : 센서의 앞뒤로의 기울기 // 가속도y2 을 이용하여 pitch를 구하는 방법
                    }

                    for(int n=0;n<len2;n++)
                    {
                        GX_calculate2[n]=(double)(x_2[n]*0.02*(180/Math.PI));
                        GY_calculate2[n]=(double)(y_2[n]*0.02*(180/Math.PI));
                        GZ_calculate2[n]=(double)(z_2[n]*0.02*(180/Math.PI));
                    }

                    GX_angle2[0] = GX_calculate2[0];
                    GY_angle2[0] = GY_calculate2[0];
                    GZ_angle2[0] = GZ_calculate2[0];

                    for(int h=1;h<len2;h++)
                    {
                        GX_angle2[h] = GX_calculate2[h-1]+GX_calculate2[h];
                        GY_angle2[h] = GY_calculate2[h-1]+GY_calculate2[h];
                        GZ_angle2[h] = GZ_calculate2[h-1]+GZ_calculate2[h];
                    }

                    for(int h=0;h<len2;h++)
                    {
                        //FX_angle2[h] = (0.98*GX_angle2[h]) + (0.02*AX_angle2[h]);
                        //FY_angle2[h] = (0.98*GY_angle2[h]) + (0.02*AY_angle2[h]);
                        FX_angle2[h] = (0.98*GX_angle2[h]) + (0.02*AX_angle2[h]);
                        FY_angle2[h] = (0.98*GY_angle2[h]) + (0.02*AY_angle2[h]);
                        FZ_angle2[h] = GZ_angle2[h];

                    }

                    double[][] editdistance_x = new double[len1+1][len2+1]; // 편집거리 알고리즘 (FX angle1 VS FX angle2)
                    double[][] editdistance_y = new double[len1+1][len2+1]; // 편집거리 알고리즘 (FY angle1 VS FY angle2)
                    double[][] editdistance_z = new double[len1+1][len2+1]; // 편집거리 알고리즘 (FZ angle1 VS FZ angle2)

                    for(int i=1;i<len1;i++) // editdistance 인덱스 값 추가
                    {
                        editdistance_x[i][0] = i;
                        editdistance_y[i][0] = i;
                        editdistance_z[i][0] = i;
                    }

                    for(int j=1;j<len2;j++) // editdistance 인덱스 값 추가
                    {
                        editdistance_x[0][j] = j;
                        editdistance_y[0][j] = j;
                        editdistance_z[0][j] = j;
                    }

                    for(int j=1;j<len2;j++) // 상보필터 x에 대한 editdistance 계산과정
                    {
                        for(int i=1;i<len1;i++)
                        {
                            if(Math.round(FX_angle1[i]) > Math.round(FX_angle2[j])-30 && Math.round(FX_angle1[i]) < Math.round(FX_angle2[j])+30)
                            {
                                editdistance_x[i][j] = editdistance_x[i-1][j-1];
                            }
                            else
                            {
                                editdistance_x[i][j] = minimum(editdistance_x[i-1][j],editdistance_x[i][j-1],editdistance_x[i-1][j-1])+1;
                            }
                        }
                    }

                    for(int j=1;j<len2;j++) // 상보필터 y에 대한 editdistance 계산과정
                    {
                        for(int i=1;i<len1;i++)
                        {
                            if(Math.round(FY_angle1[i]) > Math.round(FY_angle2[j])-30 && Math.round(FY_angle1[i]) < Math.round(FY_angle2[j])+30)
                            {
                                editdistance_y[i][j] = editdistance_y[i-1][j-1];
                            }
                            else
                            {
                                editdistance_y[i][j] = minimum(editdistance_y[i-1][j],editdistance_y[i][j-1],editdistance_y[i-1][j-1])+1;
                            }
                        }
                    }

                    for(int j=1;j<len2;j++) // 상보필터 z에 대한 editdistance 계산과정
                    {
                        for(int i=1;i<len1;i++)
                        {
                            if(Math.round(FZ_angle1[i]) > Math.round(FZ_angle2[j])-30 && Math.round(FZ_angle1[i]) < Math.round(FZ_angle2[j])+30)
                            {
                                editdistance_z[i][j] = editdistance_z[i-1][j-1];
                            }
                            else
                            {
                                editdistance_z[i][j] = minimum(editdistance_z[i-1][j],editdistance_z[i][j-1],editdistance_z[i-1][j-1])+1;
                            }
                        }
                    }

                    if (len1 >= len2)
                    {
                        result_F1=((len1-(editdistance_x[len1-1][len2-1]))/len1)*100;
                        result_F2=((len1-(editdistance_y[len1-1][len2-1]))/len1)*100;
                        result_F3=((len1-(editdistance_z[len1-1][len2-1]))/len1)*100;
                    }
                    else
                    {
                        result_F1 = ((len2-(editdistance_x[len1-1][len2-1]))/len2)*100;
                        result_F2 = ((len2-(editdistance_y[len1-1][len2-1]))/len2)*100;
                        result_F3 = ((len2-(editdistance_z[len1-1][len2-1]))/len2)*100;
                    }

                    for(int i=0; i<len2; i++)
                    {
                        valsComp1.add(new Entry((float)FX_angle2[i],i));
                        valsComp2.add(new Entry((float)FY_angle2[i],i));
                        valsComp3.add(new Entry((float)FZ_angle2[i],i));
                    }

                    ArrayList<String> xVals = new ArrayList<String>();

                    for(int p=0; p<len2; p++)
                    {
                        xVals.add(p+"");
                    }

                    lineDataSets.add(lineDataSet1);
                    //lineDataSets.add(lineDataSet2);
                    lineDataSets.add(lineDataSet3);

                    lineChart.setData(new LineData(xVals,lineDataSets));
                    lineChart.setVisibleXRangeMaximum(65f);
                    lineChart.invalidate(); //실시간 그래프 찍기

                    lineChart = (LineChart) findViewById(R.id.chart2);

                    ArrayList<Entry> valsComp4 = new ArrayList<Entry>();
                    ArrayList<Entry> valsComp5 = new ArrayList<Entry>();
                    ArrayList<Entry> valsComp6 = new ArrayList<Entry>();


                    ArrayList<LineDataSet> lineDataSets2 = new ArrayList<LineDataSet>();

                    LineDataSet lineDataSet4 = new LineDataSet(valsComp4, "FX angle1 (신규)");
                    lineDataSet4.setDrawCircles(false);
                    lineDataSet4.setColor(Color.RED);

                    LineDataSet lineDataSet5 = new LineDataSet(valsComp5, "FY angle1 (신규)");
                    lineDataSet5.setDrawCircles(false);
                    lineDataSet5.setColor(Color.TRANSPARENT);

                    LineDataSet lineDataSet6 = new LineDataSet(valsComp6, "FZ angle1 (신규)");
                    lineDataSet6.setDrawCircles(false);
                    lineDataSet6.setColor(Color.BLUE);

                    for(int i=0; i<len1; i++)
                    {
                        valsComp4.add(new Entry((float)FX_angle1[i],i));
                        valsComp5.add(new Entry((float)FY_angle1[i],i));
                        valsComp6.add(new Entry((float)FZ_angle1[i],i));
                    }

                    ArrayList<String> xVals2 = new ArrayList<String>();

                    for(int p=0; p<len1; p++)
                    {
                        xVals2.add(p+"");
                    }

                    lineDataSets2.add(lineDataSet4);
                    //lineDataSets2.add(lineDataSet5);
                    lineDataSets2.add(lineDataSet6);

                    lineChart.setData(new LineData(xVals2,lineDataSets2));
                    lineChart.setVisibleXRangeMaximum(65f);
                    lineChart.invalidate(); //실시간 그래프 찍기

                    //속도 차트
                    lineChart = (LineChart) findViewById(R.id.chart3);

                    ArrayList<Entry> valsComp7 = new ArrayList<Entry>();
                    ArrayList<Entry> valsComp8 = new ArrayList<Entry>();


                    ArrayList<LineDataSet> lineDataSets3 = new ArrayList<LineDataSet>();

                    LineDataSet lineDataSet7 = new LineDataSet(valsComp7, "속도 (예시)");
                    lineDataSet7.setDrawCircles(false);
                    lineDataSet7.setColor(Color.RED);

                    LineDataSet lineDataSet8 = new LineDataSet(valsComp8, "속도 (신규)");
                    lineDataSet8.setDrawCircles(false);
                    lineDataSet8.setColor(Color.BLUE);


                    for(int i=0; i<len2; i++)
                    {
                        valsComp7.add(new Entry((float)testcal2[i],i));
                        valsComp8.add(new Entry((float)testnewcal1[i],i));
                    }

                    ArrayList<String> xVals3 = new ArrayList<String>();

                    for(int p=0; p<len2; p++)
                    {
                        xVals3.add(p+"");
                    }

                    lineDataSets3.add(lineDataSet7);
                    lineDataSets3.add(lineDataSet8);

                    lineChart.setData(new LineData(xVals3,lineDataSets3));
                    lineChart.setVisibleXRangeMaximum(65f);
                    lineChart.invalidate(); //실시간 그래프 찍기

                }
            }, 1400);// 1.4초 정도 딜레이를 준 후 시작

        }

    }

    private class phpdo2 extends AsyncTask<String,Void,String> {


        protected void onPreExecute(){

        }
        @Override
        protected String doInBackground(String... arg0) {

            try {
                String num =  arg0[0];
                String url1 =  arg0[1];

                compare = Integer.valueOf(num); //num값 정수형으로 저장

                String link = "http://"+url1+"/outvalues4.php?num=" + num ;
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                //Scanner in = new Scanner(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                /*while ((line = in.nextLine()) != null) {
                    sb.append(line);
                    break;
                }*/
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result){


            int splitStartPos = 0;
            int splitEndPos = 0;
            int i=0;
            int a=result.length();

            if (compare == 1) //새로측정한 디비값 가져오기
            {
                while (true) //while문이 디비에서 값 result를 스플릿해서 배열에 담는 과정//
                {
                    if (i == 0)
                    {
                        splitStartPos = 0;
                        splitEndPos = result.indexOf(" ");
                        data2[i]=Integer.valueOf(result.substring(splitStartPos, splitEndPos));
                    }
                    else {
                        splitStartPos = splitEndPos + 1;
                        splitEndPos = result.indexOf(" ", splitStartPos);
                        if(splitEndPos == -1)
                            break;
                        data2[i]=Integer.valueOf(result.substring(splitStartPos, splitEndPos));
                    }
                    i=i+1;
                }
                i=i-1;
                //여기서 부터 디비값을 x,y,z,x_,y_,z_ 로 나눠서 배열에 담아주는 과정
                int l=0;
                int k=0;
                while(l<i)
                {
                    num1[k]=data2[l];
                    x1[k]=data2[l+1];
                    y1[k]=data2[l+2];
                    z1[k]=data2[l+3];
                    x_1[k]=data2[l+4];
                    y_1[k]=data2[l+5];
                    z_1[k]=data2[l+6];
                    j1[k]=data2[l+7];
                    b1[k]=data2[l+8];
                    l=l+9;
                    k=k+1;
                }
                i=i/9;
                len1 = i+1;
            }else if (compare == 2) //비교값으로 저장해둔 디비값 가져오기
            {
                while (true) //while문이 디비에서 값 result를 스플릿해서 배열에 담는 과정//
                {
                    if (i == 0)
                    {
                        splitStartPos = 0;
                        splitEndPos = result.indexOf(" ");
                        data3[i]=Integer.valueOf(result.substring(splitStartPos, splitEndPos));
                    }
                    else {
                        splitStartPos = splitEndPos + 1;
                        splitEndPos = result.indexOf(" ", splitStartPos);
                        if(splitEndPos == -1)
                            break;
                        data3[i]=Integer.valueOf(result.substring(splitStartPos, splitEndPos));
                    }
                    i=i+1;
                }
                i=i-1;
                //여기서 부터 디비값을 x,y,z,x_,y_,z_ 로 나눠서 배열에 담아주는 과정
                int l=0;
                int k=0;
                while(l<i)
                {
                    num2[k]=data3[l];
                    x2[k]=data3[l+1];
                    y2[k]=data3[l+2];
                    z2[k]=data3[l+3];
                    x_2[k]=data3[l+4];
                    y_2[k]=data3[l+5];
                    z_2[k]=data3[l+6];
                    j2[k]=data3[l+7];
                    b2[k]=data3[l+8];
                    l=l+9;
                    k=k+1;
                }
                i=i/9;
                len2 = i+1;
            }

            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    testcal1[0]=0;
                    testcal2[0]=0;// 속도구하는 실험 새로운 식
                    for (int m=0;m<len1-1;m++)
                    {
                        testcal1[m+1] =Math.abs(x1[m+1]+y1[m+1]+z1[m+1]-x1[m]-y1[m]-z1[m])/0.3;
                        if (maxcal1<testcal1[m+1])
                        {
                            maxcal1=testcal1[m+1];
                        }
                    }
                    for (int m=0;m<len2-1;m++)
                    {
                        testcal2[m+1] =Math.abs(x2[m+1]+y2[m+1]+z2[m+1]-x2[m]-y2[m]-z2[m])/0.3;
                        if (maxcal2<testcal2[m+1])
                        {
                            maxcal2=testcal2[m+1];
                        }
                    }
                    maxcal1 = ((maxcal1)*3600)/100000;
                    maxcal2 = ((maxcal2)*3600)/100000;

                    double rate = (double)(len1-1)/(double)(len2-1);

                    testnewcal1[0]=testcal1[0];
                    testnewcal1[len2-1]=testcal1[len1-1];
                    for(int m=1; m<len2-1;m++)
                    {
                        rate = rate * m;
                        int rate_num = (int)rate;
                        double final_rate = rate - rate_num;
                        rate = ((double)len1-1)/((double)len2-1);

                        double plus_value10 = Math.abs(testcal1[rate_num]-testcal1[(rate_num)+1])*(final_rate); //속도구하는 실험부분
                        if(testcal1[rate_num] < testcal1[(rate_num)+1]) {
                            testnewcal1[m] = Math.min(testcal1[rate_num], testcal1[(rate_num)+1]) + plus_value10;
                        }
                        else if (testcal1[rate_num] > testcal1[(rate_num)+1]) {
                            testnewcal1[m] = Math.max(testcal1[rate_num], testcal1[(rate_num)+1]) - plus_value10;
                        }
                        else if (testcal1[rate_num] == testcal1[(rate_num)+1]) {
                            testnewcal1[m] = testcal1[rate_num];
                        }

                    }

                    //그래프 출력 및 유사율 계산

                    lineChart = (LineChart) findViewById(R.id.chart);

                    ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
                    ArrayList<Entry> valsComp2 = new ArrayList<Entry>();
                    ArrayList<Entry> valsComp3 = new ArrayList<Entry>();


                    ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>();

                    LineDataSet lineDataSet1 = new LineDataSet(valsComp1, "FX angle2 (예시)");
                    lineDataSet1.setDrawCircles(false);
                    lineDataSet1.setColor(Color.RED);

                    LineDataSet lineDataSet2 = new LineDataSet(valsComp2, "FY angle2 (예시)");
                    lineDataSet2.setDrawCircles(false);
                    lineDataSet2.setColor(Color.TRANSPARENT);

                    LineDataSet lineDataSet3 = new LineDataSet(valsComp3, "FZ angle2 (예시)");
                    lineDataSet3.setDrawCircles(false);
                    lineDataSet3.setColor(Color.BLUE);

                    //테이블1의 유사율
                    for(int k=0;k<len1;k++)
                    {
                        //AX_angle1[k] = (double)((Math.atan2(new_x1[k],new_z1[k]))*(180/Math.PI));
                        //AY_angle1[k] = (double)((Math.atan2(new_y1[k],new_z1[k]))*(180/Math.PI));
                        //AZ_angle1[k] = (double)((Math.atan2(new_z1[k],new_z1[k]))*(180/Math.PI));
                        AX_angle1[k] = (double)((Math.atan(y1[k]/Math.sqrt((x1[k]*x1[k])+(z1[k]*z1[k])))))*(180/Math.PI); //roll : 센서의 좌우로의 기울기 // 가속도x1 를 이용하여 roll을 구하는 방법
                        AY_angle1[k] = (double)((Math.atan((-1*x1[k])/Math.sqrt((y1[k]*y1[k])+(z1[k]*z1[k])))))*(180/Math.PI); // pitch : 센서의 앞뒤로의 기울기 // 가속도y1 을 이용하여 pitch를 구하는 방법
                    }

                    for(int n=0;n<len1;n++)
                    {
                        GX_calculate1[n]=(double)(x_1[n]*0.02*(180/Math.PI));
                        GY_calculate1[n]=(double)(y_1[n]*0.02*(180/Math.PI));
                        GZ_calculate1[n]=(double)(z_1[n]*0.02*(180/Math.PI));
                    }

                    GX_angle1[0] = GX_calculate1[0];
                    GY_angle1[0] = GY_calculate1[0];
                    GZ_angle1[0] = GZ_calculate1[0];

                    for(int h=1;h<len1;h++)
                    {
                        GX_angle1[h] = GX_calculate1[h-1]+GX_calculate1[h];
                        GY_angle1[h] = GY_calculate1[h-1]+GY_calculate1[h];
                        GZ_angle1[h] = GZ_calculate1[h-1]+GZ_calculate1[h];
                    }

                    for(int h=0;h<len1;h++) // 상보필터
                    {
                        //FX_angle1[h] = (0.98*GX_angle1[h]) + (0.02*AX_angle1[h]);
                        //FY_angle1[h] = (0.98*GY_angle1[h]) + (0.02*AY_angle1[h]);
                        FX_angle1[h] = (0.98*GX_angle1[h]) + (0.02*AX_angle1[h]);
                        FY_angle1[h] = (0.98*GY_angle1[h]) + (0.02*AY_angle1[h]);
                        FZ_angle1[h] = GZ_angle1[h];
                    }

                    //테이블2의 유사율
                    for(int k=0;k<len2;k++)
                    {
                        //AX_angle2[k] = (double)((Math.atan2(newx2[k],newz2[k]))*(180/Math.PI));
                        //AY_angle2[k] = (double)((Math.atan2(newy2[k],newz2[k]))*(180/Math.PI));
                        //AZ_angle2[k] = (double)((Math.atan2(newz2[k],newz2[k]))*(180/Math.PI));
                        AX_angle2[k] = (double)((Math.atan(y2[k]/Math.sqrt((x2[k]*x2[k])+(z2[k]*z2[k])))))*(180/Math.PI); //roll : 센서의 좌우로의 기울기 // 가속도x2 를 이용하여 roll을 구하는 방법
                        AY_angle2[k] = (double)((Math.atan((-1*x2[k])/Math.sqrt((y2[k]*y2[k])+(z2[k]*z2[k])))))*(180/Math.PI); // pitch : 센서의 앞뒤로의 기울기 // 가속도y2 을 이용하여 pitch를 구하는 방법
                    }

                    for(int n=0;n<len2;n++)
                    {
                        GX_calculate2[n]=(double)(x_2[n]*0.02*(180/Math.PI));
                        GY_calculate2[n]=(double)(y_2[n]*0.02*(180/Math.PI));
                        GZ_calculate2[n]=(double)(z_2[n]*0.02*(180/Math.PI));
                    }

                    GX_angle2[0] = GX_calculate2[0];
                    GY_angle2[0] = GY_calculate2[0];
                    GZ_angle2[0] = GZ_calculate2[0];

                    for(int h=1;h<len2;h++)
                    {
                        GX_angle2[h] = GX_calculate2[h-1]+GX_calculate2[h];
                        GY_angle2[h] = GY_calculate2[h-1]+GY_calculate2[h];
                        GZ_angle2[h] = GZ_calculate2[h-1]+GZ_calculate2[h];
                    }

                    for(int h=0;h<len2;h++)
                    {
                        //FX_angle2[h] = (0.98*GX_angle2[h]) + (0.02*AX_angle2[h]);
                        //FY_angle2[h] = (0.98*GY_angle2[h]) + (0.02*AY_angle2[h]);
                        FX_angle2[h] = (0.98*GX_angle2[h]) + (0.02*AX_angle2[h]);
                        FY_angle2[h] = (0.98*GY_angle2[h]) + (0.02*AY_angle2[h]);
                        FZ_angle2[h] = GZ_angle2[h];

                    }

                    double[][] editdistance_x = new double[len1+1][len2+1]; // 편집거리 알고리즘 (FX angle1 VS FX angle2)
                    double[][] editdistance_y = new double[len1+1][len2+1]; // 편집거리 알고리즘 (FY angle1 VS FY angle2)
                    double[][] editdistance_z = new double[len1+1][len2+1]; // 편집거리 알고리즘 (FZ angle1 VS FZ angle2)

                    for(int i=1;i<len1;i++) // editdistance 인덱스 값 추가
                    {
                        editdistance_x[i][0] = i;
                        editdistance_y[i][0] = i;
                        editdistance_z[i][0] = i;
                    }

                    for(int j=1;j<len2;j++) // editdistance 인덱스 값 추가
                    {
                        editdistance_x[0][j] = j;
                        editdistance_y[0][j] = j;
                        editdistance_z[0][j] = j;
                    }

                    for(int j=1;j<len2;j++) // 상보필터 x에 대한 editdistance 계산과정
                    {
                        for(int i=1;i<len1;i++)
                        {
                            if(Math.round(FX_angle1[i]) > Math.round(FX_angle2[j])-30 && Math.round(FX_angle1[i]) < Math.round(FX_angle2[j])+30)
                            {
                                editdistance_x[i][j] = editdistance_x[i-1][j-1];
                            }
                            else
                            {
                                editdistance_x[i][j] = minimum(editdistance_x[i-1][j],editdistance_x[i][j-1],editdistance_x[i-1][j-1])+1;
                            }
                        }
                    }

                    for(int j=1;j<len2;j++) // 상보필터 y에 대한 editdistance 계산과정
                    {
                        for(int i=1;i<len1;i++)
                        {
                            if(Math.round(FY_angle1[i]) > Math.round(FY_angle2[j])-30 && Math.round(FY_angle1[i]) < Math.round(FY_angle2[j])+30)
                            {
                                editdistance_y[i][j] = editdistance_y[i-1][j-1];
                            }
                            else
                            {
                                editdistance_y[i][j] = minimum(editdistance_y[i-1][j],editdistance_y[i][j-1],editdistance_y[i-1][j-1])+1;
                            }
                        }
                    }

                    for(int j=1;j<len2;j++) // 상보필터 z에 대한 editdistance 계산과정
                    {
                        for(int i=1;i<len1;i++)
                        {
                            if(Math.round(FZ_angle1[i]) > Math.round(FZ_angle2[j])-30 && Math.round(FZ_angle1[i]) < Math.round(FZ_angle2[j])+30)
                            {
                                editdistance_z[i][j] = editdistance_z[i-1][j-1];
                            }
                            else
                            {
                                editdistance_z[i][j] = minimum(editdistance_z[i-1][j],editdistance_z[i][j-1],editdistance_z[i-1][j-1])+1;
                            }
                        }
                    }

                    if (len1 >= len2)
                    {
                        result_F1=((len1-(editdistance_x[len1-1][len2-1]))/len1)*100;
                        result_F2=((len1-(editdistance_y[len1-1][len2-1]))/len1)*100;
                        result_F3=((len1-(editdistance_z[len1-1][len2-1]))/len1)*100;
                    }
                    else
                    {
                        result_F1 = ((len2-(editdistance_x[len1-1][len2-1]))/len2)*100;
                        result_F2 = ((len2-(editdistance_y[len1-1][len2-1]))/len2)*100;
                        result_F3 = ((len2-(editdistance_z[len1-1][len2-1]))/len2)*100;
                    }

                    for(int i=0; i<len2; i++)
                    {
                        valsComp1.add(new Entry((float)FX_angle2[i],i));
                        valsComp2.add(new Entry((float)FY_angle2[i],i));
                        valsComp3.add(new Entry((float)FZ_angle2[i],i));
                    }

                    ArrayList<String> xVals = new ArrayList<String>();

                    for(int p=0; p<len2; p++)
                    {
                        xVals.add(p+"");
                    }

                    lineDataSets.add(lineDataSet1);
                    //lineDataSets.add(lineDataSet2);
                    lineDataSets.add(lineDataSet3);

                    lineChart.setData(new LineData(xVals,lineDataSets));
                    lineChart.setVisibleXRangeMaximum(65f);
                    lineChart.invalidate(); //실시간 그래프 찍기

                    lineChart = (LineChart) findViewById(R.id.chart2);

                    ArrayList<Entry> valsComp4 = new ArrayList<Entry>();
                    ArrayList<Entry> valsComp5 = new ArrayList<Entry>();
                    ArrayList<Entry> valsComp6 = new ArrayList<Entry>();


                    ArrayList<LineDataSet> lineDataSets2 = new ArrayList<LineDataSet>();

                    LineDataSet lineDataSet4 = new LineDataSet(valsComp4, "FX angle1 (신규)");
                    lineDataSet4.setDrawCircles(false);
                    lineDataSet4.setColor(Color.RED);

                    LineDataSet lineDataSet5 = new LineDataSet(valsComp5, "FY angle1 (신규)");
                    lineDataSet5.setDrawCircles(false);
                    lineDataSet5.setColor(Color.TRANSPARENT);

                    LineDataSet lineDataSet6 = new LineDataSet(valsComp6, "FZ angle1 (신규)");
                    lineDataSet6.setDrawCircles(false);
                    lineDataSet6.setColor(Color.BLUE);

                    for(int i=0; i<len1; i++)
                    {
                        valsComp4.add(new Entry((float)FX_angle1[i],i));
                        valsComp5.add(new Entry((float)FY_angle1[i],i));
                        valsComp6.add(new Entry((float)FZ_angle1[i],i));
                    }

                    ArrayList<String> xVals2 = new ArrayList<String>();

                    for(int p=0; p<len1; p++)
                    {
                        xVals2.add(p+"");
                    }

                    lineDataSets2.add(lineDataSet4);
                    //lineDataSets2.add(lineDataSet5);
                    lineDataSets2.add(lineDataSet6);

                    lineChart.setData(new LineData(xVals2,lineDataSets2));
                    lineChart.setVisibleXRangeMaximum(65f);
                    lineChart.invalidate(); //실시간 그래프 찍기

                    //속도 차트
                    lineChart = (LineChart) findViewById(R.id.chart3);

                    ArrayList<Entry> valsComp7 = new ArrayList<Entry>();
                    ArrayList<Entry> valsComp8 = new ArrayList<Entry>();


                    ArrayList<LineDataSet> lineDataSets3 = new ArrayList<LineDataSet>();

                    LineDataSet lineDataSet7 = new LineDataSet(valsComp7, "속도 (예시)");
                    lineDataSet7.setDrawCircles(false);
                    lineDataSet7.setColor(Color.RED);

                    LineDataSet lineDataSet8 = new LineDataSet(valsComp8, "속도 (신규)");
                    lineDataSet8.setDrawCircles(false);
                    lineDataSet8.setColor(Color.BLUE);


                    for(int i=0; i<len2; i++)
                    {
                        valsComp7.add(new Entry((float)testcal2[i],i));
                        valsComp8.add(new Entry((float)testnewcal1[i],i));
                    }

                    ArrayList<String> xVals3 = new ArrayList<String>();

                    for(int p=0; p<len2; p++)
                    {
                        xVals3.add(p+"");
                    }

                    lineDataSets3.add(lineDataSet7);
                    lineDataSets3.add(lineDataSet8);

                    lineChart.setData(new LineData(xVals3,lineDataSets3));
                    lineChart.setVisibleXRangeMaximum(65f);
                    lineChart.invalidate(); //실시간 그래프 찍기

                }
            }, 1400);// 1.4초 정도 딜레이를 준 후 시작

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    //init은 필요없는 부분 혹시몰라서 놔둠
    private class init extends AsyncTask<String,Void,String> {


        protected void onPreExecute(){

        }
        @Override
        protected String doInBackground(String... arg0) {

            try {
                String num =  arg0[0];
                String url1 =  arg0[1];

                String link = "http://"+url1+"/initvalue.php?num=" + num ;
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result){

            listAdapter.add("DB save");
            messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);

        }
    }
    /*블루투스 관련 hit*/
    void bluetoothOn() {
        if(mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "블루투스를 지원하지 않는 기기입니다.", Toast.LENGTH_LONG).show();
        }
        else {
            if (mBluetoothAdapter.isEnabled()) {
                Toast.makeText(getApplicationContext(), "블루투스가 이미 활성화 되어 있습니다.", Toast.LENGTH_LONG).show();
                mTvBluetoothStatus.setText("활성화");
            }
            else {
                Toast.makeText(getApplicationContext(), "블루투스가 활성화 되어 있지 않습니다.", Toast.LENGTH_LONG).show();
                Intent intentBluetoothEnable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intentBluetoothEnable, BT_REQUEST_ENABLE);
            }
        }
    }
    void bluetoothOff() {
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
            Toast.makeText(getApplicationContext(), "블루투스가 비활성화 되었습니다.", Toast.LENGTH_SHORT).show();
            mTvBluetoothStatus.setText("비활성화");
        }
        else {
            Toast.makeText(getApplicationContext(), "블루투스가 이미 비활성화 되어 있습니다.", Toast.LENGTH_SHORT).show();
        }
    }
    /*블루투스 hit 시작*/
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case BT_REQUEST_ENABLE:
                if (resultCode == RESULT_OK) { // 블루투스 활성화를 확인을 클릭하였다면
                    Toast.makeText(getApplicationContext(), "블루투스 활성화", Toast.LENGTH_LONG).show();
                    mTvBluetoothStatus.setText("활성화");
                } else if (resultCode == RESULT_CANCELED) { // 블루투스 활성화를 취소를 클릭하였다면
                    Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_LONG).show();
                    mTvBluetoothStatus.setText("비활성화");
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/
    void listPairedDevices() {
        if (mBluetoothAdapter.isEnabled()) {
            mPairedDevices = mBluetoothAdapter.getBondedDevices();

            if (mPairedDevices.size() > 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("장치 선택");

                mListPairedDevices = new ArrayList<String>();
                for (BluetoothDevice device : mPairedDevices) {
                    mListPairedDevices.add(device.getName());
                    //mListPairedDevices.add(device.getName() + "\n" + device.getAddress());
                }
                final CharSequence[] items = mListPairedDevices.toArray(new CharSequence[mListPairedDevices.size()]);
                mListPairedDevices.toArray(new CharSequence[mListPairedDevices.size()]);

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        connectSelectedDevice(items[item].toString());
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                Toast.makeText(getApplicationContext(), "페어링된 장치가 없습니다.", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "블루투스가 비활성화 되어 있습니다.", Toast.LENGTH_SHORT).show();
        }
    }
    void connectSelectedDevice(String selectedDeviceName) {
        for(BluetoothDevice tempDevice : mPairedDevices) {
            if (selectedDeviceName.equals(tempDevice.getName())) {
                mBluetoothDevice = tempDevice;
                break;
            }
        }
        try {
            mBluetoothSocket = mBluetoothDevice.createRfcommSocketToServiceRecord(BT_UUID);
            mBluetoothSocket.connect();
            mThreadConnectedBluetooth = new ConnectedBluetoothThread(mBluetoothSocket);
            mThreadConnectedBluetooth.start();
            mBluetoothHandler.obtainMessage(BT_CONNECTING_STATUS, 1, -1).sendToTarget();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "블루투스 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
        }
    }

    private class ConnectedBluetoothThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedBluetoothThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "소켓 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }
        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;

            while (true) {
                try {
                    bytes = mmInStream.available();
                    if (bytes != 0) {
                        SystemClock.sleep(100);
                        bytes = mmInStream.available();
                        bytes = mmInStream.read(buffer, 0, bytes);
                        mBluetoothHandler.obtainMessage(BT_MESSAGE_READ, bytes, -1, buffer).sendToTarget();
                    }
                } catch (IOException e) {
                    break;
                }
            }
        }
        public void write(String str) {
            byte[] bytes = str.getBytes();
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "데이터 전송 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            }
        }
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "소켓 해제 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            }
        }
    }
    /*블루투스 hit 종료*/
}
