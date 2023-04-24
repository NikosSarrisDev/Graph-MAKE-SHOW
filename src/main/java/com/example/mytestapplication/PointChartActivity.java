package com.example.mytestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

public class PointChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_point_chart);

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

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openFullScreen_point_Activity(); }
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
    }

    public void openFullScreen_point_Activity() {
        Intent intent = new Intent(this, FullScreen_point_Activity.class);

        startActivity(intent);
    }
}