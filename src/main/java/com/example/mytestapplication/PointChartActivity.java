package com.example.mytestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

        //Declare a button for changing the equasion
        Button equasion_Button = (Button) findViewById(R.id.submit_button_for_eqausion);

        Button submit_button_for_name_line  =(Button) findViewById(R.id.submit_button);

        Button submit_button_for_name_of_graph = (Button) findViewById(R.id.submit_button_for_graph_name);

        Button submit_button_for_text_Size_of_Graph = (Button) findViewById(R.id.submit_button_for_graph_text_size);

        //EditText for equasion
        EditText editText_for_Equasion = (EditText) findViewById(R.id.form_of_equasion);

        //Declare a EditText for name of graph
        EditText editText_for_graph = (EditText) findViewById(R.id.name_of_graph);

        //Declare an EditText for text Size of the Graph
        EditText editText_for_text_Size_of_Graph = (EditText) findViewById(R.id.Text_size_of_graph);

        //Declare an EditText for name of line
        EditText editText_for_line =(EditText) findViewById(R.id.name_of_line);

        //Declare a Checkbox for showing the Legend in graph
        CheckBox Show_the_Legend = (CheckBox) findViewById(R.id.Show_Legend_in_Graph);

        //Declare an EditText for changing the name of X Axis
        EditText X_Axis_name = (EditText) findViewById(R.id.Title_of_X_Axis);

        //Declare an EditText for changing the name of Y Axis
        EditText Y_Axis_name = (EditText) findViewById(R.id.Title_of_Y_Axis);

        //Declare an Button for submiting the title of X Axis
        Button submit_X_Axis = (Button) findViewById(R.id.submit_button_for_X_Axis_name);

        //Declare a Button for submiting the title of Y Axis
        Button submit_Y_Axis = (Button) findViewById(R.id.submit_button_for_Y_Axis_name);

        //get graph from layout
        GraphView graph = (GraphView) findViewById(R.id.graph_point);

        //form series (curve for graph)
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>();

        double y;
        for (int x=0;x<90;x++){
            y = Math.sin(2*x*0.2) - 2*Math.sin(x*0.2);
//            y = x + 1;
            series.appendData(new DataPoint(x,y),true,90);
        }

        graph.addSeries(series);

        //Graph in full screen Activity

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFullScreen_line_Activity();
            }
        });

        //////////////////////////////////////////////////////////////////////////////

        //Customisation for color , title of curve , DataPoint raduis , thickness

        //Set a color_Set array for the colors in the Color Dropdown
        int[] color_Set = {Color.RED,Color.GREEN,Color.YELLOW,Color.BLUE,Color.BLACK,Color.CYAN,Color.DKGRAY,Color.LTGRAY,Color.MAGENTA,Color.TRANSPARENT,Color.WHITE};

        //Listener for submiting the name of line
        submit_button_for_name_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Set custom title for line
                series.setTitle(editText_for_line.getText().toString());
            }
        });

        series.setColor(Color.GREEN);


        series.setTitle("Graph");

//        series.setDrawDataPoints(false);

//        series.setThickness(8);

        /////////////////////////////////////////////////////////////////////////

        submit_button_for_name_of_graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                graph.setTitle(editText_for_graph.getText().toString());
            }
        });

        //set Title of graph

        graph.setTitle("Graph");
        graph.setTitleTextSize(90);
        graph.setTitleColor(Color.BLUE);

        ////////////////////////////////////////////////////////////////////////

        submit_button_for_text_Size_of_Graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                graph.setTitleTextSize(Integer.parseInt(editText_for_text_Size_of_Graph.getText().toString()));
            }
        });

        //Legend

        Show_the_Legend.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    graph.getLegendRenderer().setVisible(true);
                }else{
                    graph.getLegendRenderer().setVisible(false);
                }
            }
        });

        graph.getLegendRenderer().setVisible(false);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

        ////////////////////////////////////////////////////////////////////////

        //axis titles

        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("X Axis Title");

        submit_X_Axis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gridLabel.setHorizontalAxisTitle(X_Axis_name.getText().toString());
            }
        });

        gridLabel.setHorizontalAxisTitleTextSize(50);
        gridLabel.setVerticalAxisTitle("Y Axis Title");

        submit_Y_Axis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gridLabel.setVerticalAxisTitle(Y_Axis_name.getText().toString());
            }
        });

        gridLabel.setVerticalAxisTitleTextSize(50);

        /////////////////////////////////////////////////////////
    }

    //Move to Activity
    public void openFullScreen_line_Activity() {
        Intent intent = new Intent(this, FullScreen_point_Activity.class);

        startActivity(intent);
    }
}