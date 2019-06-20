package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

//import android.support.v7.app.AppCompatActivity;
//
//import android.support.v7.app.ActionBarActivity;
//
//그래프출력
//import android.support.v7.app.AppCompatActivity;
//


public class Speed3Activity extends Activity {

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

        testcal2 = Main3Activity.Trans_testcal2;
        testnewcal1=Main3Activity.Trans_testnewcal1;
        maxcal1 = Main3Activity.Trans_maxcal1;
        maxcal2 = Main3Activity.Trans_maxcal2;
        len2 = Main3Activity.Trans_len2;

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
