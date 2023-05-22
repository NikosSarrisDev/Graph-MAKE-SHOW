package com.example.mytestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.io.FileOutputStream;

public class FullScreen_line_Activity extends AppCompatActivity {

    private RelativeLayout relativeLayout_Of_Graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_line);

        //Declare a button for sending email throught an email client
        Button buttonSendEmail =(Button) findViewById(R.id.button_send_email);

        //Declare a button for saving as image the graph
        Button buttonforSaving = (Button) findViewById(R.id.button_to_save_image);

        //Declare the layout that will be saved
        relativeLayout_Of_Graph = (RelativeLayout) findViewById(R.id.Graph_view_layout);

        //Declare a methoud to saveImage
        buttonforSaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImage();
            }
        });

        //get graph from layout
        GraphView graph = (GraphView) findViewById(R.id.graph_line);

        //form series (curve for graph)
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

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

        series.setColor(Color.RED);


        series.setTitle("Line Graph");

        series.setDrawDataPoints(false);

        series.setThickness(8);

        //set Title of graph

        graph.setTitle("Graph");
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

        /////////////////////////////////////////////////////////

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
    private void saveImage(){//Methoud that saves the image

        relativeLayout_Of_Graph.setDrawingCacheEnabled(true);
        relativeLayout_Of_Graph.buildDrawingCache();
        relativeLayout_Of_Graph.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = relativeLayout_Of_Graph.getDrawingCache();
        save(bitmap);

    }

    private void save(Bitmap bitmap){

        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(root + "/Download");

        //This is the name of the file we are saving
        String filename = "my_simple_image.jpg";
        File myfile = new File(file,filename);

        if(myfile.exists()){
            myfile.delete();
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(myfile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            Toast.makeText(this, "Saved...", Toast.LENGTH_SHORT).show();
            relativeLayout_Of_Graph.setDrawingCacheEnabled(false);
        }
        catch (Exception e){
//
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
//
        }
    }
}

