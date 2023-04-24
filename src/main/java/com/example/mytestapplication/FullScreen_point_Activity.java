package com.example.mytestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

public class FullScreen_point_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_point);

        //Declare a button for sending email throught an email client
        Button buttonSendEmail = findViewById(R.id.button_send_email);

        //get graph from layout
        GraphView graph = (GraphView) findViewById(R.id.graph_point);

        //form series (curve for graph)
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>();

        double y;
        for (int x=0;x<90;x++){
            y = Math.sin(2*x*0.2) - 2*Math.sin(x*0.2);
            series.appendData(new DataPoint(x,y),true,90);
        }

        graph.addSeries(series);

        buttonSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });

        //Customisation for color , title of curve , type of point

        series.setColor(Color.GREEN);
        series.setTitle("Point Chart");
        series.setShape(PointsGraphSeries.Shape.TRIANGLE);

        /////////////////////////////////////////////////////////////////////////

        //set Tittle of graph

        graph.setTitle("Heart Graph");
        graph.setTitleTextSize(90);
        graph.setTitleColor(Color.BLUE);

        ////////////////////////////////////////////////////////////////////////

        //Legend

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

        ////////////////////////////////////////////////////////////////////////

        //axis titles

        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("X Axis Title");
        gridLabel.setHorizontalAxisTitleTextSize(50);
        gridLabel.setVerticalAxisTitle("Y Axis Title");
        gridLabel.setVerticalAxisTitleTextSize(50);

        ////////////////////////////////////////////////////////////////////////

        // set manual X bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-150);
        graph.getViewport().setMaxY(150);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(4);
        graph.getViewport().setMaxX(80);

        // enable scaling and scrolling
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

        graph.addSeries(series);
    }

    private void sendMail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "recipient@example.com" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.EXTRA_TEXT, "Body");
        startActivity(Intent.createChooser(intent, "Send Email"));
    }
}